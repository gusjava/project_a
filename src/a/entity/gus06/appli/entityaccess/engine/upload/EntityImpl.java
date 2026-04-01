package a.entity.gus06.appli.entityaccess.engine.upload;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, R, E, P {

	public String creationDate() {return "20150228";}
	

	private Service getRemoteList;
	private Service getLocalMap;
	private Service handleDir;
	private Service handleLost;


	private int size;
	private String line;
	
	private Map localMap;
	private List localList;
	private List remoteList;
	private List lostList;
	private List updateList;
	
	

	public EntityImpl() throws Exception
	{
		getRemoteList = Outside.service(this,"gus06.appli.entityaccess.remote.md5list.user");
		getLocalMap = Outside.service(this,"gus06.appli.entityaccess.entitydir.buildmap.md5dir");
		handleDir = Outside.service(this,"gus06.appli.entityaccess.engine.upload.handledir");
		handleLost = Outside.service(this,"gus06.appli.entityaccess.engine.upload.sendlostlist");
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
		localMap = (Map) getLocalMap.g();
		localList = new ArrayList(localMap.keySet());
		
		Object res1 = getRemoteList.g();
		if(res1 instanceof String)
		{
			size=-1;
			line = (String) res1;
			return;
		}
		
		remoteList = (List) res1;
		
		lostList = new ArrayList(remoteList);
		lostList.removeAll(localList);
		
		updateList = new ArrayList(localList);
		updateList.removeAll(remoteList);
		
		Object res2 = handleLost.t(lostList);
		if(res2!=null)
		{
			size=-1;
			line = (String) res2;
			return;
		}
		
		size = updateList.size();
	}




	private void next() throws Exception
	{
		if(updateList.isEmpty()) throw new Exception("queue is empty");
		
		String md5 = (String) updateList.get(0);
		File dir = (File) localMap.get(md5);
		updateList.remove(0);
		
		line = (String) handleDir.t(dir);
	}
}
