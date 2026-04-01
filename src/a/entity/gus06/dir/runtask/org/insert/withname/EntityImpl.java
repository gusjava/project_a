package a.entity.gus06.dir.runtask.org.insert.withname;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150621";}


	private Service findCommon;
	private Service autoRename;
	
	
	public EntityImpl() throws Exception
	{
		findCommon = Outside.service(this,"gus06.data.compare.string.common.firstpart");
		autoRename = Outside.service(this,"gus06.file.newfile.autorename2");
	}


	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		
		File[] ff = dir.listFiles();
		String[] nn = names(ff);
		String common = (String) findCommon.t(nn);
		int length = common.length();
		
		File dir1 = new File(dir,buildDirName(common));
		dir1 = (File) autoRename.t(dir1);
		dir1.mkdirs();
		
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		for(File f:ff)
		{
			String name = f.getName();
			String newName = name.substring(length);
			
			File f1 = new File(dir1,newName);
			rename(f,f1);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	
	private void rename(File f, File f1) throws Exception
	{
		boolean r = f.renameTo(f1);
		if(!r) throw new Exception("Failed to rename path: "+f+" into path: "+f1);
	}
	
	
	
	private String[] names(File[] ff)
	{
		String[] nn = new String[ff.length];
		for(int i=0;i<ff.length;i++) nn[i] = ff[i].getName();
		return nn;
	}
	
	
	private String buildDirName(String s)
	{
		while(s.endsWith(" ") || s.endsWith("_") || s.endsWith("-") || s.endsWith("."))
			s = s.substring(0,s.length()-1);
		return s.equals("")?"newdir":s;
	}
}
