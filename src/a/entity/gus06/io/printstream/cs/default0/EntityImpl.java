package a.entity.gus06.io.printstream.cs.default0;

import java.io.File;
import java.io.PrintStream;
import a.framework.*;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220617";}




	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		return new PrintStream(f);
	}
}