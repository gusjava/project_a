package a.entity.gus.y.addjavaimport1.lastimport.lineindex;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240712";}

	public static final String ST_PACKAGE = "package ";
	public static final String ST_IMPORT = "import ";
	public static final String ST_CLASSHEADER = "public class ";
	
	
	private Service toArray;

	public EntityImpl() throws Exception
	{toArray = Outside.service(this,"gus.x.javasrc.toarray");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String[] lines = (String[]) toArray.t(obj);
		
		boolean packageFound = false;
		boolean classHeaderFound = false;
		int lineIndex = -1;
		
		for(int i=0;i<lines.length;i++)
		{
			String line = lines[i];
			
			if(!packageFound)
			{
				if(is_package(line)) packageFound = true;
			}
			else if(!classHeaderFound)
			{
				if(is_import(line)) lineIndex = i;
				else if(is_classHeader(line))
					classHeaderFound = true;
			}
		}
		return lineIndex;
	}
	
	
	private boolean is_package(String line)
	{return line.startsWith(ST_PACKAGE);}

	private boolean is_import(String line)
	{return line.startsWith(ST_IMPORT);}

	private boolean is_classHeader(String line)
	{return line.startsWith(ST_CLASSHEADER);}
}
