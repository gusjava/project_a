package a.entity.gus06.dirfile.find.abslocation;

import java.io.File;
import javax.swing.filechooser.FileSystemView;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150723";}




	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		
		StringBuffer b = new StringBuffer();
		
		while(!FileSystemView.getFileSystemView().isFileSystemRoot(f))
		{
			b.insert(0,"_"+f.getName());
			f = f.getParentFile();
		}
		
		b.insert(0,driverName(f));
		return b.toString();
	}
	
	
	
	
	private String driverName(File root)
	{
		String name = FileSystemView.getFileSystemView().getSystemDisplayName(root);
		
		String path = root.getAbsolutePath();
		if(path.endsWith(File.separator))
			path = path.substring(0,path.length()-1);
		
		String endPart = " ("+path+")";
		if(name.endsWith(endPart))
			name = name.substring(0,name.length()-endPart.length());
		return name;
	}
}
