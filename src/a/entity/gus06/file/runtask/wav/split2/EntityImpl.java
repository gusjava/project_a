package a.entity.gus06.file.runtask.wav.split2;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260201";}

	private Service splitOp;
	private Service getInput;
	
	public EntityImpl() throws Exception
	{
		splitOp = Outside.service(this,"gus06.file.wav.split.todir");
		getInput = Outside.service(this,"gus06.file.wav.split.todir.input");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		double[] data = (double[]) getInput.g();
		if(data==null) return;
		
		File dir = new File(file.getAbsolutePath()+"_split");
		dir.mkdirs();
		
		if(progress!=null) ((V)progress).v("size","1");
		splitOp.p(new Object[]{file,dir, data});
		if(progress!=null) ((E)progress).e();
	}
}
