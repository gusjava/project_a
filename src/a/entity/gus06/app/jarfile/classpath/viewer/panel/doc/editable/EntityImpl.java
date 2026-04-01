package a.entity.gus06.app.jarfile.classpath.viewer.panel.doc.editable;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Event;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;


public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20140830";}

	public static final KeyStroke KEYSTROKE = KeyStroke.getKeyStroke(KeyEvent.VK_S,Event.CTRL_MASK);
	
	private Service register;


	private JTextComponent comp;
	private String classPath;
	
	public EntityImpl() throws Exception
	{
		register = Outside.service(this,"gus06.ling.gui.lingdir.register");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		comp = (JTextComponent) obj;
		comp.setEditable(true);
		
		AbstractAction action = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {save();}
		};
		comp.getInputMap().put(KEYSTROKE,action);
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("classPath")) {classPath = (String) obj;return;}
		throw new Exception("Unknown key: "+key);
	}



	private void save()
	{
		try
		{
			if(classPath==null) return;
			String text = comp.getText();
			
			register.v(lingKey(),text);
		}
		catch(Exception e)
		{Outside.err(this,"save()",e);}
	}


	private String lingKey()
	{return "doc1_class_"+classPath.toLowerCase();}
}
