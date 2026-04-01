package a.entity.gus06.sys.dirdoubloon1.engine;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl extends S1 implements Entity, V, R, P {

	public String creationDate() {return "20221218";}


	private Service toList;
	private Service computeMd5;
	private Service computeSizes;
	private Service deduplicate;
	
	private Object roots;
	private Thread t;
	private Map md5Map;
	private Exception exception;
	private int deduplicatedNb = 0;
	
	public EntityImpl() throws Exception
	{
		toList = Outside.service(this,"gus06.find.list");
		computeMd5 = Outside.service(this,"gus06.sys.dirdoubloon1.compute.md5");
		computeSizes = Outside.service(this,"gus06.sys.dirdoubloon1.compute.sizes");
		deduplicate = Outside.service(this,"gus06.sys.dirdoubloon1.perform.deduplicate.group");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("roots")) {roots = obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("roots")) return roots;
		if(key.equals("md5Map")) return md5Map;
		if(key.equals("exception")) return exception;
		if(key.equals("deduplicatedNb")) return deduplicatedNb;
		if(key.equals("keys")) 
			return new String[]{"roots", "md5Map", "exception", "deduplicatedNb"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void p(Object obj) throws Exception
	{
		String cmd = (String) obj;
		if(cmd.equals("analyze")) {startAnalyze();return;}
		if(cmd.equals("clean")) {startCleaning();return;}
		throw new Exception("Unsupported cmd: "+cmd);
	}
	
	
	
	private void startAnalyze()
	{
		if(t!=null && t.isAlive())
		{
			failed();
			return;
		}
		Runnable r = new Runnable() {
			public void run() {performAnalyze();}
		};
		t = new Thread(r, "THREAD_"+getClass().getName());
		t.start();
	}

	
	private void startCleaning()
	{
		if(t!=null && t.isAlive())
		{
			failed();
			return;
		}
		Runnable r = new Runnable() {
			public void run() {performCleaning();}
		};
		t = new Thread(r, "THREAD_"+getClass().getName());
		t.start();
	}

	
	
	
	
	private void performAnalyze()
	{
		try
		{
			List rootList = buildRootList();
			Map sizeMap = (Map) computeSizes.t(rootList);
			groupedBySize();
			md5Map = (Map) computeMd5.t(sizeMap);
			groupedByMd5();
		}
		catch(Exception e)
		{
			Outside.err(this,"performAnalyze()",e);
			exception = e;
			failed();
		}
	}
	
	
	private void performCleaning()
	{
		try
		{
			if(md5Map==null) return;
			Iterator it = md5Map.keySet().iterator();
			deduplicatedNb = 0;
			while(it.hasNext())
			{
				String md5 = (String) it.next();
				Map m = (Map) md5Map.get(md5);
				if(deduplicate.f(m)) deduplicatedNb++;
			}
			cleaned();
		}
		catch(Exception e)
		{
			Outside.err(this,"performCleaning()",e);
			exception = e;
			failed();
		}
	}
	
	
	
	private List buildRootList() throws Exception
	{
		if(roots==null) throw new Exception("Root has not been initialized");
		if(roots instanceof G) return (List) toList.t(((G) roots).g());
		return (List) toList.t(roots);
	}
	
	
	
	
	private void groupedByMd5()
	{send(this,"groupedByMd5()");}
	
	private void groupedBySize()
	{send(this,"groupedBySize()");}
	
	private void cleaned()
	{send(this,"cleaned()");}
	
	private void failed()
	{send(this,"failed()");}
}
