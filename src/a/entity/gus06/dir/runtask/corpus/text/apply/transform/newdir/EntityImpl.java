package a.entity.gus06.dir.runtask.corpus.text.apply.transform.newdir;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151014";}
	
	
	private Service listing;
	private Service autoRename;
	private Service chooser;
	private Service op;
	
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.txt");
		autoRename = Outside.service(this,"gus06.file.newfile.autorename2");
		chooser = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_h.tool.perform.chooser");
		op = Outside.service(this,"gus06.file.string.perform2.apply.t");
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
		
		int size = ff.length;
		if(progress!=null) ((V)progress).v("size",""+size);
		
		T t = (T) chooser.g();
		if(t==null) {interrupt.add("*");return;}
		
		for(File f:ff)
		{
			File f1 = new File(dir1,f.getName());
			op.p(new Object[]{f,f1,t});
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
}