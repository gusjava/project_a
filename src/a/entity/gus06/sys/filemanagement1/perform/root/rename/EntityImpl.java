package a.entity.gus06.sys.filemanagement1.perform.root.rename;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20191127";}
	
	public static final String MESSAGE = "Please, enter new name";


	private Service getInput;
	private Service getMessage;
	private Service renameFile;
	private Service renameDir;


	public EntityImpl() throws Exception
	{
		getInput = Outside.service(this,"gus06.input.text.dialog");
		getMessage = Outside.service(this,"gus06.input.text.dialog");
		renameFile = Outside.service(this,"gus06.file.op.rename");
		renameDir = Outside.service(this,"gus06.dir.op.rename");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String name0 = (String) o[1];
		
		File dirRoots = (File) ((R) engine).r("dirRoots");
		File dirScansR = (File) ((R) engine).r("dirScans");
		
		File fileRoot = new File(dirRoots,name0+".properties");
		File dirScans = new File(dirScansR,name0);
		
		
		if(!fileRoot.exists()) return false;
		
		String name1 = (String) getInput.t(new String[]{MESSAGE,name0});
		if(isEmpty(name1)) return false;
		
		File fileRoot1 = new File(dirRoots,name1+".properties");
		if(fileRoot1.exists()) return false;
		
		
		renameFile.p(new Object[]{fileRoot,fileRoot1.getName()});
		if(dirScans.exists())
		renameDir.p(new Object[]{dirScans,name1});
		
		return true;
	}
	
	
	private boolean isEmpty(String s)
	{return s==null || s.trim().equals("");}
}
