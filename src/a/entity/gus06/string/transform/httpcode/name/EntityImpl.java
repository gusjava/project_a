package a.entity.gus06.string.transform.httpcode.name;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190715";}


	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("100","Continue");
		put("101","Switching Protocols");
		put("102","Processing");
		put("103","Early Hints");
		put("200","OK");
		put("201","Created");
		put("202","Accepted");
		put("203","Non-Authoritative Information");
		put("204","No Content");
		put("205","Reset Content");
		put("206","Partial Content");
		put("207","Multi-Status");
		put("208","Already Reported");
		put("210","Content Different");
		put("226","IM Used");
		put("300","Multiple Choices");
		put("301","Moved Permanently");
		put("302","Found");
		put("303","See Other");
		put("304","Not Modified");
		put("305","Use Proxy (depuis HTTP/1.1)");
		put("306","Switch Proxy");
		put("307","Temporary Redirect");
		put("308","Permanent Redirect");
		put("310","Too many Redirects");
		put("400","Bad Request");
		put("401","Unauthorized");
		put("402","Payment Required");
		put("403","Forbidden");
		put("404","Not Found");
		put("405","Method Not Allowed");
		put("406","Not Acceptable");
		put("407","Proxy Authentication Required");
		put("408","Request Time-out");
		put("409","Conflict");
		put("410","Gone");
		put("411","Length Required");
		put("412","Precondition Failed");
		put("413","Request Entity Too Large");
		put("414","Request-URI Too Long");
		put("415","Unsupported Media Type");
		put("416","Requested range unsatisfiable");
		put("417","Expectation failed");
		put("418","I�m a teapot");
		put("421","Bad mapping / Misdirected Request");
		put("422","Unprocessable entity");
		put("423","Locked");
		put("424","Method failure");
		put("425","Unordered Collection");
		put("426","Upgrade Required");
		put("428","Precondition Required");
		put("429","Too Many Requests");
		put("431","Request Header Fields Too Large");
		put("449","Retry With");
		put("450","Blocked by Windows Parental Controls");
		put("451","Unavailable For Legal Reasons");
		put("456","Unrecoverable Error");
		put("444","No Response");
		put("495","SSL Certificate Error");
		put("496","SSL Certificate Required");
		put("497","HTTP Request Sent to HTTPS Port");
		put("498","Token expired/invalid");
		put("499","Client Closed Request");
		put("500","Internal Server Error");
		put("501","Not Implemented");
		put("502","Bad Gateway ou Proxy Error");
		put("503","Service Unavailable");
		put("504","Gateway Time-out");
		put("505","HTTP Version not supported");
		put("506","Variant Also Negotiates");
		put("507","Insufficient storage");
		put("508","Loop detected");
		put("509","Bandwidth Limit Exceeded");
		put("510","Not extended");
		put("511","Network authentication required");
		put("520","Unknown Error");
		put("521","Web Server Is Down");
		put("522","Connection Timed Out");
		put("523","Origin Is Unreachable");
		put("524","A Timeout Occurred");
		put("525","SSL Handshake Failed");
		put("526","Invalid SSL Certificate");
		put("527","Railgun Error");
	}
	
	
	private void put(String code, String name)
	{map.put(code,name);}
	
	
	public Object t(Object obj) throws Exception
	{
		String code = (String) obj;
		if(!map.containsKey(code)) throw new Exception("Unknown code: "+code);
		return map.get(code);
	}
}
