package a.entity.gus06.sys.filemanagement1.tool.scan.row.tomap;

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
		if(row.length!=6) throw new Exception("Invalid row parts nb="+row.length);
		
		String location = row[0];
		String fileName = row[1];
		Long size = toLong(row[2]);
		String modified = row[3];
		String md5 = row[4];
		String mime = row[5];
		
		String path = location+"."+fileName;
		String[] n = (String[]) name0Ext.t(fileName);
		String name0 = n[0];
		String ext = n[1];
		
		Map map = new HashMap();
		
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