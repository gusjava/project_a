package a.entity.gus06.sys.webserver1.web2.zdyn.e.head.meta;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140930";}


	private Service findPage;
	private Service formatter;
	
	public EntityImpl() throws Exception
	{
		findPage = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.find.page");
		formatter = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.format.formatter1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		R mr = (R) obj;
		
		Map conf = (Map) mr.r("data config head_meta");
		String id = (String) findPage.t(obj);
		
		apply(mr,conf,"first");
		apply(mr,conf,id);
		apply(mr,conf,"last");
	}
	
	
	
	private void apply(R mr, Map conf, String key) throws Exception
	{
		if(!conf.containsKey(key)) return;
		String value = (String) conf.get(key);
		String[] n = value.split(";");
		
		for(String s:n)
		{
			if(conf.containsKey(s))
			{
				String meta = (String) conf.get(s);
				addMeta(mr,meta);
			}
			int i = 1;
			while(conf.containsKey(s+"-"+i))
			{
				String meta = (String) conf.get(s+"-"+i);
				addMeta(mr,meta);
				i++;
			}
		}
	}
	
	
	
	private void addMeta(R mr, String meta) throws Exception
	{
		P h = (P) mr.r("data h");
		meta = (String) formatter.t(new Object[]{mr,meta});
		h.p(meta);
	}
}
