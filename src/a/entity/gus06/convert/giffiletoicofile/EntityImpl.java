package a.entity.gus06.convert.giffiletoicofile;

import a.framework.*;
import java.io.File;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160208";}


	private Service iconToIcoFile;
	private Service readGif;


	public EntityImpl() throws Exception
	{
		iconToIcoFile = Outside.service(this,"gus06.convert.icontoicofile");
		readGif = Outside.service(this,"gus06.file.read.gif.asicon");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File gif = (File) obj;
		Icon icon = (Icon) readGif.t(gif);
		return iconToIcoFile.t(icon);
	}
}
