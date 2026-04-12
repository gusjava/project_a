package a.entity.gus06.java.srccode.adapttofile.changename;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20251219";}

	private Service getClassName;
	private Service readFile;
	private Service replacePackage;
	private Service replaceClassName;
	private Service writeFile;

	public EntityImpl() throws Exception
	{
		getClassName = Outside.service(this,"gus06.java.srcfile.filename.toclassname");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		replacePackage = Outside.service(this,"gus06.java.srccode.replacepackage");
		replaceClassName = Outside.service(this,"gus06.java.srccode.replaceclassname");
		writeFile = Outside.service(this,"gus06.file.write.string.cs.utf8");
	}
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	public boolean f(Object obj) throws Exception
	{
		File file = (File) obj;
		
		String targetClassName = (String) getClassName.t(file);
		if(targetClassName==null) return false;
		
		String javaSrc = (String) readFile.t(file);
		
		javaSrc = (String) replaceClassName.t(new Object[]{javaSrc, targetClassName});
		writeFile.p(new Object[]{file,javaSrc});
		return true;
	}
}
