package a.entity.gus06.runtime.exec1.cmd.inside;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170313";}


	private Service prepare;
	
	public EntityImpl() throws Exception
	{
		prepare = Outside.service(this,"gus06.runtime.exec.resolveexe");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String cmd = (String) prepare.t(o[0]);
		File root = (File) o[1];
		
		if(root==null) return Runtime.getRuntime().exec(cmd);
		if(!root.isDirectory()) throw new Exception("Invalid root dir: "+root);
		
		return Runtime.getRuntime().exec(cmd,null,root);
	}
}
