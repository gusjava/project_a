package a.entity.gus06.convert.htot;

import a.framework.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151123";}
	
	
	
	private Service toColor;
	private Service toImage;
	
	public EntityImpl() throws Exception
	{
		toColor = Outside.service(this,"gus06.awt.color.apply.h.torgb");
		toImage = Outside.service(this,"gus06.awt.bufferedimage.transform.color.h.torgb");
	}


	public Object t(Object obj) throws Exception
	{return new T1((H) obj);}
	

	
	
	private class T1 implements T
	{
		private H h;
		public T1(H h) {this.h = h;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return null;
			
			if(obj instanceof Number)		return applyH(h,(Number) obj);
			if(obj instanceof String)		return applyH(h,(String) obj);
			if(obj instanceof double[][])		return applyH(h,(double[][]) obj);
			if(obj instanceof double[])		return applyH(h,(double[]) obj);
			if(obj instanceof Color)		return applyH(h,(Color) obj);
			if(obj instanceof BufferedImage)	return applyH(h,(BufferedImage) obj);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	private Object applyH(H h, Number d) throws Exception
	{
		return Double.valueOf(h.h(d.doubleValue()));
	}
	
	private Object applyH(H h, String s) throws Exception
	{
		return Double.valueOf(h.h(Double.parseDouble(s)));
	}
	
	private Object applyH(H h, double[][] d) throws Exception
	{
		int x = d.length;
		int y = x>0 ? d[0].length : 0;
		
		double[][] d1 = new double[x][y];
		for(int i=0;i<x;i++) for(int j=0;j<y;j++) d1[i][j] = h.h(d[i][j]);
		return d1;
	}
	
	private Object applyH(H h, double[] d) throws Exception
	{
		int x = d.length;
		
		double[] d1 = new double[x];
		for(int i=0;i<x;i++) d1[i] = h.h(d[i]);
		return d1;
	}
	
	private Object applyH(H h, Color color) throws Exception
	{
		return toColor.t(new Object[]{color,h});
	}
	
	private Object applyH(H h, BufferedImage img) throws Exception
	{
		return toImage.t(new Object[]{img,h});
	}
}
