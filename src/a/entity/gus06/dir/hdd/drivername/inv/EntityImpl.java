package a.entity.gus06.dir.hdd.drivername.inv;

import java.io.File;
import javax.swing.filechooser.FileSystemView;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180505";}

	
	public Object t(Object obj) throws Exception
	{
		String name = (String) obj;
		if(name==null) return null;
		
		File[] roots = File.listRoots();
		for(File root : roots)
		{
			String name1 = FileSystemView.getFileSystemView().getSystemDisplayName(root);
			if(name1.startsWith(name+" ")) return root;
		}
		return null;
	}
}
