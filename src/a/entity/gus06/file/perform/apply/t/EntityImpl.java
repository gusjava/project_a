package a.entity.gus06.file.perform.apply.t;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160412";}


	private Service checkPropFile;
	private Service checkStringFile;
	private Service checkImageFile;
	
	private Service handlePropFile;
	private Service handleStringFile;
	private Service handleImageFile;


	public EntityImpl() throws Exception
	{
		checkPropFile = Outside.service(this,"gus06.file.properties.check");
		checkStringFile = Outside.service(this,"gus06.file.string.check");
		checkImageFile = Outside.service(this,"gus06.file.image.check");
		
		handlePropFile = Outside.service(this,"gus06.file.properties.perform.apply.t");
		handleStringFile = Outside.service(this,"gus06.file.string.perform.apply.t");
		handleImageFile = Outside.service(this,"gus06.file.image.perform.apply.t");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		
		if(checkPropFile.f(file)) {handlePropFile.p(obj);return;}
		if(checkStringFile.f(file)) {handleStringFile.p(obj);return;}
		if(checkImageFile.f(file)) {handleImageFile.p(obj);return;}
		
		throw new Exception("Unsupported file: "+file);
	}
}
