package a.entity.gus06.filter.string.build.mask2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160404";}


	private Service validator;
	private Service toString;

	public EntityImpl() throws Exception
	{
		validator = Outside.service(this,"gus06.filter.string.build.mask2.validator");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return new F1(s);
	}
	
	
	private class F1 implements F
	{
		private String value;
		private int valueLength;
		
		public F1(String value)
		{
			this.value = value;
			valueLength = value.length();
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			if(str.length()!=valueLength) return false;
			
			for(int i=0;i<valueLength;i++)
			{
				char c1 = value.charAt(i);
				char c2 = str.charAt(i);
				if(!isValidChar(c1,c2)) return false;
			}
			return true;
		}
	}
	
	private boolean isValidChar(char c1, char c2) throws Exception
	{return validator.f(new char[]{c1,c2});}
}
