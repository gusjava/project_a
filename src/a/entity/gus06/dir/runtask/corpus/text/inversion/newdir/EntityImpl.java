package a.entity.gus06.dir.runtask.corpus.text.inversion.newdir;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150922";}


	private Service listing;
	private Service autoRename;
	private Service invertOp;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.txt");
		autoRename = Outside.service(this,"gus06.file.newfile.autorename2");
		invertOp = Outside.service(this,"gus06.file.string.todir.invert");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File dir1 = (File) autoRename.t(dir);
		dir1.mkdirs();
		
		File[] ff = (File[]) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		for(File f:ff)
		{
			invertOp.p(new File[]{f,dir1});
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
}
