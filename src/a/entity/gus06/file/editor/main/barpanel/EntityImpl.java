package a.entity.gus06.file.editor.main.barpanel;

import a.framework.*;
import javax.swing.JPanel;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20190331";}


	private JPanel panel;

	public EntityImpl() throws Exception
	{
		panel = new JPanel();
	}
	
	
	public Object i() throws Exception
	{
		return panel;
	}
	
	
	public void p(Object obj) throws Exception
	{
		
	}
}
