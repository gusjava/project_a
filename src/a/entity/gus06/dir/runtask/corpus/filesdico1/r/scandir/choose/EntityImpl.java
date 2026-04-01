package a.entity.gus06.dir.runtask.corpus.filesdico1.r.scandir.choose;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170109";}


	private Service runtask0;
	private Service chooseDir;
	
	public EntityImpl() throws Exception
	{
		runtask0 = Outside.service(this,"gus06.dir.runtask0.corpus.filesdico1.r.scandir.choose");
		chooseDir = Outside.service(this,"gus06.file.choose.open.dir");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File targetDir = (File) chooseDir.g();
		if(targetDir==null) return;
		
		runtask0.p(new Object[]{dir,progress,interrupt,targetDir});
	}
}
