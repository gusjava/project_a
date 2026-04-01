package a.entity.gus06.sys.filemanagement1.tool.ebook.format.author;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201105";}



	private Service normalizeFileName;
	
	public EntityImpl() throws Exception
	{
		normalizeFileName = Outside.service(this,"gus06.string.transform.normalize.filename");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return format((String) obj);
	}
	
	
	private String format(String value) throws Exception
	{
		value = value.split(" - ")[0].trim();
		
		value = value.replaceAll("\\([^\\)]*\\)"," ");
		value = value.replaceAll("\\[[^\\]]*\\]"," ");
		value = value.replaceAll("[_%#\n\t ]+"," ");
		
		String[] n = value.split(",",-1);
		if(n.length==2)
		{
			String part1 = n[0].trim();
			String part2 = n[1].trim();
			if(!part1.contains(" ") && !part2.contains(" "))
			value = part2+" "+part1;
		}
		
		while(value.endsWith(".")) value = value.substring(0,value.length()-1);
		while(value.startsWith(".")) value = value.substring(1);
		
		return (String) normalizeFileName.t(value);
	}
}