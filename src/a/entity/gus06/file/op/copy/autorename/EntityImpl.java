package a.entity.gus06.file.op.copy.autorename;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150606";}
	
	
	private Service perform;
	private Service autoRename;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus.x.file.op.copy");
		autoRename = Outside.service(this,"gus06.file.newfile.autorename2");
	}

	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File in = o[0];
		File out = (File) autoRename.t(o[1]);
		
		perform.p(new File[]{in,out});
	}
}