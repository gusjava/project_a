package a.entity.gus06.convert.stringtodoublearray;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150928";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return array((String) obj);
	}
	
	
	private double[] array(String s)
	{
		String[] n = s.split(" ");
		double[] a = new double[n.length];
		for(int i=0;i<n.length;i++) a[i] = d_(n[i]);
		return a;
	}


	private double d_(String s)
	{return Double.parseDouble(s);}
}
