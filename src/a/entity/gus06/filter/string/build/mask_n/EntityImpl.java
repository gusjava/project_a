package a.entity.gus06.filter.string.build.mask_n;

import a.framework.*;

public class EntityImpl implements Entity, T, V {

	public String creationDate() {return "20150526";}


	private Service normalize;
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}
	
	private String normalize(String s) throws Exception
	{return (String) normalize.t(s);}
	
	
	
	private char maskChar = '*';
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("maskChar")) {setMaskChar((String)obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	private void setMaskChar(String value) throws Exception
	{
		if(value.length()!=1) throw new Exception("wrong value: "+value);
		maskChar = value.charAt(0);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return new F1(normalize(s),maskChar);
	}
	
	
	private class F1 implements F
	{
		private String value;
		private int valueLength;
		private char maskChar0;
		
		public F1(String value, char maskChar0)
		{
			this.value = value;
			this.maskChar0 = maskChar0;
			valueLength = value.length();
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = normalize((String) toString.t(obj));
			if(str.length()!=valueLength) return false;
			for(int i=0;i<valueLength;i++)
			{
				char c1 = value.charAt(i);
				char c2 = str.charAt(i);
				if(c1!=maskChar0 && c1!=c2) return false;
			}
			return true;
		}
	}


	
}
