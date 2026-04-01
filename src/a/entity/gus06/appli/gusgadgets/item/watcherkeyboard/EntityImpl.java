package a.entity.gus06.appli.gusgadgets.item.watcherkeyboard;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, I, R {

	public String creationDate() {return "20160605";}

	public static final String NAME = "watcherkeyboard";
	public static final String TITLE = "Keyboard Watcher";
	public static final Dimension SIZE = new Dimension(250,50);


	private Service item;

	public EntityImpl() throws Exception
	{
		item = Outside.service(this,"gus06.jna.keyboard.buffer.gadget1");
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
