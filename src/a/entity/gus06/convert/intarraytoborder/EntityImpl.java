package a.entity.gus06.convert.intarraytoborder;

import a.framework.*;
import javax.swing.BorderFactory;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160908";}


	
	public Object t(Object obj) throws Exception
	{
		int[] n = (int[]) obj;
		
		if(n.length==1) return BorderFactory.createEmptyBorder(n[0],n[0],n[0],n[0]);
		if(n.length==2) return BorderFactory.createEmptyBorder(n[0],n[1],n[0],n[1]);
		if(n.length==4) return BorderFactory.createEmptyBorder(n[0],n[1],n[2],n[3]);
		
		throw new Exception("Wrong array length: "+n.length);
	}
}
