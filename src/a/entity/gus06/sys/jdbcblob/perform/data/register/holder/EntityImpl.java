package a.entity.gus06.sys.jdbcblob.perform.data.register.holder;

import a.framework.*;
import java.util.Map;
import java.sql.Connection;
import java.util.HashMap;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160603";}
	
	public final static String KEY_ID = "ID";
	public final static String KEY_NAME = "NAME";
	public final static String KEY_TYPE = "TYPE";
	public final static String KEY_SIZE = "SIZE";
	public final static String KEY_PREVIEW = "PREVIEW";
	public final static String KEY_IMAGE = "IMAGE";
	public final static String KEY_CONTENT = "CONTENT";


	private Service buildMd5;
	private Service prepareData;
	private Service sendData;


	public EntityImpl() throws Exception
	{
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
		prepareData = Outside.service(this,"gus06.sys.jdbcblob.perform.data.register.prepare");
		sendData = Outside.service(this,"gus06.sys.jdbcblob.perform.data.register.send");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		Object data = o[2];
		
		return new Holder(cx,path,data);
	}
	
	
	
	
	private class Holder extends S1 implements R, Runnable
	{
		private Connection cx;
		private String path;
		private Object data;
		
		private String name;
		private String type;
		private byte[] preview;
		private byte[] content;
		private BufferedImage image;
		private String id;
		private String size;
		
		private Exception exception;
		
		
		public Holder(Connection cx, String path, Object data) throws Exception
		{
			this.cx = cx;
			this.path = path;
			this.data = data;
			
			Object[] oo = (Object[]) prepareData.t(data);
			if(oo.length!=5) throw new Exception("Wrong data number (2): "+oo.length);
		
			name = (String) oo[0];
			type = (String) oo[1];
			preview = (byte[]) oo[2];
			content = (byte[]) oo[3];
			image = (BufferedImage) oo[4];
			
			id = (String) buildMd5.t(content);
			size = ""+content.length;
		}
		
		public void run()
		{
			Map map = new HashMap();
			
			map.put(KEY_ID,id);
			map.put(KEY_NAME,name);
			map.put(KEY_TYPE,type);
			map.put(KEY_SIZE,size);
			map.put(KEY_PREVIEW,preview);
			map.put(KEY_CONTENT,content);
			map.put(KEY_IMAGE,image);
			
			started();
			exception = sendData(cx,path,map);
			
			if(exception==null) complete();
			else failed();
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("cx")) return cx;
			if(key.equals("path")) return path;
			if(key.equals("data")) return data;
			
			if(key.equals("name")) return name;
			if(key.equals("type")) return type;
			if(key.equals("preview")) return preview;
			if(key.equals("content")) return content;
			if(key.equals("image")) return image;
			if(key.equals("id")) return id;
			if(key.equals("size")) return size;
			if(key.equals("exception")) return exception;
			
			if(key.equals("keys"))
				return new String[]{"cx","path","data","name","type",
				"preview","content","image","id","size","exception"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		private void started()
		{send(this,"started()");}
		
		private void complete()
		{send(this,"complete()");}
		
		private void failed()
		{send(this,"failed()");}
	}
	
	
	
	
	private Exception sendData(Connection cx, String path, Map map)
	{
		try
		{
			sendData.p(new Object[]{cx,path,map});
			return null;
		}
		catch(Exception e)
		{
			Outside.err(this,"sendData(Connection,String,Map)",e);
			return e;
		}
	}
}
