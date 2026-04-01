package a.entity.gus06.dir.runtask.rename.withindex;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150620";}


	private Service listing;
	private Service getExtension;
	
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0");
		getExtension = Outside.service(this,"gus06.file.getextension");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		
		File[] ff = (File[]) listing.t(dir);
		int number = ff.length;
		int l = (""+number).length();
		
		if(progress!=null) ((V)progress).v("size",""+number);
		
		for(int i=0;i<number;i++)
		{
			File f = ff[i];
			String ext = (String) getExtension.t(f);
			String newName = format(i,l)+"."+ext;
			
			File f1 = new File(dir,newName);
			rename(f,f1);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	
	
	private void rename(File file0, File file1) throws Exception
	{
		boolean r = file0.renameTo(file1);
		if(!r) throw new Exception("Failed to rename file: "+file0+" to file "+file1);
	}
	
	
	
	private String format(int i, int length)
	{
		String s = ""+i;
		while(s.length()<length) s = "0"+s;
		return s;
	}
}
