package a.entity.gus06.convert.filetoinputstream;

import a.framework.*;
import java.io.File;
import java.io.FileInputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220616";}


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return new FileInputStream((File) obj);
	}
}