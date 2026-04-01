package a.entity.gus06.sys.expression1.apply.op._extract_htmltag_a;

import java.util.regex.Pattern;
import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170328";}


	private Service build;
	private Service readText;
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.string.extract.html.tag.buildpattern");
		readText = Outside.service(this,"gus06.file.read.string.autodetect");
		perform = Outside.service(this,"gus06.string.extract.match.all");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T1((String) obj);
		if(obj instanceof File) return new T1(readText((File) obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String readText(File f) throws Exception
	{return (String) readText.t(f);}
	
	
	
	private class T1 implements T
	{
		private String text;
		public T1(String text) throws Exception
		{this.text = text;}
		
		public Object t(Object obj) throws Exception
		{
			Pattern p = (Pattern) build.t(obj);
			return perform.t(new Object[]{text,p});
		}
	}
}
