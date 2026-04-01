package a.entity.gus06.appli.gusgadgets.item.desktopviewer;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, I, R {

	public String creationDate() {return "20160605";}

	public static final String NAME = "desktopviewer";
	public static final String TITLE = "Desktop Viewer";


	private Service item;

	public EntityImpl() throws Exception
	{
		item = Outside.service(this,"gus06.sys.screen1.viewer.printscreen");
	}
	
	
	public Object i() throws Exception
	{return item.i();}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("name")) return NAME;
		if(key.equals("title")) return TITLE;
		if(key.equals("size")) return null;
		
		if(key.equals("config")) return null;
		if(key.equals("mini")) return null;
		
		if(key.equals("keys")) return new String[]{"name","title","size","config","mini"};
		
		throw new Exception("Unknown key: "+key);
	}
}
