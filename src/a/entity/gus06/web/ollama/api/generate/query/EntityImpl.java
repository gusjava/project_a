package a.entity.gus06.web.ollama.api.generate.query;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251206";}

	public static final String KEY_PROMPT = "prompt";
	public static final String KEY_MODEL = "model";
	public static final String KEY_STOREDIR = "storedir";

	private Service call;
	private Service getDeep;
	private Service writeProp;

	public EntityImpl() throws Exception
	{
		call = Outside.service(this,"gus06.web.ollama.api.generate.call");
		getDeep = Outside.service(this,"gus06.map.deep.get");
		writeProp = Outside.service(this,"gus06.file.write.properties");
	}

	public Object t(Object obj) throws Exception
	{
		Map input = (Map) obj;

		String prompt = (String) get(input, KEY_PROMPT);
		String model = (String) get(input, KEY_MODEL);
		File storeDir = (File) get(input, KEY_STOREDIR);

		if(prompt==null || prompt.isEmpty()) throw new Exception("Invalid prompt");
		if(model==null || model.isEmpty()) throw new Exception("Invalid model");

		Map m = new HashMap();
		m.put("model", model);
		m.put("prompt", prompt);
		m.put("stream", false);

		long t1 = System.currentTimeMillis();
		Map r = (Map) call.t(m);
		long t2 = System.currentTimeMillis();

		long duration = t2-t1;
		String timestamp = timestamp(t1);
		String response = (String) getDeep.t(new Object[]{r, "response"});

		Map output = new HashMap();
		put(output,"duration",""+duration);
		put(output,"timestamp",timestamp);
		put(output,"model",model);
		put(output,"prompt",prompt);
		put(output,"response",response);

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