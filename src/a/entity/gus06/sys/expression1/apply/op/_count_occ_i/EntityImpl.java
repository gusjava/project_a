package a.entity.gus06.sys.expression1.apply.op._count_occ_i;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160808";}


	private Service perform;
	private Service readText;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.string.count.substrings_i");
		readText = Outside.service(this,"gus06.file.read.string.generic");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return new T1((String) obj);
		if(obj instanceof File) return new T1((String) readText.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private class T1 implements T
	{
		private String data;
		public T1(String data){this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new String[]{data,(String) obj});}
	}
}
