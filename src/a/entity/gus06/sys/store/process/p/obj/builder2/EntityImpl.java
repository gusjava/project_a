package a.entity.gus06.sys.store.process.p.obj.builder2;

import a.framework.*;
import java.util.Map;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140907";}

	
	public static final String TYPE_T = "t";
	public static final String TYPE_R = "r";
	public static final String TYPE_F = "f";
	public static final String TYPE_I = "i";
	public static final String TYPE_G = "g";
	public static final String TYPE_O = "o";
	
	
	
	private Service findObj;
	
	public EntityImpl() throws Exception
	{findObj = Outside.service(this,"gus06.sys.store.obj.find");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);

		String type = (String) o[0];
		Object main = o[1];
		Object input = o[2];
   	
		if(type.equals(TYPE_T))	return build_t(main,input);
		if(type.equals(TYPE_R))	return build_r(main,input);
		if(type.equals(TYPE_F))	return build_f(main,input);
		if(type.equals(TYPE_I))	return build_i(main,input);
		if(type.equals(TYPE_G)) return build_g(main,input);
		if(type.equals(TYPE_O))	return build_o(main,input);
	
		throw new Exception("Invalid type: "+type);
	}
	
	
	
	
	
	private Object build_t(Object main, Object input) throws Exception
	{
		if(!(main instanceof T)) err(main);
		return ((T) main).t(input);
	}
	
	private Object build_r(Object main, Object input) throws Exception
	{
		if(!(main instanceof R)) err(main);
		return ((R) main).r((String)input);
	}
	
	private Object build_f(Object main, Object input) throws Exception
	{
		if(!(main instanceof F)) err(main);
		return ""+((F) main).f(input);
	}
	
	private Object build_i(Object main, Object input) throws Exception
	{
		if(!(main instanceof I)) err(main);
		inject(main,input);
		return ((I) main).i();
	}
	
	private Object build_g(Object main, Object input) throws Exception
	{
		if(!(main instanceof G)) err(main);
		inject(main,input);
		return ((G) main).g();
	}
	
	private Object build_o(Object main, Object input) throws Exception
	{
		inject(main,input);
		return main;
	}
	
	private void err(Object main) throws Exception
	{throw new Exception("Invalid main object type: "+main.getClass().getName());}
	
	
	
	private void inject(Object main, Object input) throws Exception
	{
		if(main instanceof P && input!=null)
		((P)main).p(input);
	}
}
