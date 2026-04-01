package a.entity.gus06.sys.expression1.apply.op._extract_date_en_l;

import a.framework.*;
import java.io.File;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170212";}


	private Service readText;
	private Service extract;
	
	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.generic");
		extract = Outside.service(this,"gus06.string.extract.date.en.l");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return extract.t(obj);
		if(obj instanceof File) return extract.t(readText.t(obj));
		if(obj instanceof JTextComponent) return extract.t(((JTextComponent) obj).getText());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}