package a.entity.gus06.awt.focus.keylistener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.JLabel;
import a.framework.*;

public class EntityImpl implements Entity, V, ActionListener {

	public String creationDate() {return "20151015";}

	
	private static JLabel l = new JLabel();

	private Service focusSupport;
	private Component comp;
	private Vector listeners;

	
	public EntityImpl() throws Exception
	{
		focusSupport = Outside.service(this,"gus06.awt.focus.focussupport");
		focusSupport.addActionListener(this);
		
		listeners = new Vector();
	}


	

	public void v(String key, Object obj) throws Exception
	{
		if(obj instanceof KeyListener)
		{
			if(key.equals("add")) addListener((KeyListener)obj);
			if(key.equals("remove")) removeListener((KeyListener)obj);
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void addListener(KeyListener listener)
	{
		listeners.add(listener);
		if(comp!=null) comp.addKeyListener(listener);
	}
	
	private void removeListener(KeyListener listener)
	{
		listeners.remove(listener);
		if(comp!=null) comp.removeKeyListener(listener);
	}

	
	
	
	public void actionPerformed(ActionEvent e)
	{focusChanged();}
	
	
	
	
	private void focusChanged()
	{ 
		if(comp!=null)
		for(int i=0;i<listeners.size();i++)
		{
			KeyListener listener = (KeyListener) listeners.get(i);
			comp.removeKeyListener(listener);
		}
		findNewFocus();
		
		if(comp!=null)
		for(int i=0;i<listeners.size();i++)
		{
			KeyListener listener = (KeyListener) listeners.get(i);
			comp.addKeyListener(listener);
		}
	}

	
	
	
	
	private void findNewFocus()
	{
		try
		{
			Object focused = focusSupport.g();
			
			if(focused==null)
			{comp = l;l.requestFocus();return;}
			
			if(!(focused instanceof Component))
			{comp = l;l.requestFocus();return;}
			
			comp = (Component) focused;
		}
		catch(Exception e)
		{Outside.err(this,"findNewFocus()",e);}
	}
}
