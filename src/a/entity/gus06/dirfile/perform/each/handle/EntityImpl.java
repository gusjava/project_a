package a.entity.gus06.dirfile.perform.each.handle;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161115";}


	private Service handleDir;
	private Service handleProp;
	private Service handleTxt;
	
	private Service isProp;
	private Service isTxt;


	public EntityImpl() throws Exception
	{
		handleDir = Outside.service(this,"gus06.dir.perform.each");
		handleProp = Outside.service(this,"gus06.file.properties.perform.each.keyvalue.handle");
		handleTxt = Outside.service(this,"gus06.file.string.perform.lines.apply.p");
		
		isProp = Outside.service(this,"gus06.file.filter.mime.istype.text.properties");
		isTxt = Outside.service(this,"gus06.file.filter.mime.istype.text.plain");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File input = (File) o[0];
		
		if(input.isDirectory()) {handleDir.p(obj);return;}
		if(isProp.f(input)) {handleProp.p(obj);return;}
		if(isTxt.f(input)) {handleTxt.p(obj);return;}
		
		throw new Exception("Invalid file type: "+input);
	}
}
