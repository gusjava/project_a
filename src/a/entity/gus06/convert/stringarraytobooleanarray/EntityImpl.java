package a.entity.gus06.convert.stringarraytobooleanarray;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20171014";}


	private Service stringToBoolean;

	public EntityImpl() throws Exception
	{
		stringToBoolean = Outside.service(this,"gus06.convert.stringtoboolean");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return convert((String[]) obj);
	}
	
	
	private boolean[] convert(String[] n) throws Exception
	{
		boolean[] a = new boolean[n.length];
		for(int i=0;i<n.length;i++) a[i] = b(n[i]);
		return a;
	}

	private boolean b(String s) throws Exception
	{return stringToBoolean.f(s);}
}
