package a.entity.gus06.file.string.perform.lines.apply.p;

import java.io.File;
import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161115";}


	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string.array.autodetect");
	}



	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		P p = (P) o[1];
		
		String[] lines = (String[]) readFile.t(file);
		for(String line:lines) p.p(line);
	}
}
