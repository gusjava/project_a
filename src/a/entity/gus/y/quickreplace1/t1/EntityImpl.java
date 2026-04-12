package a.entity.gus.y.quickreplace1.t1;

import a.framework.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240714";}

	private Service byRegex;
	private Service byPosition;

	public EntityImpl() throws Exception
	{
		byRegex = Outside.service(this,"gus.y.quickreplace1.t1.byregex");
		byPosition = Outside.service(this,"gus.y.quickreplace1.t1.byregex");
	}
	
	public Object t(Object obj) throws Exception
	{
		R info = (R) obj;
		String selected = (String) info.r("selected");
		
		if(selected==null || selected.equals(""))
			return byPosition.t(obj);
		return byRegex.t(obj);
	}
}