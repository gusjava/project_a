package a.entity.gus06.sys.expression1.apply.op._htmltag_wrap;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190709";}



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
		private String text;
		public T1(String text) {this.text = text;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return insideTag(text,"null");
			if(obj instanceof String) return insideTag(text,""+obj);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	private String insideTag(String text, String tag)
	{
		String end = tag.split(" ",2)[0];
		return "<"+tag+">"+text+"</"+end+">";
	}
	
}