package a.entity.gus06.sys.editor16x16.r.flip.horizontally;

import a.framework.*;
import java.awt.Color;

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
		
		String[][] newData = new String[w][h];
		for(int i=0;i<w;i++)
		for(int j=0;j<h;j++)
		newData[i][j] = data[w-i-1][j];
		
		return newData;
	}
}