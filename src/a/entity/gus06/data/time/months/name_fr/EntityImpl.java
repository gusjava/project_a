package a.entity.gus06.data.time.months.name_fr;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160614";}

	public static final String[] DATA = new String[]{
		"Janvier",
		"Février",
		"Mars",
		"Avril",
		"Mai",
		"Juin",
		"Juillet",
		"Août",
		"Septembre",
		"Octobre",
		"Novembre",
		"Décembre"
	};
	
	public Object g() throws Exception
	{return DATA;}
}
