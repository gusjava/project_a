package a.entity.gus06.file.mobi.extract.data.exthheader;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity,T {

	public String creationDate() {return "20190925";}



	public Object t(Object obj) throws Exception
	{
		byte[] data = (byte[]) obj;
		
		String identifier = 		getString(data,0,4);
		if(!identifier.equals("EXTH")) throw new Exception("Invalid identifier: "+identifier);
		int recordCount = 		getInt(data,8,4);
		
		List records = new ArrayList();
		int offset = 12;
		for(int i=0;i<recordCount;i++)
		{
			int recordType = getInt(data, offset, 4);
			int recordlength = getInt(data, offset+4, 4);
			if(recordlength<8) throw new Exception("Invalid EXTH record length: "+recordlength);
			byte[] recordData = getBytes(data, offset+8, recordlength-8);
			
			Map m = new HashMap();
			m.put("type",recordType);
			m.put("data",recordData);
			
			records.add(m);
			offset += recordlength;
		}
		
		Map map = new HashMap();
		
		map.put("identifier",		identifier);
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
