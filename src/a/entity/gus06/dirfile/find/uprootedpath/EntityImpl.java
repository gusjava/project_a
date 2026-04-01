package a.entity.gus06.dirfile.find.uprootedpath;

import java.io.File;
import javax.swing.filechooser.FileSystemView;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161224";}


	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		File root = findRoot(f);
		
		String path1 = f.getAbsolutePath();
		String path2 = root.getAbsolutePath();
		
		return path1.substring(path2.length());
	}
	
	
	
	private File findRoot(File f)
	{
		while(!FileSystemView.getFileSystemView().isFileSystemRoot(f))
			f = f.getParentFile();
		return f;
	}
}
