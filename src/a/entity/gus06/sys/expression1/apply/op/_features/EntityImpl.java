package a.entity.gus06.sys.expression1.apply.op._features;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151205";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		StringBuffer b = new StringBuffer();
		
		if(obj instanceof E) b.append("E");
		if(obj instanceof F) b.append("F");
		if(obj instanceof G) b.append("G");
		if(obj instanceof H) b.append("H");
		if(obj instanceof I) b.append("I");
		if(obj instanceof P) b.append("P");
		if(obj instanceof R) b.append("R");
		if(obj instanceof S) b.append("S");
		if(obj instanceof T) b.append("T");
		if(obj instanceof V) b.append("V");
		
		if(obj instanceof Runnable) b.append("X");
		
		return b.toString();
	}
}
