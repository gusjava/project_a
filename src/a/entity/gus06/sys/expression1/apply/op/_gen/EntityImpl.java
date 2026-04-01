package a.entity.gus06.sys.expression1.apply.op._gen;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160730";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Integer) return new T1(toInt(obj));
		if(obj instanceof String) return new T1(toInt(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private int length;
		public T1(int length) {this.length = length;}
		
		public Object t(Object obj) throws Exception
		{return handleObj(obj, length);}
	}
	
	
	
	
	private String handleObj(Object obj, int length) throws Exception
	{
		if(obj instanceof String) return handleString((String) obj, length);
		if(obj instanceof G) return handleG((G) obj, length);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String handleString(String alphabet, int length)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<length;i++) b.append(randomChar(alphabet));
		return b.toString();
	}
	
	private String handleG(G g, int length) throws Exception
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<length;i++) 
		{
			String s = (String) g.g();
			b.append(s);
		}
		return b.toString();
	}
	
	
	
	private char randomChar(String alphabet)
	{
		int r = random(alphabet.length());
		return alphabet.charAt(r);
	}
	
	private int random(int limit)
	{return (int) (Math.random()*limit);}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}