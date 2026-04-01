package a.entity.gus06.appli.gusclient1.init.custframe;

import a.framework.*;
import javax.swing.JFrame;
import java.util.Map;


public class EntityImpl implements Entity {

	public String creationDate() {return "20140803";}

	public static final String APPICON = "app.icon";
	public static final String APPSIZE = "app.size";


	private JFrame frame;
	private Map prop;
	
	private Service setIcon;
	private Service setSize;


	public EntityImpl() throws Exception
	{
		setIcon = Outside.service(this,"gus06.swing.frame.cust2.icon");
		setSize = Outside.service(this,"gus06.swing.frame.cust2.size");
		
		frame = (JFrame) Outside.resource(this,"mainframe");
		prop = (Map) Outside.resource(this,"prop");
		
		if(prop.containsKey(APPICON))
		{
			String iconId = (String) prop.get(APPICON);
			setIcon.v(iconId,frame);
		}
		if(prop.containsKey(APPSIZE))
		{
			String size = (String) prop.get(APPSIZE);
			setSize.v(size,frame);
		}
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
