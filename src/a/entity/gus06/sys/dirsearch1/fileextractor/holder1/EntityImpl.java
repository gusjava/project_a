package a.entity.gus06.sys.dirsearch1.fileextractor.holder1;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P, V, R {

	public String creationDate() {return "20191226";}
	
	public static final String KEY_FILE = "file";
	public static final String KEY_POS = "pos";
	public static final String KEY_BLOCK = "block";
	public static final String KEY_NAME = "name";
	public static final String KEY_LOCATION = "location";
	public static final String KEY_SECTIONS = "sections";
	public static final String KEY_EXTRACTOR = "extractor";
	public static final String KEY_LINENB = "line_nb";
	


	private Service fileReader;
	
	private List blockExtrList;
	private List nameExtrList;
	private List locationExtrList;
	

	public EntityImpl() throws Exception
	{
		fileReader = Outside.service(this,"gus06.file.string.reader.handlelines.autodetect");
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("blockExtrList")) {blockExtrList = (List) obj;return;}
		if(key.equals("nameExtrList")) {nameExtrList = (List) obj;return;}
		if(key.equals("locationExtrList")) {locationExtrList = (List) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("blockExtrList")) return blockExtrList;
		if(key.equals("nameExtrList")) return nameExtrList;
		if(key.equals("locationExtrList")) return locationExtrList;
		
		if(key.equals("keys")) return new String[]{
			"blockExtrList","nameExtrList","locationExtrList"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
		
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map fileMap = (Map) o[0];
		P resultHandler = (P) o[1];
		Set interrupt = (Set) o[2];
		
		File file = (File) fileMap.get(KEY_FILE);
		
		if(nameExtrList!=null) 
		{
			String name = file.getName();
			for(int i=0;i<nameExtrList.size();i++)
			{
				T extr = (T) nameExtrList.get(i);
				Object sections = extr.t(name);
				if(sections!=null)
				{
					Map map = new HashMap(fileMap);
					map.put(KEY_NAME,name);
					map.put(KEY_SECTIONS,sections);
					map.put(KEY_EXTRACTOR,extr);
					
					resultHandler.p(map);
				}
			}
		}
		
		if(locationExtrList!=null) 
		{
			String location = file.getParentFile().getAbsolutePath();
			for(int i=0;i<locationExtrList.size();i++)
			{
				T extr = (T) locationExtrList.get(i);
				Object sections = extr.t(location);
				if(sections!=null)
				{
					Map map = new HashMap(fileMap);
					map.put(KEY_LOCATION,location);
					map.put(KEY_SECTIONS,sections);
					map.put(KEY_EXTRACTOR,extr);
					
					resultHandler.p(map);
				}
			}
		}
		
		if(blockExtrList!=null)
		{
			BlockHandler blockHandler = new BlockHandler(fileMap,resultHandler);
			fileReader.p(new Object[]{file,blockHandler,null,interrupt});
			if(blockHandler.found)
			{
				int lineNb = blockHandler.index+1;
				
				Map map = new HashMap(fileMap);
				map.put(KEY_LINENB,lineNb);
				resultHandler.p(map);
			}
		}
	}
	
	
	
	
	private class BlockHandler implements P
	{
		private Map fileMap;
		private P resultHandler;
		private int index = 0;
		private boolean found = false;
		
		public BlockHandler(Map fileMap, P resultHandler)
		{
			this.fileMap = fileMap;
			this.resultHandler = resultHandler;
		}
		
		public void p(Object obj) throws Exception
		{
			String block = (String) obj;
			for(int i=0;i<blockExtrList.size();i++)
			{
				T extr = (T) blockExtrList.get(i);
				Object sections = extr.t(block);
				if(sections!=null)
				{
					Map map = new HashMap(fileMap);
					map.put(KEY_POS,index);
					map.put(KEY_BLOCK,block);
					map.put(KEY_SECTIONS,sections);
					map.put(KEY_EXTRACTOR,extr);
					
					resultHandler.p(map);
					found = true;
				}
			}
			index++;
		}
	}
}
