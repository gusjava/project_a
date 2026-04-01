package a.entity.gus06.appli.gusclient1.execute.space.documentation.extract.resources;

import a.framework.*;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140910";}


	private Service extract;


	public EntityImpl() throws Exception
	{
		extract = Outside.service(this,"gus06.app.jarfile.extract1.resources2");
	}
	
	
	public void e() throws Exception
	{
		extract.e();
		JOptionPane.showMessageDialog(null,"Resource extraction is over");
	}
}
