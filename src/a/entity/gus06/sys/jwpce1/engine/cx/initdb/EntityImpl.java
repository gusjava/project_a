package a.entity.gus06.sys.jwpce1.engine.cx.initdb;

import a.framework.*;

public class EntityImpl implements Entity, P, G {

	public String creationDate() {return "20250721";}

	public static final String STRUCT_LAST_UPDATE = "2025-07-21 21:00:00";


	private Service initDico;

	public EntityImpl() throws Exception
	{
		initDico = Outside.service(this,"gus06.sys.jwpce1.engine.cx.initdb.edict");
	}

	public Object g() throws Exception
	{return STRUCT_LAST_UPDATE;}
	
	
	public void p(Object obj) throws Exception
	{
		initDico.p(obj);
	}
}