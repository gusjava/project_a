package a.entity.gus06.data.time.days.name_en;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160614";}

	public static final String[] DATA = new String[]{
		"Monday",
		"Thuesday",
		"Wednesday",
		"Thursday",
		"Friday",
		"Saturday",
		"Sunday"
	};
	
	public Object g() throws Exception
	{return DATA;}
}
