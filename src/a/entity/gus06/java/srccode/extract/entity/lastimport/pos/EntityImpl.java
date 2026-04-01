package a.entity.gus06.java.srccode.extract.entity.lastimport.pos;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200207";}

	public static final String ST_PACKAGE = "package gus06.entity.";
	public static final String ST_IMPORT = "import ";
	public static final String ST_CLASSHEADER = "public class EntityImpl ";
	
	
	private Service toArray;

	public EntityImpl() throws Exception
	{toArray = Outside.service(this,"gus06.java.srccode.toarray");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String[] lines = (String[]) toArray.t(obj);
		
		boolean packageFound = false;
		boolean classHeaderFound = false;
		int importLine = -1;
		
		for(int i=0;i<lines.length;i++)
		{
			String line = lines[i];
			
			if(!packageFound)
			{
				if(is_package(line)) packageFound = true;
			}
			else if(!classHeaderFound)
			{
				if(is_import(line)) importLine = i;
				else if(is_classHeader(line))
					classHeaderFound = true;
			}
		}
		return importLine;
	}
	
	
	private boolean is_package(String line)
	{return line.startsWith(ST_PACKAGE);}

	private boolean is_import(String line)
	{return line.startsWith(ST_IMPORT);}

	private boolean is_classHeader(String line)
	{return line.startsWith(ST_CLASSHEADER);}
}
