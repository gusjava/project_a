package a.entity.gus06.data.time.days.name_ja;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160614";}

	public static final String[] DATA = new String[]{
		"\u6708\u66dc\u65e5",
		"\u706b\u66dc\u65e5",
		"\u6c34\u66dc\u65e5",
		"\u6728\u66dc\u65e5",
		"\u91d1\u66dc\u65e5",
		"\u571f\u66dc\u65e5",
		"\u65e5\u66dc\u65e5"
	};
	
	public Object g() throws Exception
	{return DATA;}
}
