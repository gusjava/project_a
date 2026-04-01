package a.entity.gus06.sys.editor16x16.h.chooser.map;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20250312";}


	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("brighten", Outside.service(this,"gus06.sys.editor16x16.c.brighten"));
		put("darken", Outside.service(this,"gus06.sys.editor16x16.c.darken"));
		put("blackandwhite", Outside.service(this,"gus06.sys.editor16x16.c.blackandwhite"));
		put("grayscale", Outside.service(this,"gus06.sys.editor16x16.c.grayscale"));
		put("discretize1", Outside.service(this,"gus06.sys.editor16x16.c.discretize1"));
		put("discretize2", Outside.service(this,"gus06.sys.editor16x16.c.discretize2"));
		put("discretize3", Outside.service(this,"gus06.sys.editor16x16.c.discretize3"));
		
		put("invert.rgb", Outside.service(this,"gus06.sys.editor16x16.c.invert.rgb"));
		
		put("invert.red", Outside.service(this,"gus06.sys.editor16x16.c.invert.red"));
		put("invert.green", Outside.service(this,"gus06.sys.editor16x16.c.invert.green"));
		put("invert.blue", Outside.service(this,"gus06.sys.editor16x16.c.invert.blue"));
	
		put("invert.brightness", Outside.service(this,"gus06.sys.editor16x16.c.invert.brightness"));
		put("invert.saturation", Outside.service(this,"gus06.sys.editor16x16.c.invert.saturation"));
		put("invert.hue", Outside.service(this,"gus06.sys.editor16x16.c.invert.hue"));
		
		
	}
	
	private void put(String key, T t)
	{map.put(key,t);}
	
	
	public Object g() throws Exception
	{return map;}
}