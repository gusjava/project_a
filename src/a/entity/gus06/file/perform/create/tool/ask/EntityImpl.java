package a.entity.gus06.file.perform.create.tool.ask;

import a.framework.*;
import java.io.File;
import java.util.Properties;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20150524";}
	
	public static final String PATH_ROOT = "path.root";


	private Service input;
	private Service getFile;
	private Service writeProp;


	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog");
		getFile = Outside.service(this,"gus06.clipboard.access.file");
		writeProp = Outside.service(this,"gus06.file.write.properties");
	}
	
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		
		String name = (String) input.t("Please, enter tool's name:");
		if(name==null || name.equals("")) return null;
		
		if(!name.endsWith(".tool")) name = name+".tool";
		
		File file = new File(dir,name);
		file.createNewFile();
		
		
		File root = (File) getFile.g();
		if(root!=null)
		{
			Properties p = new Properties();
			p.setProperty(PATH_ROOT,root.getAbsolutePath());
			writeProp.p(new Object[]{file,p});
		}
		
		return file;
	}
}
