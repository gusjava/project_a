package a.entity.gus06.sys.dirsearch1.engine;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class EntityImpl extends S1 implements Entity, R, V, G, Runnable {

	public String creationDate() {return "20191223";}

	public static final String KEY_FILE = "file";
	public static final String KEY_ROOT_LIST = "root_list";
	public static final String KEY_ROOT_INDEX = "root_index";

	private Service buildListing;
	private Service toList;

	private Object roots;
	private F fileFilter;
	private P fileExtractor;
	private Object progress;
	private Set interrupt;
	
	private Map lastResult;
	private List results;
	private P resultHandler;
	
	public EntityImpl() throws Exception
	{
		buildListing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		toList = Outside.service(this,"gus06.find.list");
		resultHandler = obj->handleResult((Map) obj);
	}
	
	public Object g() throws Exception
	{return lastResult;}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("roots")) return roots;
		if(key.equals("results")) return results;
		if(key.equals("keys")) return new String[]{"roots","results"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("roots")) {roots = obj;return;}
		if(key.equals("fileFilter")) {fileFilter = (F) obj;return;}
		if(key.equals("fileExtractor")) {fileExtractor = (P) obj;return;}
		if(key.equals("progress")) {progress = obj;return;}
		if(key.equals("interrupt")) {interrupt = (Set) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	public void run()
	{
		try
		{
			if(roots==null) throw new Exception("Root has not been initialized");
			
			lastResult = null;
			results = new ArrayList();
			
			List rootList = buildRootList();
			List listings = new ArrayList();
			
			int nb = 0;
			for(int i=0;i<rootList.size();i++)
			{
				File root = (File) rootList.get(i);
				List listing = (List) buildListing.t(root);
				listings.add(listing);
				nb += listing.size();
			}
			
			if(progress!=null) ((V)progress).v("size",""+nb);
			
			for(int i=0;i<rootList.size();i++)
			{
				List listing = (List) listings.get(i);
				for(int j=0;j<listing.size();j++)
				{
					File file = (File) listing.get(j);
					handleFile(file,rootList,i);
					
					if(progress!=null) ((E)progress).e();
					if(interrupt!=null && !interrupt.isEmpty()) break;
				}
			}
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	private void handleFile(File file, List rootList, int rootIndex) throws Exception
	{
		if(fileFilter!=null && !fileFilter.f(file)) return;
		
		Map fileMap = new HashMap();
		fileMap.put(KEY_FILE,file);
		fileMap.put(KEY_ROOT_LIST,rootList);
		fileMap.put(KEY_ROOT_INDEX,rootIndex);
			
		if(fileExtractor==null)
		{
			handleResult(fileMap);
		}
		else
		{
			fileExtractor.p(new Object[]{fileMap,resultHandler,interrupt});
		}
	}
	
	private void handleResult(Map result)
	{
		results.add(result);
		lastResult = result;
		found();
	}
	
	private void found()
	{send(this,"found()");}
	
	private List buildRootList() throws Exception
	{
		if(roots instanceof G) 
			return (List) toList.t(((G) roots).g());
		return (List) toList.t(roots);
	}
}
