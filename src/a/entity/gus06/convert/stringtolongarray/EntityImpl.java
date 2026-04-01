package a.entity.gus06.convert.stringtolongarray;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180113";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return array((String) obj);
	}
	
	
	private long[] array(String s)
	{
		String[] n = s.split(" ");
		long[] a = new long[n.length];
		for(int i=0;i<n.length;i++) a[i] = long_(n[i]);
		return a;
	}


	private long long_(String s)
	{return Long.parseLong(s);}
}
