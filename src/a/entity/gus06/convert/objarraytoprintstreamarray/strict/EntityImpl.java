package a.entity.gus06.convert.objarraytoprintstreamarray.strict;

import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160310";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		PrintStream[] yy = new PrintStream[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof PrintStream)) return null;
			yy[i] = (PrintStream) oo[i];
		}
		return yy;
	}
}
