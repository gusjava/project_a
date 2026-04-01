package a.entity.gus06.maincust.customizeframe;

import a.framework.*;
import javax.swing.JFrame;
import java.util.Map;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140903";}

	public static final String APPICON = "app.icon";
	public static final String APPSIZE = "app.size";
	public static final String APPTITLE = "app.title";
	public static final String APPVERSION = "app.version";
	public static final String APPVISIBLEONSTART = "app.visibleonstart";


	private Map prop;
	
	private Service setIcon;
	private Service setSize;


	public EntityImpl() throws Exception
	{
		setIcon = Outside.service(this,"gus06.swing.frame.cust2.icon");
		setSize = Outside.service(this,"gus06.swing.frame.cust2.size");
		
		prop = (Map) Outside.resource(this,"prop");
	}
	
	
	
	
	
	public void p(Object obj) throws Exception
	{	
		JFrame frame = (JFrame) obj;
		
		if(has(APPICON))
		{
			String iconId = prop(APPICON);
			setIcon.v(iconId,frame);
		}
		if(has(APPSIZE))
		{
			String size = prop(APPSIZE);
			setSize.v(size,frame);
		}
		
		String title = buildTitle();
		if(title!=null) frame.setTitle(title);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(visibleOnStart());
	}
	
	
	
	
	private String buildTitle()
	{
		if(!has(APPTITLE)) return null;
		String title = prop(APPTITLE);
		
		if(has(APPVERSION)) title = title+" "+prop(APPVERSION);
		return title;
	}
	
	
	
	private boolean visibleOnStart()
	{
		if(!has(APPVISIBLEONSTART)) return true;
		String v = prop(APPVISIBLEONSTART);
		return Boolean.parseBoolean(v);
	}
	
	
	
	private String prop(String key)
	{return (String) prop.get(key);}
	
	private boolean has(String key)
	{return prop.containsKey(key);}
}
