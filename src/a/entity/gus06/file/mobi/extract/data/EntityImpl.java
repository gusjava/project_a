package a.entity.gus06.file.mobi.extract.data;

import a.framework.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity,T {

	public String creationDate() {return "20190923";}
	


	private Service readBytes;
	private Service extractPDBHeader;
	private Service extractMobiHeader;
	private Service buildExthMap;
	
	public EntityImpl() throws Exception
	{
		readBytes = Outside.service(this,"gus06.file.read.raw");
		extractPDBHeader = Outside.service(this,"gus06.file.mobi.extract.data.pdbheader");
		extractMobiHeader = Outside.service(this,"gus06.file.mobi.extract.data.mobiheader");
		buildExthMap = Outside.service(this,"gus06.file.mobi.extract.data.exthmap");
	}



	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		if(file==null) return null;
		if(!file.exists()) return null;
		if(file.length()==0) return null;
		
		byte[] data = (byte[]) readBytes.t(file);
		Map pdbHeader = (Map) extractPDBHeader.t(data);
		
		List records = (List) pdbHeader.get("records");
		int recordNb = records.size();
		if(recordNb<2) throw new Exception("Invalid record number: "+records.size());
		
		/*
		* BUILD MOBI CONTENT
		*/
		
		List mobiContents = new ArrayList();
		for(int i=0;i<recordNb;i++)
		{
			long offset1 = (long) ((Map) records.get(i)).get("dataOffset");
			
			long offset2 = 0;
			if(i==recordNb-1) offset2 = data.length;
			else offset2 = (long) ((Map) records.get(i+1)).get("dataOffset");
			
			byte[] contentData = getBytes(data, (int) offset1, (int) (offset2-offset1));
			mobiContents.add(contentData);
		}
		
		
		/*
		* BUILD MOBI HEADER
		*/
		
		byte[] headerData = (byte[]) mobiContents.get(0);
		Map mobiHeader = (Map) extractMobiHeader.t(headerData);
		
		
		/*
		* BUILD EXTH MAP
		*/
		
		Map exthMap = (Map) buildExthMap.t(mobiHeader);
		
		
		Map map = new HashMap();
		map.put("pdbHeader",pdbHeader);
		map.put("mobiHeader",mobiHeader);
		map.put("mobiContents",mobiContents);
		map.put("exthMap",exthMap);
		
		return map;
	}
	
	
	
	
	
	
	
	private byte[] getBytes(byte[] buffer, int offset, int length)
	{
		byte[] b = new byte[length];
		System.arraycopy(buffer, offset, b, 0, length);
		return b;
	}
}
