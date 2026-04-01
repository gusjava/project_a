package a.entity.gus06.sys.jdbcblob.perform.data.register.send;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.sql.PreparedStatement;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160602";}

	public final static String KEY_ID = "ID";
	public final static String KEY_NAME = "NAME";
	public final static String KEY_TYPE = "TYPE";
	public final static String KEY_SIZE = "SIZE";
	public final static String KEY_PREVIEW = "PREVIEW";
	public final static String KEY_IMAGE = "IMAGE";
	public final static String KEY_CONTENT = "CONTENT";
	
	
	
	private Service formatName;
	private Service computeDataSize;
	private Service getWatcher;
	
	public EntityImpl() throws Exception
	{
		formatName = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");
		computeDataSize = Outside.service(this,"gus06.data.size.computesize");
		getWatcher = Outside.service(this,"gus06.jdbc.connection.builder.watcher");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		Map map = (Map) o[2];
		
		String id = (String) map.get(KEY_ID);
		String name = (String) map.get(KEY_NAME);
		String type = (String) map.get(KEY_TYPE);
		String size = (String) map.get(KEY_SIZE);
		byte[] preview = (byte[]) map.get(KEY_PREVIEW);
		byte[] content = (byte[]) map.get(KEY_CONTENT);
		BufferedImage image = (BufferedImage) map.get(KEY_IMAGE);
		
		StringBuilder sql = new StringBuilder("INSERT INTO "+formatName(path));
		sql.append(" (ID,NAME,TYPE,SIZE,PREVIEW,DATA) VALUES (?,?,?,?,?,?)");
		sql.append(" ON DUPLICATE KEY UPDATE NAME=VALUES(NAME), TYPE=VALUES(TYPE), SIZE=VALUES(SIZE), PREVIEW=VALUES(PREVIEW), DATA=VALUES(DATA)");
		
		PreparedStatement st = cx.prepareStatement(sql.toString());
		st.setObject(1,id);
		st.setObject(2,name);
		st.setObject(3,type);
		st.setObject(4,size);
		st.setObject(5,preview);
		st.setObject(6,content);
		
		V watcher = getWatcher(cx);
		if(watcher!=null)
		{
			Long d = computeDataSize(new Object[]{sql,id,name,type,size,preview,content});
			
			watcher.v("query_preview",image);
			watcher.v("query_size",Long.valueOf(d*2));
		}
		
		st.executeUpdate();
	}

	
	
	private String formatName(String name) throws Exception
	{return (String) formatName.t(name);}
	
	
	private Long computeDataSize(Object[] obj) throws Exception
	{return (Long) computeDataSize.t(obj);}
	
	
	private V getWatcher(Connection cx) throws Exception
	{return (V) getWatcher.t(cx);}
}
