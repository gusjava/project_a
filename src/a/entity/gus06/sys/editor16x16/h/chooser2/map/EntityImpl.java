package a.entity.gus06.sys.editor16x16.h.chooser2.map;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20250416";}


	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("flip_vertically", Outside.service(this,"gus06.sys.editor16x16.r.flip.vertically"));
		put("flip_horizontally", Outside.service(this,"gus06.sys.editor16x16.r.flip.horizontally"));
		put("flip_diagonally", Outside.service(this,"gus06.sys.editor16x16.r.flip.diagonally"));
		put("frame_black", Outside.service(this,"gus06.sys.editor16x16.r.frame.black"));
		put("frame_white", Outside.service(this,"gus06.sys.editor16x16.r.frame.white"));
		put("frame_transparent", Outside.service(this,"gus06.sys.editor16x16.r.frame.transparent"));
		put("random", Outside.service(this,"gus06.sys.editor16x16.r.random"));
		put("random2", Outside.service(this,"gus06.sys.editor16x16.r.random2"));
		
		put("rotate90", Outside.service(this,"gus06.sys.editor16x16.r.rotate90"));
		put("rotate180", Outside.service(this,"gus06.sys.editor16x16.r.rotate180"));
		put("rotate270", Outside.service(this,"gus06.sys.editor16x16.r.rotate270"));
		
		put("reduce", Outside.service(this,"gus06.sys.editor16x16.r.scale.reduce"));
		put("part", Outside.service(this,"gus06.sys.editor16x16.r.scale.part"));
	}
	
	private void put(String key, T t)
	{map.put(key,t);}
	
	
	public Object g() throws Exception
	{return map;}
}