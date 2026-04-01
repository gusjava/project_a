package a.entity.gus06.sys.webserver1.format.input;

import a.framework.*;
import java.util.Map;
import java.nio.channels.SocketChannel;
import java.util.Hashtable;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140928";}
	
	
	public static final String KEY_ADDRESS = "address";
	public static final String KEY_PORT = "port";
	public static final String KEY_CHANNEL = "channel";
	public static final String KEY_DATA = "data";
	
	public static final int BUFFSIZE = 8192;


	private Service formatData;
	private Map pending;
	
	

	public EntityImpl() throws Exception
	{
		formatData = Outside.service(this,"gus06.sys.webserver1.format.input.data");
		pending = new Hashtable();
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		SocketChannel ch = (SocketChannel) o[0];
		byte[] data = (byte[]) o[1];
		
		
		if(data.length==BUFFSIZE)
		{
			enqueueData(ch,data);
			return null;
		}
		data = appendData(ch,data);
		
		Map map = (Map) formatData.t(data);
		
		if(map==null)
		{
			enqueueData(ch,data);
			return null;
		}
		
		map.put(KEY_ADDRESS,address(ch));
		map.put(KEY_PORT,port(ch));
		map.put(KEY_CHANNEL,ch);
		map.put(KEY_DATA,data);
		
		return map;
	}
	
	
	
	
	private String address(SocketChannel ch)
	{return ch.socket().getInetAddress().getHostName();}
	
	
	private String port(SocketChannel ch)
	{return ""+ch.socket().getPort();}
	
	
	
	private void enqueueData(SocketChannel ch, byte[] data)
	{
		if(!pending.containsKey(ch))
		{pending.put(ch,data);return;}
		byte[] data0 = (byte[]) pending.get(ch);
		pending.put(ch,sum(data0,data));
	}
	
	
	private byte[] appendData(SocketChannel ch, byte[] data)
	{
		if(!pending.containsKey(ch)) return data;
		byte[] data0 = (byte[]) pending.get(ch);
		pending.remove(ch);
		return sum(data0,data);
	}
	
	
	private byte[] sum(byte[] b1, byte[] b2)
	{
		byte[] data = new byte[b1.length + b2.length];
		System.arraycopy(b1,0,data,0,b1.length);
		System.arraycopy(b2,0,data,b1.length,b2.length);
		return data;
	}
}
