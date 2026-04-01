package a.entity.gus06.url.connect.as.mozilla50;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170113";}
	
	public static final String USERAGENT = "Mozilla/5.0";

	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.url.connect.as");}	
	
	public Object t(Object obj) throws Exception
	{return perform.t(new Object[]{obj,USERAGENT});}
}
