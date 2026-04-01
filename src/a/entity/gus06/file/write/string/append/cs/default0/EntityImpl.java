package a.entity.gus06.file.write.string.append.cs.default0;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.io.FileOutputStream;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160415";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String text = (String) o[1];
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		FileOutputStream fos = new FileOutputStream(file,true);
		PrintStream p = new PrintStream(fos,true);
		p.print(text);
		p.close();
	}
}
