package a.entity.gus06.convert.stringtofloatarray;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180113";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return array((String) obj);
	}
	
	
	private float[] array(String s)
	{
		String[] n = s.split(" ");
		float[] a = new float[n.length];
		for(int i=0;i<n.length;i++) a[i] = d_(n[i]);
		return a;
	}


	private float d_(String s)
	{return Float.parseFloat(s);}
}
