package a.entity.gus.x.app.classpath.str;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260409";}

	public Object t(Object obj) throws Exception
	{
		File[] files = (File[]) obj;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<files.length; i++)
		{
			if(i>0) sb.append(File.pathSeparator);
			sb.append(files[i].getAbsolutePath());
		}
		return sb.toString();
	}
}
