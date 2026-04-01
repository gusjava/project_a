package a.entity.gus06.sys.expression1.apply.op._extract_alphanum_k;

import a.framework.*;
import java.util.regex.Pattern;
import java.io.File;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190427";}

	public static final String KEY = "alphanum";


	private Service readText;
	private Service regexFromRule;
	private Service extract;
	private Pattern p;
	
	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.generic");
		regexFromRule = Outside.service(this,"gus06.string.transform.regexp.fromrule");
		extract = Outside.service(this,"gus06.string.extract.match.full");
		
		String regex = (String) regexFromRule.r(KEY);
		p = Pattern.compile(regex,Pattern.DOTALL);
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return extract(obj);
		if(obj instanceof File) return extract(readText.t(obj));
		if(obj instanceof JTextComponent) return extract(((JTextComponent) obj).getText());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object extract(Object data) throws Exception
	{return extract.t(new Object[]{data,p});}
}
