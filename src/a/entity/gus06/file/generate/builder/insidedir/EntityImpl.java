package a.entity.gus06.file.generate.builder.insidedir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20151002";}


	private Service builder;


	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.file.generate.builder");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String size = (String) o[1];
		
		File file = new File(dir,size+".binary");
		builder.v(size,file);
		
		return file;
	}
	
	public void p(Object obj) throws Exception
	{t(obj);}
}
