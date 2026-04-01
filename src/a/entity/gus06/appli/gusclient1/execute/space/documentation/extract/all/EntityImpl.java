package a.entity.gus06.appli.gusclient1.execute.space.documentation.extract.all;

import a.framework.*;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20141028";}


	private Service extract1;
	private Service extract2;
	private Service extract3;
	private Service extract4;


	public EntityImpl() throws Exception
	{
		extract1 = Outside.service(this,"gus06.app.jarfile.extract1.framework2");
		extract2 = Outside.service(this,"gus06.app.jarfile.extract1.manager2");
		extract3 = Outside.service(this,"gus06.app.jarfile.extract1.entities");
		extract4 = Outside.service(this,"gus06.app.jarfile.extract1.resources2");
	}
	
	
	public void e() throws Exception
	{
		extract1.e();
		extract2.e();
		extract3.e();
		extract4.e();
		
		JOptionPane.showMessageDialog(null,"Full extraction is over");
	}
}
