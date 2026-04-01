package a.entity.gus06.web.openrouter.api.chat.query2.translator;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251126";}
	
	public static final String KEY_PROMPT = "prompt";
	public static final String KEY_LANGUAGE1 = "language1";
	public static final String KEY_LANGUAGE2 = "language2";
	
	public static final String KEY_MODEL = "model";
	public static final String KEY_APIKEY = "apikey";
	public static final String KEY_MESSAGES = "messages";
	
	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.web.openrouter.api.chat.query2");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map input = (Map) obj;
		
		String prompt = (String) input.get(KEY_PROMPT);
		String model = (String) input.get(KEY_MODEL);
		String apikey = (String) input.get(KEY_APIKEY);
		String languageCode1 = (String) input.get(KEY_LANGUAGE1);
		String languageCode2 = (String) input.get(KEY_LANGUAGE2);
		
		String languageName1 = new Locale(languageCode1).getDisplayLanguage(Locale.ENGLISH);
		String languageName2 = new Locale(languageCode2).getDisplayLanguage(Locale.ENGLISH);
		
		Map message1 = new HashMap();
		message1.put("role", "system");
		message1.put("content", "You are a "+languageName1+"-"+languageName2+" translator and you always answer by giving only the translation");
		
		Map message2 = new HashMap();
		message2.put("role", "user");
		message2.put("content", "Translate the following into "+languageName2+": "+prompt);
		
		List messages = new ArrayList();
		messages.add(message1);
		messages.add(message2);
		
		Map m = new HashMap();
		m.put(KEY_MODEL, model);
		m.put(KEY_APIKEY, apikey);
		m.put(KEY_MESSAGES, messages);
		
		Map r = (Map) perform.t(m);
		return r.get("content");
	}
}