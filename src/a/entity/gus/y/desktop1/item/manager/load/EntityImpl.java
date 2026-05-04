package a.entity.gus.y.desktop1.item.manager.load;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191122";}

	public static final String KEY_DIR_ITEM_DEF = "dir_item_def";
	public static final String KEY_ITEM_MANAGER = "item_manager";
	
	
	private Service listing;
	
	public EntityImpl() throws Exception
	{listing = Outside.service(this,"gus06.dir.listing0.names0");}

	
	public void p(Object obj) throws Exception
	{
		Map main = (Map) obj;
		
		File dirItemDef = (File) main.get(KEY_DIR_ITEM_DEF);
		V manager = (V) main.get(KEY_ITEM_MANAGER);
		
		String[] ids = (String[]) listing.t(dirItemDef);
		for(String id : ids) manager.v("load",id);
	}
}
