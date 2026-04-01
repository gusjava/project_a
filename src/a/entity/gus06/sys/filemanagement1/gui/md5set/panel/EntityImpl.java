package a.entity.gus06.sys.filemanagement1.gui.md5set.panel;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, I, P, Runnable {

	public String creationDate() {return "20201108";}


	private Service buildFileMap;
	private Service handleSearch;
	private Service resultPanel;
	
	private Object engine;
	private Set md5Set;
	private Thread t;
	

	public EntityImpl() throws Exception
	{
		buildFileMap = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.latest");
		handleSearch = Outside.service(this,"gus06.sys.filemanagement1.tool.search.bymd5set");
		resultPanel = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_3.search.resultpanel1");
	}
	
	
	public Object i() throws Exception
	{return resultPanel.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		md5Set = (Set) o[1];
		
		triggerSearch();
	}
	
	
	private void triggerSearch()
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{
		try
		{
			if(engine==null) return;
			if(md5Set==null) return;
			
			resultPanel.p(null);
			
			Map fileMap = (Map) buildFileMap.t(engine);
			List results = (List) handleSearch.t(new Object[]{fileMap,md5Set});
			
			resultPanel.p(new Object[]{engine,results});
			resultPanel.v("select","first");
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
}