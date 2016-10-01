package br.com.java;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Luzes extends JFrame{

	private static final long serialVersionUID = -7481827715386759389L;
	private Robot key;
	
	public Luzes() throws AWTException
	{
		key = new Robot();
	}
	
	public void LuzesApagar()
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		if(tk.getLockingKeyState(KeyEvent.VK_NUM_LOCK))
		{
			key.keyPress(KeyEvent.VK_NUM_LOCK);
			key.keyRelease(KeyEvent.VK_NUM_LOCK);
		}
		if(tk.getLockingKeyState(KeyEvent.VK_CAPS_LOCK))
		{
			key.keyPress(KeyEvent.VK_CAPS_LOCK);
			key.keyRelease(KeyEvent.VK_CAPS_LOCK);
		}
		if(tk.getLockingKeyState(KeyEvent.VK_SCROLL_LOCK))
		{
			key.keyPress(KeyEvent.VK_SCROLL_LOCK);
			key.keyRelease(KeyEvent.VK_SCROLL_LOCK);
		}
	}
	
	private void LuzesDancar_0()
	{
		key.delay(300);
		key.keyPress(KeyEvent.VK_NUM_LOCK);
		key.keyRelease(KeyEvent.VK_NUM_LOCK);
	}
	
	public void LuzesDancar_1()
	{
		key.delay(100);
		key.keyPress(KeyEvent.VK_NUM_LOCK);
		key.delay(100);
		key.keyPress(KeyEvent.VK_CAPS_LOCK);
		key.delay(100);
		key.keyPress(KeyEvent.VK_SCROLL_LOCK);
		
		key.keyRelease(KeyEvent.VK_CAPS_LOCK);
		key.keyRelease(KeyEvent.VK_NUM_LOCK);
		key.keyRelease(KeyEvent.VK_SCROLL_LOCK);
	}
	
	public void LuzesDancar_2()
	{
		key.delay(100);
		key.keyPress(KeyEvent.VK_NUM_LOCK);
		key.keyPress(KeyEvent.VK_CAPS_LOCK);
		key.keyPress(KeyEvent.VK_SCROLL_LOCK);
		
		key.keyRelease(KeyEvent.VK_NUM_LOCK);		
		key.keyRelease(KeyEvent.VK_CAPS_LOCK);
		key.keyRelease(KeyEvent.VK_SCROLL_LOCK);
	}
	
	public void LuzesDancar_3()
	{
		key.delay(100);
		key.keyPress(KeyEvent.VK_SCROLL_LOCK);
		key.delay(100);
		key.keyPress(KeyEvent.VK_CAPS_LOCK);
		key.delay(100);
		key.keyPress(KeyEvent.VK_NUM_LOCK);

		key.keyRelease(KeyEvent.VK_SCROLL_LOCK);
		key.keyRelease(KeyEvent.VK_CAPS_LOCK);
		key.keyRelease(KeyEvent.VK_NUM_LOCK);
	}
	
	public void LuzesDancar_4()
	{
		key.delay(200);
		key.keyPress(KeyEvent.VK_NUM_LOCK);
		key.keyPress(KeyEvent.VK_CAPS_LOCK);
		key.keyPress(KeyEvent.VK_SCROLL_LOCK);
		
		key.keyRelease(KeyEvent.VK_NUM_LOCK);
		key.keyRelease(KeyEvent.VK_CAPS_LOCK);
		key.keyRelease(KeyEvent.VK_SCROLL_LOCK);
	}
	
	public void dance06()
	{
		key.keyPress(KeyEvent.VK_NUM_LOCK);
		key.keyPress(KeyEvent.VK_SCROLL_LOCK);
		key.delay(200);
		key.keyRelease(KeyEvent.VK_NUM_LOCK);
		key.keyRelease(KeyEvent.VK_SCROLL_LOCK);
	}
	
	public void dance07()
	{
		key.keyPress(KeyEvent.VK_NUM_LOCK);
		key.delay(50);
		key.keyRelease(KeyEvent.VK_NUM_LOCK);
	}
	
	public void dance08()
	{
		key.keyPress(KeyEvent.VK_NUM_LOCK);
		key.keyPress(KeyEvent.VK_CAPS_LOCK);
		key.delay(50);
		key.keyRelease(KeyEvent.VK_NUM_LOCK);
		key.keyRelease(KeyEvent.VK_CAPS_LOCK);
	}
	
	public void dance09()
	{
		key.keyPress(KeyEvent.VK_NUM_LOCK);
		key.keyPress(KeyEvent.VK_CAPS_LOCK);
		key.keyPress(KeyEvent.VK_SCROLL_LOCK);
		key.delay(50);
		key.keyRelease(KeyEvent.VK_NUM_LOCK);
		key.keyRelease(KeyEvent.VK_CAPS_LOCK);
		key.keyRelease(KeyEvent.VK_SCROLL_LOCK);
	}
	
	public static void main(String[] args) throws AWTException {
		Luzes objLuzes = new Luzes();
		
		objLuzes.LuzesApagar();
		for(int i = 0; i <= 9; i++)
			objLuzes.LuzesDancar_0();
		for(int i = 0; i <= 37; i++)
			objLuzes.dance07();
		for(int i = 0; i <= 37; i++)
			objLuzes.dance08();
		for(int i = 0; i <= 37; i++)
			objLuzes.dance09();
		for(int i = 0; i <= 9; i++)
			objLuzes.LuzesDancar_1();
		for(int i = 0; i <= 9; i++)
			objLuzes.LuzesDancar_2();
		for(int i = 0; i <= 9; i++)
			objLuzes.LuzesDancar_3();
		for(int i = 0; i <= 9; i++)
			objLuzes.LuzesDancar_4();
		for(int i = 0; i <= 9; i++)
			objLuzes.dance06();
		for(int i = 0; i <= 18; i++)
		{
			if(i%2 == 0)
				objLuzes.LuzesDancar_1();
			else
				objLuzes.LuzesDancar_3();
		}
		objLuzes.LuzesApagar();
	}
}
