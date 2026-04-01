package a.entity.gus06.file.icon.os.asimage;

import java.io.File;
import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191217";}

	
	private Service iconToImage;

	public EntityImpl() throws Exception
	{
		iconToImage = Outside.service(this,"gus06.convert.icontoimage");
	}


	public Object t(Object obj) throws Exception
	{
		File file = (File)obj;
		if(!file.exists()) return null;
		Icon icon = FileSystemView.getFileSystemView().getSystemIcon(file);
		return iconToImage.t(icon);
	}
}
