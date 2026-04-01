package a.entity.gus06.data.perform.half;

import a.framework.*;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160816";}


	private Service halfFunction;
	private Service halfImage;
	
	public EntityImpl() throws Exception
	{
		halfFunction = Outside.service(this,"gus06.feature.op.function.half");
		halfImage = Outside.service(this,"gus06.awt.bufferedimage.resize1.half");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof H) return halfFunction.t(obj);
		if(obj instanceof BufferedImage) return halfImage.t(obj);
		
		if(obj instanceof Integer) return Double.valueOf(toInt(obj)*0.5);
		if(obj instanceof Double) return Double.valueOf(toDouble(obj)*0.5);
		if(obj instanceof Float) return Double.valueOf(toFloat(obj)*0.5);
		if(obj instanceof Long) return Double.valueOf(toLong(obj)*0.5);
		
		if(obj instanceof int[])
		{
			int[] n = (int[]) obj;
			double[] n1 = new double[n.length];
			for(int i=0;i<n.length;i++) n1[i] = n[i]*0.5;
			return n1;
		}
		if(obj instanceof double[])
		{
			double[] n = (double[]) obj;
			double[] n1 = new double[n.length];
			for(int i=0;i<n.length;i++) n1[i] = n[i]*0.5;
			return n1;
		}
		if(obj instanceof float[])
		{
			float[] n = (float[]) obj;
			double[] n1 = new double[n.length];
			for(int i=0;i<n.length;i++) n1[i] = n[i]*0.5;
			return n1;
		}
		if(obj instanceof long[])
		{
			long[] n = (long[]) obj;
			double[] n1 = new double[n.length];
			for(int i=0;i<n.length;i++) n1[i] = n[i]*0.5;
			return n1;
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private int toInt(Object obj)
	{return ((Integer) obj).intValue();}
	
	private double toDouble(Object obj)
	{return ((Double) obj).doubleValue();}
	
	private float toFloat(Object obj)
	{return ((Float) obj).floatValue();}
	
	private long toLong(Object obj)
	{return ((Long) obj).longValue();}
}
