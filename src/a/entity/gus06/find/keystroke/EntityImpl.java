package a.entity.gus06.find.keystroke;

import a.framework.*;
import javax.swing.KeyStroke;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140728";}


	private Service stringToKeystroke;

	public EntityImpl() throws Exception
	{stringToKeystroke = Outside.service(this,"gus06.convert.stringtokeystroke");}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof KeyStroke) return obj;
		if(obj instanceof String) return stringToKeystroke.t(obj);
		if(obj instanceof Integer) return stringToKeystroke.t(""+obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
