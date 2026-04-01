package a.entity.gus06.sys.webserver1.format.output.header.first;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141107";}

	public static final String KEY_CODE = "code";
	public static final String KEY_VERSION = "version";
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String code = (String) get(map,KEY_CODE);
		String version = (String) get(map,KEY_VERSION);
		
		return version+" "+code+" "+message(code);
	}
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
	
	private String message(String code) throws Exception
	{
		if(code.equals("100")) return "Continue";
		if(code.equals("101")) return "Switching Protocols";
		
		if(code.equals("200")) return "OK";
		if(code.equals("201")) return "Created";
		if(code.equals("202")) return "Accepted";
		if(code.equals("203")) return "Non-Authoritative Information";
		if(code.equals("204")) return "No Content";
		if(code.equals("205")) return "Reset Content";
		if(code.equals("206")) return "Partial Content";
		
		if(code.equals("300")) return "Multiple Choices";
		if(code.equals("301")) return "Moved Permanently";
		if(code.equals("302")) return "Found";
		if(code.equals("303")) return "See Other";
		if(code.equals("304")) return "Not Modified";
		if(code.equals("305")) return "Use Proxy";
		if(code.equals("307")) return "Temporary Redirect";
		
		if(code.equals("400")) return "Bad Request";
		if(code.equals("401")) return "Unauthorized";
		if(code.equals("402")) return "Payment Required";
		if(code.equals("403")) return "Forbidden";
		if(code.equals("404")) return "Not Found";
		if(code.equals("405")) return "Method Not Allowed";
		if(code.equals("406")) return "Not Acceptable";
		if(code.equals("407")) return "Proxy Authentication Required";
		if(code.equals("408")) return "Request Time-out";
		if(code.equals("409")) return "Conflict";
		if(code.equals("410")) return "Gone";
		if(code.equals("411")) return "Length Required";
		if(code.equals("412")) return "Precondition Failed";
		if(code.equals("413")) return "Request Entity Too Large";
		if(code.equals("414")) return "Request-URI Too Large";
		if(code.equals("415")) return "Unsupported Media Type";
		if(code.equals("416")) return "Requested range not satisfiable";
		if(code.equals("417")) return "Expectation Failed";
		
		if(code.equals("500")) return "Internal Server Error";
		if(code.equals("501")) return "Not Implemented";
		if(code.equals("502")) return "Bad Gateway";
		if(code.equals("503")) return "Service Unavailable";
		if(code.equals("504")) return "Gateway Time-out";
		if(code.equals("505")) return "HTTP Version not supported";
		
		throw new Exception("Invalid HTTP return code: "+code);
	}
}
