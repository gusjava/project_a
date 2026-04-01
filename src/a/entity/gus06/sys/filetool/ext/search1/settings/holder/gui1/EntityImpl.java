package a.entity.gus06.sys.filetool.ext.search1.settings.holder.gui1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20220503";}
	
	public static final String KEY = "terms_builder";
	public static final String INITVALUE = "@code\n\nreturn [input, input._reverse]";

	private Service editor;
	
	private Map map;

	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.sys.mapediting2.key.gusscript1");
		
		editor.v("mapKey",KEY);
		editor.v("initValue",INITVALUE);
	}
	
	
	public Object i() throws Exception
	{return editor.i();}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		editor.p(map);
	}
}