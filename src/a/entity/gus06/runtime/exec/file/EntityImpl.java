package a.entity.gus06.runtime.exec.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180220";}
	
	public Object t(Object obj) throws Exception
	{return process((File) obj);}
	
	private Process process(File f) throws Exception
	{return Runtime.getRuntime().exec(p(f));}
	
	private String p(File f)
	{return "\""+f.getAbsolutePath()+"\"";}
}
