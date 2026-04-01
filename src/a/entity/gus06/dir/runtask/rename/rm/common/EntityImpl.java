package a.entity.gus06.dir.runtask.rename.rm.common;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150620";}


	private Service listing;
	private Service findCommon;
	
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0");
		findCommon = Outside.service(this,"gus06.data.compare.string.common.firstpart");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		
		File[] ff = (File[]) listing.t(dir);
		String[] nn = names(ff);
		String common = (String) findCommon.t(nn);
		
		if(common.equals("")) return;
		
		int length = common.length();
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		for(File f:ff)
		{
			String name = f.getName();
			String newName = name.substring(length);
			
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
	
	
	
	private String[] names(File[] ff)
	{
		String[] nn = new String[ff.length];
		for(int i=0;i<ff.length;i++) nn[i] = ff[i].getName();
		return nn;
	}
}
