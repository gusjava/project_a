package a.entity.gus06.file.write.string.map1.utf8;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151024";}


	private Service printer;

	

	public EntityImpl() throws Exception
	{
		printer = Outside.service(this,"gus06.data.coltree.printer");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object data = o[1];
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		PrintStream p = new PrintStream(file,"UTF-8");
		printer.p(new Object[]{data,p});
		p.close();
	}
}
