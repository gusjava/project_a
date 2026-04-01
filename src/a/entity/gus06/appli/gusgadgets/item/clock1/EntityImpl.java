package a.entity.gus06.appli.gusgadgets.item.clock1;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, I, R {

	public String creationDate() {return "20160605";}

	public static final String NAME = "clock1";
	public static final String TITLE = "Analog blue clock";
	public static final Dimension SIZE = new Dimension(160,160);


	private Service item;

	public EntityImpl() throws Exception
	{
		item = Outside.service(this,"gus06.time.clock.gui.analogclock1");
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
