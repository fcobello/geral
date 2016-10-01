package br.com.android.weatherforecast;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import com.sun.awt.AWTUtilities;
import br.com.android.weatherforecast.weather.GoogleWeatherHandler;
import br.com.android.weatherforecast.weather.WeatherCurrentCondition;
import br.com.android.weatherforecast.weather.WeatherSet;
import br.com.android.weatherforecast.weather.WeatherUtils;

/**
 * Classe para Gerenciamento da Aplicação de Previsão do Tempo
 * @author Felipe Cobello
 * @version 1.0
 *
 */
public class WeatherForecast
{
	public final static String DEBUG_TAG = "WEATHER_FORECAST";
	private JDialog frmTela = new JDialog();
	private JLabel lblImg = new JLabel(new ImageIcon(getClass().getResource("/img/sunny.png")));
	private JPanel pCondicoes = new JPanel(new GridLayout(3, 1));
	private JLabel lblCidade = new JLabel("Cidade");
	private JLabel lblCondicao = new JLabel("Condição");
	private JLabel lblVento = new JLabel("Vento");
	
	public WeatherForecast()
	{
		frmTela.setSize(160, 190);
		frmTela.setResizable(false);
		frmTela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frmTela.setLocation((int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth() - frmTela.getSize().getWidth()), (int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight() - frmTela.getSize().getHeight()));
		frmTela.setTitle("Previsão do Tempo");
		frmTela.setAlwaysOnTop(true);
		frmTela.add(lblImg, BorderLayout.NORTH);
		pCondicoes.add(lblCidade);
		pCondicoes.add(lblCondicao);
		pCondicoes.add(lblVento);
		frmTela.add(pCondicoes, BorderLayout.CENTER);
//		AWTUtilities.setWindowOpacity(frmTela, 0.7f);
		frmTela.setVisible(true);
	}

	/**
	 * Atualiza Previsão Atual
	 * @param aWCIS Informaçoes do Tempo
	 */
	private void updateWeatherInfoView(WeatherCurrentCondition aWCIS)
	{
		lblImg.setIcon(new ImageIcon(getClass().getResource("/img/" + WeatherUtils.getImage(aWCIS.getIconURL().split("/")[4]))));
		lblCondicao.setText(aWCIS.getTempCelcius() + "°C " + aWCIS.getCondition());
		lblVento.setText(aWCIS.getWindCondition());
	}

	private void searchWeatherInfo()
	{
		WeatherSet ws;

		try
		{
			ws = getWeatherSet(lblCidade.getText());
			if (ws != null)
				updateWeatherInfoView(ws.getWeatherCurrentCondition());
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(frmTela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public WeatherSet getWeatherSet(String cityParam) throws MalformedURLException, IOException, SAXException, ParserConfigurationException
	{
		String line;
		String queryString;
		GoogleWeatherHandler gwh = new GoogleWeatherHandler();
		StringBuilder xml = new StringBuilder();
		URLConnection connection;
		BufferedReader reader;
		ByteArrayInputStream in;
		XMLReader xr;
		WeatherSet ws = null;

		if (cityParam.equals(""))
			return ws;
		queryString = "http://www.google.com/ig/api?weather=" + cityParam + "&hl=pt-br";
		connection = new URL(queryString.replace(" ", "%20")).openConnection();
		connection.setConnectTimeout(1000 * 5);
		reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
		while ((line = reader.readLine()) != null)
			xml.append(line);
		in = new ByteArrayInputStream(xml.toString().getBytes());
		xr = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
		xr.setContentHandler(gwh);
		xr.parse(new InputSource(in));
		ws = gwh.getWeatherSet();
		reader.close();
		in.close();
		connection = null;
		reader = null;
		in = null;
		xr = null;
		return ws;
	}
	
	public void update()
	{
		lblCidade.setText(JOptionPane.showInputDialog("Informe a Cidade:"));
		if(!lblCidade.getText().isEmpty())
			searchWeatherInfo();
		else
			update();
	}
	
	public static void main(String[] args)
	{
		new WeatherForecast().update();
	}
}