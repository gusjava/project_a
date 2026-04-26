package a.entity.gus.x.file.icon.os;

import java.io.File;
import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231128";}

	public Object t(Object obj) throws Exception
	{return findIcon((File) obj);}

	private Icon findIcon(File f)
	{
		if (f == null || !f.exists()) return null;
		return FileSystemView.getFileSystemView().getSystemIcon(f);
	}
}