package a.entity.gus.x.file.string.read.v2;

import java.io.File;
import java.io.FileReader;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231128";}
	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		if(f==null || !f.isFile()) return null;
		
		FileReader fr = new FileReader(f);
		char[] a = new char[(int) f.length()];
		fr.read(a, 0, (int) f.length());
		fr.close();
		
		return new String(a);
	}
}
