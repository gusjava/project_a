package a.entity.gus06.sys.translator1.builder.date;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160304";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		String s = (String) o[1];
		
		return new T1(t,s);
	}
	
	
	private class T1 implements T
	{
		private T t;
		private String pattern;
		
		public T1(T t, String pattern)
		{
			this.t = t;
			this.pattern = pattern;
		}
		
		public Object t(Object obj) throws Exception
		{
			Date date = (Date) obj;
			String pattern_ling = (String) t.t(pattern);
			SimpleDateFormat sdf = new SimpleDateFormat(pattern_ling);
			return sdf.format(date);
		}
	}
}
