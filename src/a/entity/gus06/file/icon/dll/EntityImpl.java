package a.entity.gus06.file.icon.dll;

import java.io.File;
import java.util.HashMap;
import javax.swing.Icon;
import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150615";}

	
	private Service dllFileType;
	
	private Map map1;
	private Map map2;
	
	private Icon icon_dll_32;
	private Icon icon_dll_64;
	private Icon icon_dll_unknown;
	
	
	
	public EntityImpl() throws Exception
	{
		dllFileType = Outside.service(this,"gus06.env.windows.dll.findtype");
		icon_dll_32 = (Icon) Outside.resource(this,"icon#FILE_dll2_32bit");
		icon_dll_64 = (Icon) Outside.resource(this,"icon#FILE_dll2_64bit");
		icon_dll_unknown = (Icon) Outside.resource(this,"icon#FILE_dll2_unknowntype");
		
		map1 = new HashMap();
		map2 = new HashMap();
	}
		
		
	

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		String t0 = ""+file.lastModified();
		
		if(!map1.containsKey(file))
		{
			map1.put(file,findIcon(file));
			map2.put(file,t0);
		}
		
		String t = (String) map2.get(file);
		if(!t.equals(t0))
		{
			map1.put(file,findIcon(file));
			map2.put(file,t0);
		}
		
		return (Icon) map1.get(file);
	}
	
	
	
	
	
	private Icon findIcon(File file)
	{
		try
		{
			String type = (String) dllFileType.t(file);
			if(type.equals("32")) return icon_dll_32;
			if(type.equals("64")) return icon_dll_64;
		}
		catch(Exception e)
		{Outside.err(this,"findIcon(File)",e);}
		return icon_dll_unknown;
	}
}
