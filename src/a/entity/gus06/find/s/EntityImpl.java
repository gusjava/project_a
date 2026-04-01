package a.entity.gus06.find.s;

import a.framework.*;
import java.io.FileFilter;
import javax.swing.AbstractButton;
import javax.swing.JTextField;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161216";}


	private Service buttonToS;
	private Service fieldToS;

	public EntityImpl() throws Exception
	{
		buttonToS = Outside.service(this,"gus06.convert.buttontos");
		fieldToS = Outside.service(this,"gus06.convert.textfieldtos");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof S) return obj;
		if(obj instanceof AbstractButton) return buttonToS.t(obj);
		if(obj instanceof JTextField) return fieldToS.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
