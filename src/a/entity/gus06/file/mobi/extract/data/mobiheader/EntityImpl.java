package a.entity.gus06.file.mobi.extract.data.mobiheader;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity,T {

	public String creationDate() {return "20190925";}
	

	/** Size of the extra bytes for the rest of mobi header*/
	public static final int MOBI_HEADER_REST = 16;
	
	


	private Service extractExthHeader;

	public EntityImpl() throws Exception
	{
		extractExthHeader = Outside.service(this,"gus06.file.mobi.extract.data.exthheader");
		
	}



	public Object t(Object obj) throws Exception
	{
		byte[] data = (byte[]) obj;
		
		//PalmDOC Header 
		
		int compression =		getInt(data,0,2);
		int unused0 =			getInt(data,2,2);
		int textLength =		getInt(data,4,4);
		int recordCount = 		getInt(data,8,2);
		int recordSize = 		getInt(data,10,2);
		int encryptionType = 		getInt(data,12,2);
		int unused1 = 			getInt(data,14,2);
		
		//MOBI Header 
		
		String identifier = 		getString(data,16,4);
		if(!identifier.equals("MOBI")) throw new Exception("Invalid identifier: "+identifier);
		int headerLength = 		getInt(data,20,4);
		int mobiType = 			getInt(data,24,4);
		int textEncoding = 		getInt(data,28,4);
		int uniqueID = 			getInt(data,32,4);
		int fileVersion = 		getInt(data,36,4);
		int orthographicIndex = 	getInt(data,40,4);
		int inflectionIndex = 		getInt(data,44,4);
		int indexNames = 		getInt(data,48,4);
		int indexKeys = 		getInt(data,52,4);
		int extraIndex0 = 		getInt(data,56,4);
		int extraIndex1 = 		getInt(data,60,4);
		int extraIndex2 = 		getInt(data,64,4);
		int extraIndex3 = 		getInt(data,68,4);
		int extraIndex4 = 		getInt(data,72,4);
		int extraIndex5 = 		getInt(data,76,4);
		int firstNonBookIndex = 	getInt(data,80,4);
		int fullNameOffset = 		getInt(data,84,4);
		int fullNameLength = 		getInt(data,88,4);
		int locale = 			getInt(data,92,4);
		int inputLanguage = 		getInt(data,96,4);
		int outputLanguage = 		getInt(data,100,4);
		int minVersion = 		getInt(data,104,4);
		int firstImageIndex = 		getInt(data,108,4);
		int huffmanRecordOffset = 	getInt(data,112,4);
		int huffmanRecordCount = 	getInt(data,116,4);
		int huffmanTableOffset = 	getInt(data,120,4);
		int huffmanTableLength = 	getInt(data,124,4);
		int exthFlags = 		getInt(data,128,4);
		
		int firstContentRecordIndex = 	headerLength>=194 ? getInt(data,192,2) : -1;
		int lastContentRecordIndex = 	headerLength>=196 ? getInt(data,194,2) : -1;
		int fcisRecordIndex = 		headerLength>=204 ? getInt(data,200,4) : -1;
		int fcisRecordCount = 		headerLength>=208 ? getInt(data,204,4) : -1;
		int flisRecordIndex = 		headerLength>=212 ? getInt(data,208,4) : -1;
		int flisRecordCount = 		headerLength>=216 ? getInt(data,212,4) : -1;
		int srcsRecordIndex = 		headerLength>=228 ? getInt(data,224,4) : -1;
		int srcsRecordCount = 		headerLength>=232 ? getInt(data,228,4) : -1;
		int extraRecordDataFlags = 	headerLength>=244 ? getInt(data,240,4) : -1;
		int indxRecordIndex = 		headerLength>=248 ? getInt(data,244,4) : -1;
		int fragmentRecordIndex = 	headerLength>=256 ? getInt(data,252,4) : -1;
		int skeletonRecordIndex = 	headerLength>=264 ? getInt(data,260,4) : -1;
		int datpRecordIndex = 		headerLength>=268 ? getInt(data,264,4) : -1;
		
//		int datpRecordIndex = 		headerLength>=276 ? getInt(data,272,4) : -1;
		
		
		
		Map map = new HashMap();
		
		map.put("compression",		compression);
		map.put("unused0",		unused0);
		map.put("textLength",		textLength);
		map.put("recordCount",		recordCount);
		map.put("recordSize",		recordSize);
		map.put("encryptionType",	encryptionType);
		map.put("identifier",		identifier);
		map.put("headerLength",		headerLength);
		map.put("mobiType",		mobiType);
		map.put("textEncoding",		textEncoding);
		map.put("uniqueID",		uniqueID);
		map.put("fileVersion",		fileVersion);
		map.put("orthographicIndex",	orthographicIndex);
		map.put("inflectionIndex",	inflectionIndex);
		map.put("indexNames",		indexNames);
		map.put("indexKeys",		indexKeys);
		map.put("extraIndex0",		extraIndex0);
		map.put("extraIndex1",		extraIndex1);
		map.put("extraIndex2",		extraIndex2);
		map.put("extraIndex3",		extraIndex3);
		map.put("extraIndex4",		extraIndex4);
		map.put("extraIndex5",		extraIndex5);
		map.put("firstNonBookIndex",	firstNonBookIndex);
		map.put("fullNameOffset",	fullNameOffset);
		map.put("fullNameLength",	fullNameLength);
		map.put("locale",		locale);
		map.put("inputLanguage",	inputLanguage);
		map.put("outputLanguage",	outputLanguage);
		map.put("minVersion",		minVersion);
		map.put("firstImageIndex",	firstImageIndex);
		map.put("huffmanRecordOffset",	huffmanRecordOffset);
		map.put("huffmanRecordCount",	huffmanRecordCount);
		map.put("huffmanTableOffset",	huffmanTableOffset);
		map.put("huffmanTableLength",	huffmanTableLength);
		map.put("exthFlags",		exthFlags);
		
		map.put("firstContentRecordIndex",	firstContentRecordIndex);
		map.put("lastContentRecordIndex",	lastContentRecordIndex);
		map.put("fcisRecordIndex",		fcisRecordIndex);
		map.put("fcisRecordCount",		fcisRecordCount);
		map.put("flisRecordIndex",		flisRecordIndex);
		map.put("flisRecordCount",		flisRecordCount);
		map.put("srcsRecordIndex",		srcsRecordIndex);
		map.put("srcsRecordCount",		srcsRecordCount);
		map.put("extraRecordDataFlags",		extraRecordDataFlags);
		map.put("indxRecordIndex",		indxRecordIndex);
		map.put("fragmentRecordIndex",		fragmentRecordIndex);
		map.put("skeletonRecordIndex",		skeletonRecordIndex);
		map.put("datpRecordIndex",		datpRecordIndex);
		map.put("datpRecordIndex",		datpRecordIndex);
		
		if((exthFlags & 0x40) != 0)
		{
			int exthHeaderOffset = headerLength + MOBI_HEADER_REST;
			int exthHeaderLength = data.length - exthHeaderOffset;
			byte[] exthData = getBytes(data, exthHeaderOffset, exthHeaderLength);
		
			Map exthHeader = (Map) extractExthHeader.t(exthData);
			map.put("exthHeader",exthHeader);
		}
		
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
