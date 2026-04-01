package a.entity.gus06.sys.javaprojectviewer1.gui1.editor;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20170222";}
	
	public static final String KEY_JAVAFILE = "javafile";


	private Service editor;
	private Map map;
	
	
	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.file.editor.main");
	}
	
	
	
	public Object i() throws Exception
	{return editor.i();}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		editor.p(get(KEY_JAVAFILE));
	}
	
	
	private Object get(String key)
	{
		if(map==null) return null;
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
