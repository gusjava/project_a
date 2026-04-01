package a.entity.gus06.jna.keyboard.buffer;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl extends S1 implements Entity, ActionListener, G, R, F {

	public String creationDate() {return "20141217";}


	private Service keyboard;
	private List list;
	private String lastKey;


	public EntityImpl() throws Exception
	{
		keyboard = Outside.service(this,"gus06.jna.keyboard.display");
		list = new ArrayList();
		keyboard.addActionListener(this);
	}
	
	
	public Object g() throws Exception
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<list.size();i++) b.append(list.get(i)+" ");
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("list")) return list;
		if(key.equals("lastKey")) return lastKey;
		
		if(key.equals("keys")) return new String[]{"list","lastKey"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		String s = ((String) obj).toUpperCase();
		String[] nn = s.split(" ");
		
		if(nn.length!=list.size()) return false;
		for(int i=0;i<nn.length;i++)
			if(!nn[i].equals(list.get(i))) return false;
		return true;
	}


	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("keyPressed()")) keyPressed();
		else if(s.equals("keyReleased()")) keyReleased();
	}
	
	
	
	private void keyPressed()
	{
		try
		{
			String display = (String) keyboard.g();
			if(!list.contains(display)) list.add(display);
			lastKey = "+"+display;
			modified();
		}
		catch(Exception e)
		{Outside.err(this,"keyPressed()",e);}
	}
	
	
	private void keyReleased()
	{
		try
		{
			String display = (String) keyboard.g();
			list.remove(display);
			lastKey = "-"+display;
			modified();
		}
		catch(Exception e)
		{Outside.err(this,"keyReleased()",e);}
	}
	
	
	private void modified()
	{send(this,"modified()");}
}
