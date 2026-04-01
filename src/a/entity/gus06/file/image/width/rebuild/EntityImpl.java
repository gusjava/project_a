package a.entity.gus06.file.image.width.rebuild;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200114";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Integer totalwidth = (Integer) o[0];
		Object widthObj = o[1];
		
		return computeWidth(totalwidth,widthObj);
	}
	
	
	
	private int computeWidth(int totalwidth, Object widthObj) throws Exception
	{
		if(widthObj==null) return totalwidth;
		if(widthObj instanceof Integer) return computeWidthAsInt(totalwidth, (int) widthObj);
		if(widthObj instanceof Double) return computeWidthAsDouble(totalwidth, (double) widthObj);
		
		throw new Exception("Invalid widthObj type: "+widthObj.getClass().getName());
	}
	
	private int computeWidthAsInt(int totalwidth, int width) throws Exception
	{
		if(width<0) throw new Exception("Invalid width: "+width);
		return width;
	}
	
	private int computeWidthAsDouble(int totalwidth, double width) throws Exception
	{
		if(width<0 || width>1) throw new Exception("Invalid width: "+width);
		return (int) (width*totalwidth);
	}
}
