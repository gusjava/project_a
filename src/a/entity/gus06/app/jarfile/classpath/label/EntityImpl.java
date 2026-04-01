package a.entity.gus06.app.jarfile.classpath.label;

import a.framework.*;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20140830";}


	private Service paintLabel;
	private JLabel label;
	private String classPath;
	
	
	public EntityImpl() throws Exception
	{
		paintLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		
		label = new JLabel(" ");
		label.setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	public void p(Object obj) throws Exception
	{
		classPath = (String) obj;
		
		if(classPath==null) paintLabel.v(" ",label);
		else paintLabel.v("UTIL_javasrc#"+classPath,label);
	}
}
