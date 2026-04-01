package a.entity.gus06.file.link_gus.create.shortcut1;

import java.io.File;
import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250625";}
	

	public void p(Object obj) throws Exception
	{
		File[] f = (File[]) obj;
		if(f.length==2) {createShortcut(f[0],f[1]);return;}
		
		throw new Exception("Wrong data number: "+f.length);
	}
	
	
	private void createShortcut(File target, File lnk) throws Exception
	{
		PrintStream p = new PrintStream(lnk);
		p.print(target.getAbsolutePath());
		p.close();
	}
}
