package a.entity.gus06.web.allocine.api.movie.perform;

import a.framework.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200112";}


	public static final String URLROOT = "http://api.allocine.fr/rest/v3";
	public static final String METHOD = "movie";
	
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
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String code = o[0];
		String profile = o[1];
		
		String params = buildParams(code,profile);
		String sig = (String) encrypt.t(new Object[]{METHOD,params});
		String url = URLROOT+"/"+METHOD+"?"+params+"&sig="+sig;
		
		return urlToContent.t(url);
	}
	
	
	private String buildParams(String code, String profile) throws Exception
	{
		StringBuffer b = new StringBuffer();
		
		b.append("partner="+partnerKey.g());
		b.append("&sed="+today());
		
		b.append("&code="+encode(code));
		b.append("&format="+FORMAT);
		b.append("&filter="+FILTER);
		b.append("&profile="+profile);
		
		return b.toString();
	}

	
	private String encode(String s) throws Exception
	{return URLEncoder.encode(s,"UTF-8");}
}
