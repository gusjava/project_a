package a.entity.gus06.file.op.copy.replace.syncdate;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20170303";}


	private Service copy;

	public EntityImpl() throws Exception
	{
		copy = Outside.service(this,"gus06.file.op.copy.replace");
	}
	
		
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File in = o[0];
		File out = o[1];
		
		if(!in.isFile()) throw new Exception("Invalid input file: "+in);
		
		if(in.equals(out)) return false;
		if(!outOfSync(in,out)) return false;
		
		copy.p(new File[]{in,out});
		return true;
	}
	
	
	
	private boolean outOfSync(File in, File out)
	{
		if(!out.exists()) return true;
		return out.lastModified() < in.lastModified();
	}
}