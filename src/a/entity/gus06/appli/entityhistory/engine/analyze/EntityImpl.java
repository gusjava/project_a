package a.entity.gus06.appli.entityhistory.engine.analyze;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, R, E, P {

	public String creationDate() {return "20150426";}

	public static final String KEY_ENTITYDIR = "entitydir";
	

	private Service fileListing;
	private Service getFile;
	private Service extractor;
	private Service mapHolder;
	
	private List list;
	private Map map;
	private int size;
	private String line;

	public EntityImpl() throws Exception
	{
		fileListing = Outside.service(this,"gus06.entitydev.filelisting");
		getFile = Outside.service(this,"gus06.sys.option.getfile");
		extractor = Outside.service(this,"gus06.appli.entityhistory.srcfile.extractor");
		mapHolder = Outside.service(this,"gus06.appli.entityhistory.map.holder");
	}
	
	
	public void e() throws Exception
	{next();}
	
	
	public void p(Object obj) throws Exception
	{
		String rule = (String) obj;
		if(rule.equals("init")) {init();return;}
		throw new Exception("Unknown rule: "+rule);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("size")) return ""+size;
		if(key.equals("line")) return line;
		
		if(key.equals("keys")) return new String[]{"size","line"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void init() throws Exception
	{
		File entityDir = (File) getFile.r(KEY_ENTITYDIR);
		list = (List) fileListing.t(entityDir);
		map = new HashMap();
		size = list.size();
	}


	private void next() throws Exception
	{
		if(list.isEmpty()) throw new Exception("queue is empty");
		
		File file = (File) list.get(0);
		list.remove(0);
		
		String[] infos = (String[]) extractor.t(file);
		if(infos.length!=2) throw new Exception("Invalid info number: "+infos.length);
		
		String entityName = infos[0];
		String entityDate = infos[1];
		
		map.put(entityName,entityDate);
		
		if(list.isEmpty()) mapHolder.p(map);
		line = "analyzed: "+entityName;
	}
}
