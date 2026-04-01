package a.entity.gus06.web.allocine.api.search.perform;

import a.framework.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141010";}


	public static final String URLROOT = "http://api.allocine.fr/rest/v3";
	public static final String METHOD = "search";
	
	public static final String FORMAT = "json";
	public static final String FILTER = "movie";
	
	
	private SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	private String today() {return yyyyMMdd.format(new Date());}
	

	private Service encrypt;
	private Service urlToContent;
	private Service partnerKey;
	
	public EntityImpl() throws Exception
	{
		encrypt = Outside.service(this,"gus06.web.allocine.api.tool.encrypt");
		urlToContent = Outside.service(this,"gus06.web.allocine.api.tool.urltocontent");
		partnerKey = Outside.service(this,"gus06.web.allocine.api.tool.partnerkey");
	}


	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		
		String[] n = input.split("\\|",2);
		String search = n[0];
		String page = n.length==2?n[1]:null;
		
		String params = buildParams(search,page);
		String sig = (String) encrypt.t(new Object[]{METHOD,params});
		String url = URLROOT+"/"+METHOD+"?"+params+"&sig="+sig;
		
		return urlToContent.t(url);
	}
	
	
	private String buildParams(String search, String page) throws Exception
	{
		StringBuffer b = new StringBuffer();
		
		b.append("partner="+partnerKey.g());
		b.append("&sed="+today());
		
		b.append("&q="+encode(search));
		if(page!=null) b.append("&page="+page);
		b.append("&format="+FORMAT);
		b.append("&filter="+FILTER);
		
		return b.toString();
	}
	
	private String encode(String s) throws Exception
	{return URLEncoder.encode(s,"UTF-8");}
}
