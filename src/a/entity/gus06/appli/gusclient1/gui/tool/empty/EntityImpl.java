package a.entity.gus06.appli.gusclient1.gui.tool.empty;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;
import java.io.File;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20140814";}

	private JPanel panel;
	
	private String entityName;
	private File entityFile;
	private JTextComponent comp;
	
	

	public EntityImpl() throws Exception
	{
		panel = new JPanel();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		entityName = (String) o[0];
		entityFile = (File) o[1];
		comp = (JTextComponent) o[2];
	}
}
