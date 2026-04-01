package a.entity.gus06.sys.editor16x16.r.random2;

import a.framework.*;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250417";}
	
	public static final String TRANSPARENT = "255-255-255-0";

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String[][] data = (String[][]) obj;
		int w = data.length;
		if(w==0) return null;
		int h = data[0].length;
		if(h==0) return null;
		
		Set colors = new HashSet();
		for(int i=0;i<w;i++)
		for(int j=0;j<h;j++)
		colors.add(data[i][j]);
		
		colors.remove(TRANSPARENT);
		Map colorMap = buildRandomMap(colors);
		
		String[][] newData = new String[w][h];
		for(int i=0;i<w;i++)
		for(int j=0;j<h;j++)
		{
			String c = data[i][j];
			newData[i][j] = convertColor(colorMap, c);
		}
		return newData;
	}
	
	
	private String convertColor(Map map, String c)
	{
		if(c.equals(TRANSPARENT)) return c;
		return (String) map.get(c);
	}
	
	private Map buildRandomMap(Set colors)
	{
		List list = new ArrayList(colors);
		Collections.shuffle(list);
		Map map = new HashMap();
		Iterator it = colors.iterator();
		while(it.hasNext()) map.put(it.next(), list.remove(0));
		return map;
	}
}