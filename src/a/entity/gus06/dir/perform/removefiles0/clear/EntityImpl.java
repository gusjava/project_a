package a.entity.gus06.dir.perform.removefiles0.clear;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140906";}


	private Service remove;
	private Service clear;
	
	
	
	public EntityImpl() throws Exception
	{
		remove = Outside.service(this,"gus06.dir.perform.removefiles0");
		clear = Outside.service(this,"gus06.dir.perform.clear");
	}

	
	public void p(Object obj) throws Exception
	{
		remove.p(obj);
		clear.p(obj);
	}
}