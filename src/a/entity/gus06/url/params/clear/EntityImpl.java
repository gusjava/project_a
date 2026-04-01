package a.entity.gus06.url.params.clear;

import a.framework.*;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190918";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.url.string.params.clear");
	}

	public Object t(Object obj) throws Exception
	{
		String result = (String) perform.t(obj);
		return new URL(result);
	}
}
