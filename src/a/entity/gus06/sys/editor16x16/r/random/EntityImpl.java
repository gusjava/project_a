package a.entity.gus06.sys.editor16x16.r.random;

import a.framework.*;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250416";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String[][] data = (String[][]) obj;
		int w = data.length;
		if(w==0) return null;
		int h = data[0].length;
		if(h==0) return null;
		
		List list = new ArrayList();
		for(int i=0;i<w;i++)
		for(int j=0;j<h;j++)
		list.add(data[i][j]);
		
		Collections.shuffle(list);
		
		String[][] newData = new String[w][h];
		for(int i=0;i<w;i++)
		for(int j=0;j<h;j++)
		newData[i][j] = (String) list.remove(0);
		
		return newData;
	}
}