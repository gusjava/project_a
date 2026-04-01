package a.entity.gus06.appli.gusclient1.template.entity.generator;

import a.framework.*;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140905";}


	public static final String KEY_ENTITYDATE = "entitydate";
	public static final String KEY_ENTITYNAME = "entityname";
	public static final String KEY_TEMPLATE = "template";
	public static final String KEY_SRC = "src";


	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private String today() {return sdf.format(new Date());}
	
	
	private Service templateEngine;
	private Service srcGenerator;
	
	

	public EntityImpl() throws Exception
	{
		templateEngine = Outside.service(this,"gus06.appli.gusclient1.template.engine.maptostring");
		srcGenerator = Outside.service(this,"gus06.entitydev.generate.srccode1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		check(map,KEY_ENTITYNAME);
		check(map,KEY_TEMPLATE);
		
		map.put(KEY_ENTITYDATE,today());
		String src = (String) templateEngine.t(map);
		map.put(KEY_SRC,src);
		
		srcGenerator.p(map);
	}
	
	
	
	
	
	private void check(Map map, String key) throws Exception
	{if(!map.containsKey(key)) throw new Exception("Key not found: "+key);}
}
