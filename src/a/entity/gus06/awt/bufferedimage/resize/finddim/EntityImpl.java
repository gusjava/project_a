package a.entity.gus06.awt.bufferedimage.resize.finddim;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180324";}
	
	public static final String FIT = "fit";



	private Service toIntArray;
	
	public EntityImpl() throws Exception
	{
		toIntArray = Outside.service(this,"gus06.find.intarray.len2");
	}


	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		BufferedImage image = (BufferedImage) o[0];
		Object width = o[1];
		Object height = o[2];
		Object dim = o[3];
		
		int w = findWidth(image,width);
		int h = findHeight(image,height);
		
		if(width!=null && width.equals(FIT)) w = fitWidth(h,image);
		if(height!=null && height.equals(FIT)) w = fitHeight(h,image);
		
		return findDim(image,dim,w,h);
	}
	
	
	
	
	private int findWidth(BufferedImage image, Object width) throws Exception
	{
		if(width==null) return image.getWidth();
		if(width instanceof Integer) return ((Integer) width).intValue();
		if(width instanceof String) return findWidthFromString(image, (String) width);
		
		throw new Exception("Invalid data type: "+width.getClass().getName());
	}
	
	
	private int findWidthFromString(BufferedImage image, String width) throws Exception
	{
		if(width.endsWith("%")) return percentOf(image.getWidth(),width);
		try{return Integer.parseInt(width);}
		catch(NumberFormatException e){}
		return -1;
	}
	
	
	private int findHeight(BufferedImage image, Object height) throws Exception
	{
		if(height==null) return image.getHeight();
		if(height instanceof Integer) return ((Integer) height).intValue();
		if(height instanceof String) return findHeightFromString(image, (String) height);
		
		throw new Exception("Invalid data type: "+height.getClass().getName());
	}
	
	
	private int findHeightFromString(BufferedImage image, String height) throws Exception
	{
		if(height.endsWith("%")) return percentOf(image.getHeight(),height);
		try{return Integer.parseInt(height);}
		catch(NumberFormatException e){}
		return -1;
	}
	
	
	private int[] findDim(BufferedImage image, Object dim, int w, int h) throws Exception
	{
		if(dim==null) return new int[]{w,h};
		if(dim instanceof List) return (int[]) toIntArray.t(dim);
		if(dim instanceof int[]) return (int[]) dim;
		if(dim instanceof Integer) return findDimFromInteger((Integer) dim);
		if(dim instanceof String) return findDimFromString(image, (String) dim);
			
		throw new Exception("Invalid data type: "+dim.getClass().getName());
	}
	
	
	private int[] findDimFromInteger(Integer dim)
	{
		int d = dim.intValue();
		return new int[]{d,d};
	}
	
	
	private int[] findDimFromString(BufferedImage image, String dim) throws Exception
	{
		if(dim.contains("x"))
		{
			String[] n = dim.split("x");
			int w = findWidth(image,n[0]);
			int h = findHeight(image,n[1]);
			return new int[]{w,h};
		}
		if(dim.startsWith("{") && dim.endsWith("}"))
		{
			double area = toDouble(dim.substring(1,dim.length()-1));
			double h1 = (double) image.getHeight();
			double w1 = (double) image.getWidth();
			double k = Math.sqrt(area/(h1*w1));
			
			int h = (int) (k*h1);
			int w = (int) (k*w1);
			return new int[]{w,h};
		}
		int w = findWidth(image,dim);
		int h = findHeight(image,dim);
		return new int[]{w,h};
	}
	
	
	
	
	private int percentOf(int value, String percent)
	{
		double p = toDouble(percent.replace("%","").trim());
		double pp = p*0.01;
		return (int) (pp*(double) value);
	}
	
	private double toDouble(String s)
	{return Double.parseDouble(s);}
	
	
	private int fitWidth(int h, BufferedImage image) throws Exception
	{
		if(h==-1) throw new Exception("Invalid dimension definition");
		double h1 = (double) image.getHeight();
		double w1 = (double) image.getWidth();
		double h0 = (double) h;
		return (int) (h0/h1*w1);
	}
	
	private int fitHeight(int w, BufferedImage image) throws Exception
	{
		if(w==-1) throw new Exception("Invalid dimension definition");
		double h1 = (double) image.getHeight();
		double w1 = (double) image.getWidth();
		double w0 = (double) w;
		return (int) (w0/w1*h1);
	}
}
