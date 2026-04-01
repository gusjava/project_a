package a.entity.gus06.sys.expression1.apply.op._extract_regex_a;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160611";}


	private Service readText;
	private Service extract;

	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.generic");
		extract = Outside.service(this,"gus06.string.extract.match.all");
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
		public T1(String data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{
			String regex = (String) obj;
			Pattern p = Pattern.compile(regex,Pattern.DOTALL);
			return extract(data,p);
		}
	}
	
	
	private List extract(String data, Pattern p) throws Exception
	{return (List) extract.t(new Object[]{data,p});}
}
