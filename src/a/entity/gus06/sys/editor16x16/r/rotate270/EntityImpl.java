package a.entity.gus06.sys.editor16x16.r.rotate270;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250417";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String[][] data = (String[][]) obj;
		int w = data.length;
		if(w==0) return null;
		int h = data[0].length;
		if(h==0) return null;
		if(h!=w) return null;
		
		String[][] newData = new String[w][h];
		for(int i=0;i<h;i++)
		for(int j=0;j<h;j++)
		newData[i][j] = data[j][h-i-1];
		
		return newData;
	}
}