package a.entity.gus06.appli.gusgadgets.item.demo3d;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, I, R {

	public String creationDate() {return "20160605";}

	public static final String NAME = "demo3d";
	public static final String TITLE = "3D Animation Demo";
	public static final Dimension SIZE = new Dimension(200,200);


	private Service item;

	public EntityImpl() throws Exception
	{
		item = Outside.service(this,"gus06.sys.point3d.demo");
	}
	
	
	public Object i() throws Exception
	{return item.i();}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("name")) return NAME;
		if(key.equals("title")) return TITLE;
		if(key.equals("size")) return SIZE;
		
		if(key.equals("config")) return null;
		if(key.equals("mini")) return null;
		
		if(key.equals("keys")) return new String[]{"name","title","size","config","mini"};
		
		throw new Exception("Unknown key: "+key);
	}
}
