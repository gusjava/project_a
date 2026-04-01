package a.entity.gus06.x.file.string.read;

import java.io.File;
import java.io.FileReader;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251113";}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		FileReader fr = new FileReader(file);
		char[] a = new char[(int) file.length()];
		fr.read(a, 0, (int) file.length());
		fr.close();
		return new String(a);
	}
}