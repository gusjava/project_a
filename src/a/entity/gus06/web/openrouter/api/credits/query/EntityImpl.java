package a.entity.gus06.web.openrouter.api.credits.query;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251203";}

	public static final String KEY_APIKEY = "apikey";
	public static final String OUTPUT_PATH = "data";

	private Service call;

	public EntityImpl() throws Exception
	{
		call = Outside.service(this, "gus06.web.openrouter.api.credits.call");
	}

	public Object t(Object obj) throws Exception
	{
		Map input = (Map) obj;
		String apikey = (String) input.get(KEY_APIKEY);
		Map output = (Map) call.t(apikey);
		return output.get(OUTPUT_PATH);
	}
}