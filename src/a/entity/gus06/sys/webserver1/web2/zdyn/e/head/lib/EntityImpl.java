package a.entity.gus06.sys.webserver1.web2.zdyn.e.head.lib;

import a.framework.*;
import java.util.Map;
import java.io.File;

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
		
		Map conf = (Map) mr.r("data config head_link");
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
			addComment(mr,s);
			if(conf.containsKey(s))
			{
				String path = (String) conf.get(s);
				addPath(mr,path);
			}
			int i = 1;
			while(conf.containsKey(s+"-"+i))
			{
				String path = (String) conf.get(s+"-"+i);
				addPath(mr,path);
				i++;
			}
		}
	}
	
	
	
	private void addPath(R mr, String path) throws Exception
	{
		P h = (P) mr.r("data h");
		path = (String) formatter.t(new Object[]{mr,path});
		h.p(buildTag(path));
	}
	
	private void addComment(R mr, String name) throws Exception
	{
		P h = (P) mr.r("data h");
		h.p("<!-- Links for library "+name+" -->");
	}
	
	
	
	
	
	
	
	
	
	private String buildTag(String path)
	{
		if(path.endsWith(".css")) return buildTagCss(path);
		if(path.endsWith(".js")) return buildTagJs(path);
		
		if(path.startsWith("css:")) return buildTagCss(path.substring(4));
		if(path.startsWith("js:")) return buildTagJs(path.substring(3));
		
		return "?"+path+"?";
	}
	
	private String buildTagCss(String path)
	{return "<link rel=\"stylesheet\" href=\""+path+"\">";}
	
	private String buildTagJs(String path)
	{return "<script type=\"text/javascript\" src=\""+path+"\"></script>";}
}
