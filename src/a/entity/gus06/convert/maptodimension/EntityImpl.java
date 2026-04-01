package a.entity.gus06.convert.maptodimension;

import a.framework.*;
import java.awt.Dimension;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250825";}


	public static final String KEY_SIZE = "size";
	public static final String KEY_WIDTH = "width";
	public static final String KEY_HEIGHT = "height";
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return dim((Map) obj);
	}
	
	
	private Dimension dim(Map map) throws Exception
	{
		if(map.containsKey(KEY_SIZE)) 
			return buildDim(map.get(KEY_SIZE));
			
		Object w = map.get(KEY_WIDTH);
		Object h = map.get(KEY_HEIGHT);
		return new Dimension(i_(w),i_(h));
	}
	
	
	
	private Dimension buildDim(Object obj) throws Exception
	{
		if(obj instanceof Dimension) return (Dimension) obj;
		if(obj instanceof int[]) return intArrayToDim((int[]) obj);
		if(obj instanceof List) return listToDim((List) obj);
		if(obj instanceof String) return stringToDim((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Dimension intArrayToDim(int[] v)
	{return new Dimension(v[0],v[1]);}
	
	private Dimension listToDim(List list)
	{return new Dimension(i_(list.get(0)),i_(list.get(1)));}
	
	private Dimension stringToDim(String size)
	{
		String[] n = size.split(" ");
		return new Dimension(i_(n[0]),i_(n[1]));
	}

	private int i_(Object obj)
	{return Integer.parseInt(""+obj);}
}