package a.entity.gus06.appli.appliaccess.engine.download;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, R, E, P {

	public String creationDate() {return "20150313";}
	
	
	
	private Service sender;
	private Service filterMd5;
	private Service handleMd5;

	private int size;
	private String line;
	private List md5List1;
	
	

	public EntityImpl() throws Exception
	{
		sender = Outside.service(this,"gus06.appli.appliaccess.api.sender");
		filterMd5 = Outside.service(this,"gus06.appli.appliaccess.storedir.filtermd5");
		handleMd5 = Outside.service(this,"gus06.appli.appliaccess.engine.download.handlemd5");
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
		String result = (String) sender.t("md5:all");
		
		if(result==null || result.equals(""))
		{
			size=-1;
			line = "error:no result returned";
			return;
		}
		if(result.startsWith("error:"))
		{
			size=-1;
			line = result;
			return;
		}
		
		String[] n = result.split("\n",-1);
		ArrayList md5List = new ArrayList();
		for(int i=1;i<n.length;i++) md5List.add(n[i].trim());
		
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
