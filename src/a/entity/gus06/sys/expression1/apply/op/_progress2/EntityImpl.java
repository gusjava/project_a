package a.entity.gus06.sys.expression1.apply.op._progress2;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191127";}

	
	private Service factory;
	
	public EntityImpl() throws Exception
	{factory = Outside.service(this,"factory#gus.swing.progressbar.progress2a");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Integer) return build((Integer) obj);
		if(obj instanceof List) return build((List) obj);
		if(obj instanceof Set) return build((Set) obj);
		if(obj instanceof Map) return build((Map) obj);
		if(obj instanceof Object[]) return build((Object[]) obj);
		
		if(obj instanceof int[]) return build((int[]) obj);
		if(obj instanceof short[]) return build((short[]) obj);
		if(obj instanceof long[]) return build((long[]) obj);
		if(obj instanceof double[]) return build((double[]) obj);
		if(obj instanceof float[]) return build((float[]) obj);
		if(obj instanceof boolean[]) return build((boolean[]) obj);
		if(obj instanceof char[]) return build((char[]) obj);
		if(obj instanceof byte[]) return build((byte[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private Object build(Integer n) throws Exception
	{return build(n.intValue());}
	
	private Object build(List l) throws Exception
	{return build(l.size());}
	
	private Object build(Set l) throws Exception
	{return build(l.size());}
	
	private Object build(Map l) throws Exception
	{return build(l.size());}
	
	private Object build(Object[] oo) throws Exception
	{return build(oo.length);}
	
	private Object build(int[] oo) throws Exception
	{return build(oo.length);}
	
	private Object build(short[] oo) throws Exception
	{return build(oo.length);}
	
	private Object build(long[] oo) throws Exception
	{return build(oo.length);}
	
	private Object build(double[] oo) throws Exception
	{return build(oo.length);}
	
	private Object build(float[] oo) throws Exception
	{return build(oo.length);}
	
	private Object build(boolean[] oo) throws Exception
	{return build(oo.length);}
	
	private Object build(char[] oo) throws Exception
	{return build(oo.length);}
	
	private Object build(byte[] oo) throws Exception
	{return build(oo.length);}
	
	
	
	private Object build(int n) throws Exception
	{
		V progress = (V) factory.g();
		progress.v("size",""+n);
		return progress;
	}
}
