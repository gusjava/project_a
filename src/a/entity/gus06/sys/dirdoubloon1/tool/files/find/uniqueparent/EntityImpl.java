package a.entity.gus06.sys.dirdoubloon1.tool.files.find.uniqueparent;

import a.framework.*;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221223";}

	
	public Object t(Object obj) throws Exception
	{
		List files = (List) obj;
		if(files.isEmpty()) return null;
		
		File parent = ((File) files.get(0)).getParentFile();
		int nb = files.size();
		for(int i=1;i<nb;i++)
		{
			File f = (File) files.get(i);
			if(!f.getParentFile().equals(parent)) return null;
		}
		return parent;
	}
}
