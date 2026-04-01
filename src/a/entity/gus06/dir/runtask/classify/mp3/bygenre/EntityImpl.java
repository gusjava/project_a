package a.entity.gus06.dir.runtask.classify.mp3.bygenre;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191226";}


	private Service fileToData;
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		fileToData = Outside.service(this,"gus06.file.info.mp3.genre");
		perform = Outside.service(this,"gus06.dir.runtask0.classify.perform");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		perform.p(new Object[]{dir,progress,interrupt,fileToData});
	}
}
