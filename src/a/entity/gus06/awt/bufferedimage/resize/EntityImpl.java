package a.entity.gus06.awt.bufferedimage.resize;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141114";}
	
	public static final String KEY_WIDTH = "width";
	public static final String KEY_HEIGHT = "height";
	public static final String KEY_DIM = "dim";
	public static final String KEY_BACKGROUND = "background";
	public static final String KEY_MODE = "mode";
	
	public static final Color DEFAULT_BACKGROUND = Color.BLACK;
	public static final String DEFAULT_MODE = "fit";


	private Service perform;
	private Service findDim;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.awt.bufferedimage.resize.perform");
		findDim = Outside.service(this,"gus06.awt.bufferedimage.resize.finddim");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		BufferedImage image = (BufferedImage) o[0];
		Map map = toMap(o[1]);
		
		Object width = get(map,KEY_WIDTH);
		Object height = get(map,KEY_HEIGHT);
		Object dim = get(map,KEY_DIM);
		Object background = get(map,KEY_BACKGROUND);
		Object mode = get(map,KEY_MODE);
		
		int[] d = (int[]) findDim.t(new Object[]{image,width,height,dim});
		Color c = background!=null ? (Color) background : DEFAULT_BACKGROUND;
		String m = mode!=null ? (String) mode : DEFAULT_MODE;
		
		return perform.t(new Object[]{image,d,c,m});
	}
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private Map toMap(Object obj) throws Exception
	{
		if(obj instanceof Map) return (Map) obj;
		if(obj instanceof Integer) return dimToMap(obj);
		if(obj instanceof String) return dimToMap(obj);
		if(obj instanceof int[]) return dimToMap(obj);
		if(obj instanceof List) return dimToMap(obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Map dimToMap(Object obj)
	{
		Map map = new HashMap();
		map.put(KEY_DIM,obj);
		return map;
	}
}
