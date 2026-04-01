package a.entity.gus06.filter.string.build.length.intfilter;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}

	
	private Service intF;
	private Service toString;

	public EntityImpl() throws Exception
	{
		intF = Outside.service(this,"gus06.filter.string.build.number.integer.filter1");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}


	public Object t(Object obj) throws Exception
	{
		F filter = (F) intF.t(obj);
		return new Filter(filter);
	}



	private class Filter implements F
	{
		private F filter;
		public Filter(F filter)
		{this.filter = filter;}

		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			int length = ((String) toString.t(obj)).length();
			return filter.f(""+length);
		}
	}
}
