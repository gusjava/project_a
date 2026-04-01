package a.entity.gus06.sys.expression1.apply.op._iso3166_countrycodes;

import a.framework.*;
import java.util.Locale;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160414";}

	public static final String T = "constant";


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return Locale.getISOCountries();
	}
}
