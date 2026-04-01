package a.entity.gus06.exception.cause.findfirst;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180106";}


	public Object t(Object obj) throws Exception
	{
		Exception e = (Exception) obj;
		return e.getCause();
	}
}
