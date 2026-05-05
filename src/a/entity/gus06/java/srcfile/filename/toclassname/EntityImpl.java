package a.entity.gus06.java.srcfile.filename.toclassname;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251219";}

	private Service getName0Ext;

	public EntityImpl() throws Exception
	{
		getName0Ext = Outside.service(this,"gus.x.file.getname0ext");
	}
	
	public Object t(Object obj) throws Exception
	{
		File srcFile = (File) obj;
		String[] n = (String[]) getName0Ext.t(srcFile);
		String name0 = n[0];
		String ext = n[1];
		
		if(!ext.equals("java")) return null;
		if(!name0.matches("[A-Z][A-Za-z0-9_$]*")) return null;
		return name0;
	}
}
