package a.entity.gus06.java.compiler.v3.handleotherfiles;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200304";}
	
	public static final F FILTER = new F(){
		public boolean f(Object obj) throws Exception
		{
			File f = (File) obj;
			if(!f.isFile()) return false;
			return !f.getName().endsWith(".java");
		}
	};


	private Service copy;

	public EntityImpl() throws Exception
	{
		copy = Outside.service(this,"gus06.dir.op.copy.each.filtered.replace.syncdate");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		copy.p(new Object[]{o[0],o[1],FILTER});
	}
}
