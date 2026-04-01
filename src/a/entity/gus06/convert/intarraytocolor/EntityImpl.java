package a.entity.gus06.convert.intarraytocolor;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160907";}


	
	public Object t(Object obj) throws Exception
	{
		int[] n = (int[]) obj;
		if(n.length==3) return new Color(n[0],n[1],n[2]);
		if(n.length==4) return new Color(n[0],n[1],n[2],n[3]);
		
		throw new Exception("Wrong array length: "+n.length);
	}
}