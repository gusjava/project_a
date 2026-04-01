package a.entity.gus06.time.date.add.years.fromnow;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191106";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.time.date.add.years");}

	public Object t(Object obj) throws Exception
	{
		Integer n = (Integer) obj;
		return perform.t(new Object[]{new Date(),n});
	}
}
