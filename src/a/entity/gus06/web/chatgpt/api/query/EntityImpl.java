package a.entity.gus06.web.chatgpt.api.query;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250112";}
	
	public static final String KEY_PROMPT = "prompt";
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
		
		String prompt = (String) input.get(KEY_PROMPT);
		String model = (String) input.get(KEY_MODEL);
		String apikey = (String) input.get(KEY_APIKEY);
		
		Map message = new HashMap();
		message.put("role", "user");
		message.put("content", prompt);
		
		Map m = new HashMap();
		m.put("model", model);
		m.put("messages", new Object[] {message});
		
		Map output = (Map) call.t(new Object[]{m,apikey});
		
		String content = (String) getDeep.t(new Object[]{output, OUTPUT_PATH});
		return content;
	}
}