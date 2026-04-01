package a.entity.gus06.sys.expression1.apply.op._prefs_user;

import a.framework.*;
import java.util.prefs.Preferences;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180425";}


	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return prefs((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Preferences prefs(String name) throws Exception
	{
		return Preferences.userRoot().node(name);
	}
}
