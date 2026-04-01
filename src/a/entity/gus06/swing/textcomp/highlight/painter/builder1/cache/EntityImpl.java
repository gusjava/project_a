package a.entity.gus06.swing.textcomp.highlight.painter.builder1.cache;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180223";}


	private Service colorToString;
	private Service buildPainter;

	private Map map;
	

	public EntityImpl() throws Exception
	{
		colorToString = Outside.service(this,"gus06.awt.color.tostring");
		buildPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.builder1");
		
		map = new HashMap();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Color color = (Color) obj;
		String key = (String) colorToString.t(color);
		
		if(!map.containsKey(key))
		{
			Object painter = buildPainter.t(color);
			map.put(key,painter);
		}
		return map.get(key);
	}
}
