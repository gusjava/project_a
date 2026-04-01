package a.entity.gus06.sys.expression1.apply.op._detect_lang;

import a.framework.*;
import java.io.File;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160719";}
	
	private Service perform;
	private Service readText;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.langdetect1.engine");
		readText = Outside.service(this,"gus06.file.read.string.generic");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return perform.t(obj);
		if(obj instanceof StringBuffer) return perform.t(obj.toString());
		if(obj instanceof StringBuilder) return perform.t(obj.toString());
		if(obj instanceof JTextComponent) return perform.t(((JTextComponent) obj).getText());
		if(obj instanceof File) return perform.t(readText.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}