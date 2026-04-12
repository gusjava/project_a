package a.entity.gus06.sys.store2.object.builder2;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191125";}

	public static final String KEY_TYPE = "type";
	public static final String KEY_MAIN = "main";
	public static final String KEY_INPUT = "input";
	
	public static final String TYPE_T = "t";
	public static final String TYPE_R = "r";
	public static final String TYPE_F = "f";
	public static final String TYPE_I = "i";
	public static final String TYPE_G = "g";
	public static final String TYPE_O = "o";
	
	
	

	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		R factory = (R) o[1];
		
		String type = get(map,KEY_TYPE,TYPE_I);
		String mainRule = get(map,KEY_MAIN,null);
		String inputRule = get(map,KEY_INPUT,null);
		
		Object main = factory.r(mainRule);
		Object input = factory.r(inputRule);
		
		if(type.equals(TYPE_T)) return typeT((T) main,input);
		if(type.equals(TYPE_R)) return typeR((R) main,input);
		if(type.equals(TYPE_F)) return typeF((F) main,input);
		if(type.equals(TYPE_I)) return typeI((I) main,input);
		if(type.equals(TYPE_G)) return typeG((G) main,input);
		if(type.equals(TYPE_O)) return typeO(main,input);
		return null;
	}
	
	private String get(Map map, String key, String defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return (String) map.get(key);
	}
	
	private Object typeT(T t, Object input) throws Exception
	{
		return t.t(input);
	}
	
	private Object typeR(R r, Object input) throws Exception
	{
		return r.r((String) input);
	}
	
	private Object typeF(F f, Object input) throws Exception
	{
		return Boolean.valueOf(f.f(input));
	}
	
	private Object typeI(I i, Object input) throws Exception
	{
		inject(i,input);
		return i.i();
	}
	
	private Object typeG(G g, Object input) throws Exception
	{
		inject(g,input);
		return g.g();
	}
	
	private Object typeO(Object o, Object input) throws Exception
	{
		inject(o,input);
		return o;
	}
	
	private void inject(Object main, Object input) throws Exception
	{
		if(main instanceof P && input!=null)
		((P)main).p(input);
	}
}
