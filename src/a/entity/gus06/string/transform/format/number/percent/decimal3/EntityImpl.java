package a.entity.gus06.string.transform.format.number.percent.decimal3;

import a.framework.*;
import java.text.DecimalFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190520";}


	private DecimalFormat df;

	public EntityImpl() throws Exception
	{df = new DecimalFormat("#.000");}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String) return format(""+obj);
		if(obj instanceof Double) return format(""+obj);
		if(obj instanceof Float) return format(""+obj);
		
		if(obj instanceof Object[]) return format((Object[]) obj);
		if(obj instanceof double[]) return format((double[]) obj);
		if(obj instanceof float[]) return format((float[]) obj);
		
		if(obj instanceof Object[][]) return format((Object[][]) obj);
		if(obj instanceof double[][]) return format((double[][]) obj);
		if(obj instanceof float[][]) return format((float[][]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String format(String s)
	{
		double d = Double.parseDouble(s);
		return df.format(d*100)+"%";
	}
	
	
	private String[] format(Object[] s)
	{
		int len = s.length;
		String[] t = new String[len];
		for(int i=0;i<len;i++) t[i] = format(""+s[i]);
		return t;
	}
	
	private String[] format(double[] s)
	{
		int len = s.length;
		String[] t = new String[len];
		for(int i=0;i<len;i++) t[i] = format(""+s[i]);
		return t;
	}
	
	private String[] format(float[] s)
	{
		int len = s.length;
		String[] t = new String[len];
		for(int i=0;i<len;i++) t[i] = format(""+s[i]);
		return t;
	}
	
	
	private String[][] format(Object[][] s)
	{
		int len1 = s.length;
		int len2 = len1>0 ? s[0].length : 0;
		
		String[][] t = new String[len1][len2];
		
		for(int i=0;i<len1;i++)
		for(int j=0;j<len2;j++)
		t[i][j] = format(""+s[i][j]);
		
		return t;
	}
	
	private String[][] format(double[][] s)
	{
		int len1 = s.length;
		int len2 = len1>0 ? s[0].length : 0;
		
		String[][] t = new String[len1][len2];
		
		for(int i=0;i<len1;i++)
		for(int j=0;j<len2;j++)
		t[i][j] = format(""+s[i][j]);
		
		return t;
	}
	
	private String[][] format(float[][] s)
	{
		int len1 = s.length;
		int len2 = len1>0 ? s[0].length : 0;
		
		String[][] t = new String[len1][len2];
		
		for(int i=0;i<len1;i++)
		for(int j=0;j<len2;j++)
		t[i][j] = format(""+s[i][j]);
		
		return t;
	}
}
