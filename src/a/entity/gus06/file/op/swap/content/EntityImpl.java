package a.entity.gus06.file.op.swap.content;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190723";}


	private Service copy;
	private Service move;
	private Service random;


	public EntityImpl() throws Exception
	{
		copy = Outside.service(this,"gus06.file.op.copy.replace");
		move = Outside.service(this,"gus06.file.op.move.replace");
		random = Outside.service(this,"gus06.data.generate.string.random.number14");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		swapContents(o[0],o[1]);
	}
	
	
	private void swapContents(File f1, File f2) throws Exception
	{
		String r = (String) random.g();
		File f1_ = new File(f1.getAbsolutePath()+"_"+r);
		
		copy.p(new File[]{f2,f1_});
		copy.p(new File[]{f1,f2});
		move.p(new File[]{f1_,f1});
	}
}
