

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.farng.mp3.AbstractMP3Tag;
import org.farng.mp3.MP3File;
import org.farng.mp3.id3.ID3v1;

public class MP3Renamer
{
	public static void main(String[] args)
	{
		JFileChooser fc = new JFileChooser("C:\\Users\\Felipe\\Music");
		StringBuilder list = new StringBuilder();
		MP3File mp3;
		AbstractMP3Tag id3;
		int opcao;
		
		try
		{ 
			fc.setMultiSelectionEnabled(true);
			if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				opcao = JOptionPane.showOptionDialog(null, "Escolha modelo de Leitura", "MP3 Renamer", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"ID3 > Name", "Name > ID3"}, "ID3 > Name");
				for (File file : fc.getSelectedFiles())
				{
					mp3 = new MP3File(file);
					id3 = mp3.hasID3v1Tag() ? mp3.getID3v1Tag():mp3.hasID3v2Tag()?mp3.getID3v2Tag():new ID3v1();
					if(opcao == 0)
					{
						if(id3 != null)
						{
							if(!id3.getLeadArtist().isEmpty())
							{
								if(!id3.getSongTitle().isEmpty())
								{
									if(file.renameTo(new File(file.getParentFile(), id3.getLeadArtist() + " - " + id3.getSongTitle() + ".mp3")))
										list.append(id3.getLeadArtist() + " - " + id3.getSongTitle()).append("\n");
								}
							}
							else
							{
								if(!id3.getSongTitle().isEmpty())
								{
									if(file.renameTo(new File(file.getParentFile(), id3.getSongTitle() + ".mp3")))
										list.append(id3.getSongTitle()).append("\n");
								}
							}
						}
					}
					else
					{
						String[] name = file.getName().split("-");
						
						switch (name.length) {
						case 2:
							id3.setLeadArtist(name[0]);
							id3.setSongTitle(name[1].split("[.]")[0]);
							if(!mp3.hasID3v1Tag() && !mp3.hasID3v2Tag())
								mp3.setID3v1Tag(id3);
							mp3.save();
							list.append(id3.getLeadArtist() + " - " + id3.getSongTitle()).append("\n");
							break;
						default:
							break;
						}
					}
				}
				JOptionPane.showMessageDialog(null, list.toString(), "Music", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
