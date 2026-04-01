package a.entity.gus06.web.chatgpt.api.query2;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250530";}
	
	public static final String KEY_MESSAGES = "messages";
	public static final String KEY_MODEL = "model";
	public static final String KEY_APIKEY = "apikey";
	
	public static final String OUTPUT_PATH = "choices.0.message.content";

	private Service call;
	private Service getDeep;

	public EntityImpl() throws Exception
	{
		call = Outside.service(this,"gus06.web.chatgpt.api.call");
		getDeep = Outside.service(this,"gus06.map.deep.get");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map input = (Map) obj;
		
		List messages = (List) input.get(KEY_MESSAGES);
		String model = (String) input.get(KEY_MODEL);
		String apikey = (String) input.get(KEY_APIKEY);
		
		Map m = new HashMap();
		m.put(KEY_MODEL, model);
		m.put(KEY_MESSAGES, messages);
		
		Map output = (Map) call.t(new Object[]{m,apikey});
		
		String content = (String) getDeep.t(new Object[]{output, OUTPUT_PATH});
		return content;
	}
}