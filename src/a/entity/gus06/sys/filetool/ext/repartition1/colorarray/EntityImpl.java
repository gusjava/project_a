package a.entity.gus06.sys.filetool.ext.repartition1.colorarray;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151020";}


	private Service findColor;
	
	public EntityImpl() throws Exception
	{findColor = Outside.service(this,"gus06.convert.stringtocolor");}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		List<Color> list = new ArrayList<>();
		
		int n = 0;
		while(map.containsKey("color."+n))
		{
			String value = (String) map.get("color."+n);
			Color color = (Color) findColor.t(value);
			list.add(color);
			n++;
		}
		Color[] array = list.toArray(new Color[list.size()]);
		return array;
	}
}
