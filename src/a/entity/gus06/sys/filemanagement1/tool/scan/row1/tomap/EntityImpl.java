package a.entity.gus06.sys.filemanagement1.tool.scan.row1.tomap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201124";}

	private Service name0Ext;

	public EntityImpl() throws Exception
	{
		name0Ext = Outside.service(this,"gus06.file.getname0ext");
	}
	
	public Object t(Object obj) throws Exception
	{
		String[] row = (String[]) obj;
		if(row.length!=7) throw new Exception("Invalid row parts nb="+row.length);
		
		String rootName = row[0];
		String location = row[1];
		String fileName = row[2];
		Long size = toLong(row[3]);
		String modified = row[4];
		String md5 = row[5];
		String mime = row[6];
		
		String path = location+"."+fileName;
		String[] n = (String[]) name0Ext.t(fileName);
		String name0 = n[0];
		String ext = n[1];
			
		Map map = new HashMap();
		
		map.put("root",rootName);
		map.put("location",location);
		map.put("name",fileName);
		map.put("size",size);
		map.put("modified",modified);
		map.put("md5",md5);
		map.put("mime",mime);
		
		map.put("path",path);
		map.put("name0",name0);
		map.put("ext",ext);
		
		return map;
	}
	
	
	private Long toLong(String s)
	{
		if(s==null || s.equals("")) return null;
		return Long.valueOf(s);
	}
}