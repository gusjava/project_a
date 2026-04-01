package a.entity.gus06.exception.cause.findlast;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180106";}


	public Object t(Object obj) throws Exception
	{
		Throwable e = (Throwable) obj;
		while(e.getCause()!=null) e = e.getCause();
		return e;
	}
}
