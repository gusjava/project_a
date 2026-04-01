package a.entity.gus06.sys.filetool.ext.compta1.settings.holder;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20260106";}
	

	private Service editor;
	
	private Map map;

	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.map.string.editor2");
		
		Map configMap = new HashMap();
		
		configMap.put("root.display","Input directory");
		configMap.put("struct","root");
		
		editor.v("init",configMap);
	}
	
	
	public Object i() throws Exception
	{return editor.i();}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		editor.p(map);
	}
}