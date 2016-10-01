package br.com.fc.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class Validator
{
	private boolean validate;
	private List<JComponent> lstComponent = new ArrayList<JComponent>();
	private Color COLOR_VALIDATE = new Color(255, 98, 98);
	
	public void addIsFilled(JTextField txtField)
	{
		lstComponent.add(txtField);
	}
	
	public void addIsFilled(JDateChooser txtDate)
	{
		lstComponent.add(txtDate);
	}
	
	public void addIsSelected(JComboBox<?> cmbBox)
	{
		lstComponent.add(cmbBox);
	}
	
	public boolean validate()
	{
		validate = true;
		
		for (JComponent comp : lstComponent)
		{
			if(comp instanceof JTextField)
			{
				if(((JTextField) comp).getText().isEmpty())
				{
					comp.setBorder(BorderFactory.createLineBorder(COLOR_VALIDATE));
					validate = false;
				}
			}
			else if(comp instanceof JComboBox)
			{
				if(((JComboBox<?>) comp).getSelectedIndex() == -1)
				{
					comp.setBorder(BorderFactory.createLineBorder(COLOR_VALIDATE));
					validate = false;
				}
			}
			else if(comp instanceof JDateChooser)
			{
				if(((JDateChooser) comp).getDate() == null)
				{
					comp.setBorder(BorderFactory.createLineBorder(COLOR_VALIDATE));
					validate = false;
				}
			}
		}
		return validate;
	}
	
	public void clear()
	{
		for(JComponent comp : lstComponent)
		{
			if(comp instanceof JDateChooser)
				comp.setBorder(BorderFactory.createEmptyBorder());
			else
				comp.setBorder(BorderFactory.createEtchedBorder());
		}
	}
}
