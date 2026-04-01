package a.entity.gus06.appli.entityaccess.engine.download;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, R, E, P {

	public String creationDate() {return "20150228";}
	
	
	
	private Service sender;
	private Service filterMd5;
	private Service handleMd5;
	private Service getRemoteList;

	private int size;
	private String line;
	
	private List md5List;
	private List md5List1;
	
	

	public EntityImpl() throws Exception
	{
		sender = Outside.service(this,"gus06.appli.entityaccess.api.sender");
		filterMd5 = Outside.service(this,"gus06.appli.entityaccess.entitydir.filtermd5");
		handleMd5 = Outside.service(this,"gus06.appli.entityaccess.engine.download.handlemd5");
		getRemoteList = Outside.service(this,"gus06.appli.entityaccess.remote.md5list.other");
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
		Object res = getRemoteList.g();
		if(res instanceof String)
		{
			size=-1;
			line = (String) res;
			return;
		}
		
		md5List = (List) res;
		md5List1 = (List) filterMd5.t(md5List);
		
		size = md5List1.size();
	}
	



	private void next() throws Exception
	{
		if(md5List1.isEmpty()) throw new Exception("queue is empty");
		
		String md5 = (String) md5List1.get(0);
		md5List1.remove(0);
		
		line = (String) handleMd5.t(md5);
	}
}
