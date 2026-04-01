package a.entity.gus06.sys.expression1.apply.op._extract_in_angle_f;

import a.framework.*;
import java.util.regex.Pattern;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191028";}

	public static final Pattern P = Pattern.compile("<([^>]*)>",Pattern.DOTALL);


	private Service readText;
	private Service extract;
	
	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.generic");
		extract = Outside.service(this,"gus06.string.extract.match.first.g1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return extract(obj);
		if(obj instanceof File) return extract(readText.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object extract(Object data) throws Exception
	{return extract.t(new Object[]{data,P});}
}
