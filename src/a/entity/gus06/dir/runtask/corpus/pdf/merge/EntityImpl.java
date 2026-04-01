package a.entity.gus06.dir.runtask.corpus.pdf.merge;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250423";}

	private Service mergeOp;
	
	public EntityImpl() throws Exception
	{
		mergeOp = Outside.service(this,"gus06.sys.pdfmerge.fromfiles");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		mergeOp.p(dir);
		if(progress!=null) ((E)progress).e();
	}
}