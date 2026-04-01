package a.entity.gus06.file.perform.keepall;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160412";}


	private Service checkStringFile;
	private Service handleStringFile;


	public EntityImpl() throws Exception
	{
		checkStringFile = Outside.service(this,"gus06.file.string.check");
		handleStringFile = Outside.service(this,"gus06.file.string.perform.lines.keep.f");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		
		if(checkStringFile.f(file)) {handleStringFile.p(obj);return;}
		
		throw new Exception("Unsupported file: "+file);
	}
}
