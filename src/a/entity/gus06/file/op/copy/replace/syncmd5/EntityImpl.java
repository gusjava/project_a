package a.entity.gus06.file.op.copy.replace.syncmd5;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20170527";}


	private Service copy;
	private Service checkSame;

	public EntityImpl() throws Exception
	{
		copy = Outside.service(this,"gus06.file.op.copy.replace");
		checkSame = Outside.service(this,"gus06.file.doubloon.check");
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
	
	
	
	private boolean outOfSync(File in, File out) throws Exception
	{
		if(!out.exists()) return true;
		return !checkSame.f(new File[]{in,out});
	}
}
