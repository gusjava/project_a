package a.entity.gus06.convert.floatarraytocolor;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180408";}


	
	public Object t(Object obj) throws Exception
	{
		float[] n = (float[]) obj;
		if(n.length!=3) throw new Exception("Wrong array length: "+n.length);
		
		int rgb = Color.HSBtoRGB(n[0],n[1],n[2]);
		return new Color(rgb);
	}
}
