package a.entity.gus06.y.maven1.gui2;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, V, R, G, ActionListener {

	public String creationDate() {return "20251220";}


	private JPanel panel;
	
	
	private Object engine;

	public EntityImpl() throws Exception
	{
		panel = new JPanel(new BorderLayout());
		
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	public Object g() throws Exception
	{return null;}
	
	
	public Object r(String key) throws Exception
	{
		
		throw new Exception("Unknown key: "+key);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("engine"))
		{
			if(engine!=null) ((S)engine).removeActionListener(this);
			engine = (R) obj;
			((S)engine).addActionListener(this);
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("loaded()")) {rebuild();return;}
	}
	
	
	private void rebuild()
	{
		try
		{
		}
		catch(Exception e)
		{Outside.err(this,"rebuild()",e);}
	}
}
