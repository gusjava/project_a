package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.load;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141001";}
	
	public static final String DIRNAME = "template";


	private Service dirLocator;
	private Service readFile;


	public EntityImpl() throws Exception
	{
		dirLocator = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.dir.locator");
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String name = (String) o[1];
		
		File[] d = (File[]) dirLocator.t(new Object[]{mr,DIRNAME});
		
		File file0 = new File(d[0],name+".txt");
		File file1 = new File(d[1],name+".txt");
		
		if(file1.isFile()) return readFile.t(file1);
		if(file0.isFile()) return readFile.t(file0);
		
		file1.getParentFile().mkdirs();
		file1.createNewFile();
		return "Template "+name+" has just been added";
	}
}
