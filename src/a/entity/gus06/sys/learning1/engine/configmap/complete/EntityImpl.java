package a.entity.gus06.sys.learning1.engine.configmap.complete;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20250716";}
	
	public static final String KEY_CORPUS_SIZE = "corpus_size";
	public static final String KEY_WEIGHT_EMPTY = "weight_empty";
	public static final String KEY_WEIGHT_RECENT = "weight_recent";
	public static final String KEY_WEIGHT_UNCERTAIN = "weight_uncertain";
	public static final String KEY_WEIGHT_SURE = "weight_sure";
	public static final String KEY_WEIGHT_OVER = "weight_over";
	public static final String KEY_RECENT_LIMIT = "recent_limit";
	public static final String KEY_SURE_THRESHOLD1 = "sure_threshold1";
	public static final String KEY_SURE_THRESHOLD2 = "sure_threshold2";
	public static final String KEY_OVER_THRESHOLD1 = "over_threshold1";
	public static final String KEY_OVER_THRESHOLD2 = "over_threshold2";
	
	public static final int DEFAULT_CORPUS_SIZE = 8;
	public static final int DEFAULT_WEIGHT_EMPTY = 50;
	public static final int DEFAULT_WEIGHT_RECENT = 5;
	public static final int DEFAULT_WEIGHT_UNCERTAIN = 10;
	public static final int DEFAULT_WEIGHT_SURE = 1;
	public static final int DEFAULT_WEIGHT_OVER = 0;
	public static final int DEFAULT_RECENT_LIMIT = 3;
	public static final int DEFAULT_SURE_THRESHOLD1 = 3;
	public static final double DEFAULT_SURE_THRESHOLD2 = 0.25;
	public static final int DEFAULT_OVER_THRESHOLD1 = 5;
	public static final double DEFAULT_OVER_THRESHOLD2 = 0.25;
	
	
	public Object t(Object obj) throws Exception
	{return complete((Map) obj);}
	
	
	public Object g() throws Exception
	{return complete(null);}
	
	
	private Map complete(Map config)
	{
		Map config1 = new HashMap();
		if(config!=null) config1.putAll(config);
		
		checkKey(config1, KEY_CORPUS_SIZE, DEFAULT_CORPUS_SIZE);
		checkKey(config1, KEY_WEIGHT_EMPTY, DEFAULT_WEIGHT_EMPTY);
		checkKey(config1, KEY_WEIGHT_RECENT, DEFAULT_WEIGHT_RECENT);
		checkKey(config1, KEY_WEIGHT_UNCERTAIN, DEFAULT_WEIGHT_UNCERTAIN);
		checkKey(config1, KEY_WEIGHT_SURE, DEFAULT_WEIGHT_SURE);
		checkKey(config1, KEY_WEIGHT_OVER, DEFAULT_WEIGHT_OVER);
		checkKey(config1, KEY_RECENT_LIMIT, DEFAULT_RECENT_LIMIT);
		checkKey(config1, KEY_SURE_THRESHOLD1, DEFAULT_SURE_THRESHOLD1);
		checkKey(config1, KEY_SURE_THRESHOLD2, DEFAULT_SURE_THRESHOLD2);
		checkKey(config1, KEY_OVER_THRESHOLD1, DEFAULT_OVER_THRESHOLD1);
		checkKey(config1, KEY_OVER_THRESHOLD2, DEFAULT_OVER_THRESHOLD2);
		
		return config1;
	}
	
	
	private void checkKey(Map m, String key, int defaultValue)
	{if(!m.containsKey(key)) m.put(key,defaultValue);}
	
	private void checkKey(Map m, String key, double defaultValue)
	{if(!m.containsKey(key)) m.put(key,defaultValue);}
}
