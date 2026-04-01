package a.entity.gus06.sys.editor16x16.r.frame.black;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250416";}
	
	public static final String BLACK = "0-0-0-255";

	
	
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
		{
			boolean isBorder = i==0 || i==w-1 || j==0 || j==h-1;
			newData[i][j] = isBorder ? BLACK : data[i][j];
		}
		return newData;
	}
}