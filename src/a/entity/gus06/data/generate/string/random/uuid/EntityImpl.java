package a.entity.gus06.data.generate.string.random.uuid;

import a.framework.*;
import java.util.UUID;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20210610";}
	
	public Object g() throws Exception
	{return UUID.randomUUID().toString();}
}