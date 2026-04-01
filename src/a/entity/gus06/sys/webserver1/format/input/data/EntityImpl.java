package a.entity.gus06.sys.webserver1.format.input.data;

import a.framework.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140928";}
	
	
	public static final String KEY_METHOD = "method";
	public static final String KEY_URL = "url";
	public static final String KEY_VERSION = "version";
	public static final String KEY_HEADER = "header";
	public static final String KEY_BODY = "body";


	private Service toInputStream;

	public EntityImpl() throws Exception
	{toInputStream = Outside.service(this,"gus06.find.inputstream");}
	
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = new HashMap();
		Map header = new HashMap();
		
		InputStream is = (InputStream) toInputStream.t(obj);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		parseFirstLine(br,map);
		parseHeader(br,header);
		String body = body(br);
		br.close();
		
		map.put(KEY_HEADER,header);
		map.put(KEY_BODY,body);
		
		int length1 = body.length();
		int length0 = contentLength(header);
		
		if(length1<length0) return null;
		return map;
	}
	
	
	
	
	
	private void parseFirstLine(BufferedReader br, Map map) throws Exception
	{
		String line = readLine(br);
		if(line==null) ex("first line",line);
		
		String[] n = line.trim().split(" ");
		if(n.length!=3) ex("first line",line);
		
		String method = n[0];
		String url = n[1];
		String version = n[2];
		
		if(!version.equals("HTTP/1.0") && !version.equals("HTTP/1.1"))
		ex("first line",line);
		
		map.put(KEY_METHOD,method);
		map.put(KEY_URL,url);
		map.put(KEY_VERSION,version);
	}
	
	
	
	
	private void parseHeader(BufferedReader br, Map header) throws Exception
	{
		String line = null;
		while(isFilled(line = readLine(br)))
		{
			String[] k = line.split(" ?: ?",2);
			if(k.length!=2) ex("header line",line);
			header.put(k[0],k[1]);
		}
		if(line==null) ex("header line",line);
	}



	private String body(BufferedReader br) throws Exception
	{
		StringBuilder b = new StringBuilder();
		String line = null;
		while((line = readLine(br))!=null) b.append(line+"\n");
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	private int contentLength(Map header)
	{
		if(!header.containsKey("Content-Length")) return -1;
		String v = (String) header.get("Content-Length");
		return Integer.parseInt(v);
	}
	
	
	private String readLine(BufferedReader br) throws Exception
	{
		String line = br.readLine();
		if(line==null) return null;
		return line.trim();
	}
	
	private boolean isFilled(String line)
	{return line!=null && !line.equals("");}
	
	
	private void ex(String location, String line) throws Exception
	{throw new Exception("Invalid http "+location+": "+start30(line));}
	
	
	private String start30(String line)
	{
		if(line==null) return "null";
		if(line.length()<=30) return line;
		return line.substring(0,30)+"...";
	}
}
