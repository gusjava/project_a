package a.entity.gus06.file.op.swap.content.toclipboard;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190723";}


	private Service perform;
	private Service clipboard;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.op.swap.content");
		clipboard = Outside.service(this,"gus06.clipboard.access.file");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File f1 = (File) obj;
		File f2 = (File) clipboard.g();
		if(f2==null) return;
		
		perform.p(new File[]{f1,f2});
	}
}
