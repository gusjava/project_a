package a.entity.gus06.app.icon;

import a.framework.*;
import java.util.Map;
import javax.swing.Icon;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150607";}

	public static final String APPICON = "app.icon";
	
	
	private Service ip;
	private Map prop;
	private Icon icon;
	
	
	
	public EntityImpl() throws Exception
	{
		ip = Outside.service(this,"gus06.icon.provider");
		prop = (Map) Outside.resource(this,"props");
	}
	
	
	public Object g() throws Exception
	{
		if(icon==null) init();
		return icon;
	}
	
	
	private void init() throws Exception
	{
		if(!has(APPICON)) return;
		
		String iconId = prop(APPICON);
		icon = (Icon) ip.r(iconId);
	}
	
	
	
	private String prop(String key)
	{return (String) prop.get(key);}
	
	private boolean has(String key)
	{return prop.containsKey(key);}
}
