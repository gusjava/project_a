package a.entity.gus06.file.mobi.extract.data.pdbheader;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity,T {

	public String creationDate() {return "20190924";}
	
	public static final int PDB_RECORD_OFFSET = 78;
	public static final int PDB_RECORD_LENGTH = 8;



	public Object t(Object obj) throws Exception
	{
		byte[] data = (byte[]) obj;
		
		String name =			getString(data,0,32);
		int attributes =		getInt(data,32,2);
		int version =			getInt(data,34,2);
		long creationDate =		getLong(data,36,4);
		long modificationDate =		getLong(data,40,4);
		long lastBackupDate =		getLong(data,44,4);
		long modificationNumber =	getLong(data,48,4);
		long appInfoID =		getLong(data,52,4);
		long sortInfoID =		getLong(data,56,4);
		long type =			getLong(data,60,4);
		long creator =			getLong(data,64,4);
		long uniqueIDSeed =		getLong(data,68,4);
		long nextRecordListID =		getLong(data,72,4);
		int recordCount = 		getInt(data,76,2);
		
		List records = new ArrayList();
		for(int i=0;i<recordCount;i++)
		{
			int offset = PDB_RECORD_OFFSET + (i * PDB_RECORD_LENGTH);
			byte[] content = getBytes(data,offset,8);
			
			long dataOffset = getLong(content,0,4);
			byte attributes1 = getByte(content,5);
			int uniqueID = getInt(content,5,3);
			
			Map m = new HashMap();
			m.put("dataOffset",dataOffset);
			m.put("attributes",attributes1);
			m.put("uniqueID",uniqueID);
			
			records.add(m);
		}
		
		Map map = new HashMap();
		
		map.put("name",			name);
		map.put("attributes",		attributes);
		map.put("version",		version);
		map.put("creationDate",		creationDate);
		map.put("modificationDate",	modificationDate);
		map.put("lastBackupDate",	lastBackupDate);
		map.put("modificationNumber",	modificationNumber);
		map.put("appInfoID",		appInfoID);
		map.put("sortInfoID",		sortInfoID);
		map.put("type",			type);
		map.put("creator",		creator);
		map.put("uniqueIDSeed",		uniqueIDSeed);
		map.put("nextRecordListID",	nextRecordListID);
		map.put("recordCount",		recordCount);
		map.put("records",		records);
		
		return map;
	}
	
	
	
	
	
	
	// GET BYTES
	
	private byte[] getBytes(byte[] buffer, int offset, int length)
	{
		byte[] b = new byte[length];
		System.arraycopy(buffer,offset,b,0,length);
		return b;
	}
	
	
	// GET BYTE
	
	private byte getByte(byte[] buffer, int pos)
	{
		return buffer[pos];
	}
	
	
	// GET STRING
	
	private String getString(byte[] buffer, int offset, int length)
	{return getString(getBytes(buffer,offset,length));}
	
	private String getString(byte[] buffer)
	{return getString(buffer,null);}
	
	private String getString(byte[] buffer,String encoding)
	{
		if(buffer==null || buffer.length==0) return "";
		
		int len = buffer.length;
		int zeroIndex = -1;
		for(int i=0;i<len;i++)
		{
			byte b = buffer[i];
			if(b==0){zeroIndex = i;break;}
		}

		if(encoding != null)
		{
			try
			{
				if (zeroIndex == -1) return new String(buffer,encoding);
				return new String(buffer,0,zeroIndex,encoding);
			}
			catch(java.io.UnsupportedEncodingException e) {}
		}
		if(zeroIndex == -1) return new String(buffer);
		return new String(buffer,0,zeroIndex);
	}
	
	
	
	// GET INT
	
	private int getInt(byte[] buffer,int offset,int length)
	{return getInt(getBytes(buffer,offset,length));}
	
	private int getInt(byte[] buffer)
	{
		int total = 0;
		int len = buffer.length;
		for(int i=0;i<len;i++) 
		total = (total << 8) + (buffer[i] & 0xff);
		return total;
	}
	
	
	// GET LONG
	
	private long getLong(byte[] buffer,int offset, int length)
	{return getLong(getBytes(buffer,offset,length));}
	
	private long getLong(byte[] buffer)
	{
		long total = 0;
		int len = buffer.length;
		for(int i=0;i<len;i++)
		total = (total << 8) + (buffer[i] & 0xff);
		return total;
	}
}
