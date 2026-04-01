package a.entity.gus06.sys.expression1.apply.op._star_replace_seq;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160304";}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T1((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private String line;
		public T1(String line) {this.line = line;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return "";
			if(obj instanceof String) return replace(line,(String) obj);
			if(obj instanceof String[]) return replace(line,(String[]) obj);
			if(obj instanceof List) return replace(line,(List) obj);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	private String replace(String line, String s)
	{
		if(s==null || s.equals("")) return "";
		return replace(line,s.split(";"));
	}
	
	private String replace(String line, String[] nn)
	{
		if(nn==null || nn.length==0) return "";
		StringBuffer b = new StringBuffer();
		for(String n:nn) b.append(line.replace("*",n));
		return b.toString();
	}
	
	private String replace(String line, List nn)
	{
		if(nn==null || nn.isEmpty()) return "";
		StringBuffer b = new StringBuffer();
		for(Object n:nn) b.append(line.replace("*",""+n));
		return b.toString();
	}
}
