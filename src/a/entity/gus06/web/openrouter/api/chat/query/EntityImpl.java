package a.entity.gus06.web.openrouter.api.chat.query;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251126";}
	
	public static final String KEY_PROMPT = "prompt";
	public static final String KEY_MODEL = "model";
	public static final String KEY_APIKEY = "apikey";
	public static final String KEY_STOREDIR = "storedir";
	public static final String KEY_TIMEOUT = "timeout";
	
	private Service call;
	private Service getDeep;
	private Service writeProp;

	public EntityImpl() throws Exception
	{
		call = Outside.service(this,"gus06.web.openrouter.api.chat.call");
		getDeep = Outside.service(this,"gus06.map.deep.get");
		writeProp = Outside.service(this,"gus06.file.write.properties");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map input = (Map) obj;
		
		String prompt = (String) get(input, KEY_PROMPT);
		String model = (String) get(input, KEY_MODEL);
		String apikey = (String) get(input, KEY_APIKEY);
		File storeDir = (File) get(input, KEY_STOREDIR); //can be null
		Integer timeout = (Integer) get(input, KEY_TIMEOUT); //can be null
		
		if(prompt==null) throw new Exception("Invalid null prompt");
		if(model==null) throw new Exception("Invalid null model");
		if(apikey==null) throw new Exception("Invalid null apikey");
		
		if(prompt.isEmpty()) throw new Exception("Invalid empty prompt");
		if(model.isEmpty()) throw new Exception("Invalid empty model");
		if(apikey.isEmpty()) throw new Exception("Invalid empty apikey");

		Map message = new HashMap();
		message.put("role", "user");
		message.put("content", prompt);
		
		Map m = new HashMap();
		m.put("model", model);
		m.put("messages", new Object[] {message});
		
		long t1 = System.currentTimeMillis();
		Map r = (Map) call.t(new Object[]{m,apikey,timeout});
		long t2 = System.currentTimeMillis();
		
		long duration = t2-t1;
		String timestamp = timestamp(t1);
		
		Map output = new HashMap();
		put(output,"duration",""+duration);
		put(output,"timestamp",timestamp);
		put(output,"model",model);
		put(output,"prompt",prompt);
		
		String error = (String) get(r,"error");
		if(error!=null)
		{
			put(output,"error",error);
		}
		else
		{
			String content = (String) getDeep.t(new Object[]{r, "choices.0.message.content"});
			String tokenRequestNb = ""+getDeep.t(new Object[]{r, "usage.prompt_tokens"});
			String tokenResponseNb = ""+getDeep.t(new Object[]{r, "usage.completion_tokens"});
			String estimatedCost = ""+getDeep.t(new Object[]{r, "usage.estimated_cost"});
			
			put(output,"response",content);
			put(output,"prompt_token_nb",tokenRequestNb);
			put(output,"response_token_nb",tokenResponseNb);
			put(output,"estimated_cost",estimatedCost);
		}
		
		if(storeDir!=null)
		{
			File file = new File(storeDir, timestamp+".properties");
			writeProp.p(new Object[]{file, output});
		}
		return output;
	}
	
	private Object get(Map map, String key)
	{return map.containsKey(key) ? map.get(key) : null;}
	
	private void put(Map map, String key, Object value)
	{if(value!=null) map.put(key, value);}
	
	private String timestamp(long t)
	{return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(t));}
}
