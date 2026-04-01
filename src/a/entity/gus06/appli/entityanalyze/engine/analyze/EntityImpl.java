package a.entity.gus06.appli.entityanalyze.engine.analyze;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, R, E, P {

	public String creationDate() {return "20150526";}

	public static final String KEY_SOURCEDIR = "sourcedir";
	

	private Service fileListing;
	private Service getFile;
	private Service analyzer;
	private Service baseHolder;
	private Service formatMap;
	
	private List list;
	private int size;
	private String line;
	


	public EntityImpl() throws Exception
	{
		fileListing = Outside.service(this,"gus06.entitydev.filelisting");
		getFile = Outside.service(this,"gus06.sys.option.getfile");
		analyzer = Outside.service(this,"gus06.java.srcfile.extract.entity.infomap1");
		baseHolder = Outside.service(this,"gus06.appli.entityanalyze.holder.base");
		formatMap = Outside.service(this,"gus06.map.formatvalues.tostring2");
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
		File sourceDir = (File) getFile.r(KEY_SOURCEDIR);
		if(sourceDir==null) return;
		
		baseHolder.p("empty");
		list = (List) fileListing.t(sourceDir);
		size = list.size();
	}



	private void next() throws Exception
	{
		if(list.isEmpty()) throw new Exception("queue is empty");
		
		File file = (File) list.get(0);
		list.remove(0);
		analyze(file);
	}
	
	
	
	
	private void analyze(File file) throws Exception
	{
		try
		{
			Map map = (Map) analyzer.t(file);
			Map map1 = (Map) formatMap.t(map);
			baseHolder.v("map",map1);
			
			String entityName = (String) map.get("entityname");
			line = "analyzed: "+entityName;
		}
		catch(Exception e)
		{
			String message = "Analyze failed for file: "+file;
			throw new Exception(message,e);
		}
	}
}
