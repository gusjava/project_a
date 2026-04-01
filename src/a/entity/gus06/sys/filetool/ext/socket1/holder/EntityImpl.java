package a.entity.gus06.sys.filetool.ext.socket1.holder;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.awt.Color;
import java.util.HashMap;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20221110";}
	
	public static final String KEY_ROOT = "path.root";
	public static final String KEY_LOCAL_PORT = "local.port";
	public static final String KEY_LOCAL_FILE_PORT = "local.file.port";
	public static final String KEY_REMOTE_PORT = "remote.port";
	public static final String KEY_REMOTE_IP = "remote.ip";
	
	public static final int DEFAULT_LOCAL_PORT = 5000;
	public static final int DEFAULT_LOCAL_FILE_PORT = 5001;
	public static final int DEFAULT_REMOTE_PORT = 5000;
	
	
	private Service findRoot;
	private Service mainGui;
	
	private Map map;
	private File root;
	
	private int localPort;
	private int localFilePort;
	private int remotePort;
	private String remoteIp;

	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		mainGui = Outside.service(this,"*gus06.sys.socket1.gui.maingui");
	}
	
	
	public Object i() throws Exception
	{return mainGui.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		
		root = (File) findRoot.t(map);
		localPort = toInt(get(KEY_LOCAL_PORT, DEFAULT_LOCAL_PORT));
		localFilePort = toInt(get(KEY_LOCAL_FILE_PORT, DEFAULT_LOCAL_FILE_PORT));
		remotePort = toInt(get(KEY_REMOTE_PORT, DEFAULT_REMOTE_PORT));
		remoteIp = (String) get(KEY_REMOTE_IP);
		
		Map data = new HashMap();
		
		data.put(KEY_ROOT, root);
		data.put(KEY_LOCAL_PORT, localPort);
		data.put(KEY_LOCAL_FILE_PORT, localFilePort);
		data.put(KEY_REMOTE_PORT, remotePort);
		data.put(KEY_REMOTE_IP, remoteIp);
		
		mainGui.p(data);
	}
	
	
	
	
	private Object get(String key, Object defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return map.get(key);
	}
	
	private Object get(String key)
	{
		return get(key, null);
	}
	
	private Object get1(String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
	
	private int toInt(Object value) throws Exception
	{
		try{return Integer.parseInt(""+value);}
		catch(NumberFormatException e)
		{throw new Exception("Failed to convert value into int: "+value);}
	}
}