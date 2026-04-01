package a.entity.gus06.sys.dirdoubloon1.tool.files.sort.bypath;

import a.framework.*;
import java.util.List;
import java.util.Collections;
import java.io.File;
import java.util.Comparator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20221221";}
	
	
	public void p(Object obj) throws Exception
	{
		List list = (List) obj;
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2)
			{
				String path1 = ((File) o1).getAbsolutePath();
				String path2 = ((File) o2).getAbsolutePath();
				return path1.compareTo(path2);
			}
		}); 
	}
}
