package a.entity.gus06.convert.intarraytorectangle;

import a.framework.*;
import java.awt.Rectangle;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170306";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		int[] oo = (int[]) obj;
		int number = oo.length;
		
		if(number==2) return new Rectangle(0,0,oo[0],oo[1]);
		if(number==4) return new Rectangle(oo[0],oo[1],oo[2],oo[3]);
		
		throw new Exception("Wrong data number: "+number);
	}
}