package a.entity.gus06.dir.listing0.files.names0.byext;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231107";}

	private Service getName;
	private Service children;

	public EntityImpl() throws Exception
	{
		getName = Outside.service(this,"gus.x.file.getname0");
		children = Outside.service(this,"gus06.dir.listing0.files.byext");
	}

	public Object t(Object obj) throws Exception
	{
		File[] f = (File[]) children.t(obj);
		
		String[] n = new String[f.length];
		for(int i=0;i<f.length;i++) n[i] = (String) getName.t(f[i]);
		return n;
	}
}