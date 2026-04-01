package a.entity.gus06.appli.usbwebprint.exam.findinfo;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140915";}

	public static final String PATIENT_INCONNU = "PATIENT_INCONNU";
	

	private Service extractTime;


	public EntityImpl() throws Exception
	{extractTime = Outside.service(this,"gus06.appli.usbwebprint.exam.extracttime");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File[] t = (File[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		File file = (File) t[0];
		File root = (File) t[1];
		
		if(root==null) throw new Exception("Invalid root dir: null");
		if(!root.exists()) throw new Exception("Invalid root dir: "+root+" does not exist");
		if(!root.isDirectory()) throw new Exception("Invalid root dir: "+root+" is not a directory");
		
		if(file==null) throw new Exception("Invalid data file: null");
		if(!file.exists()) throw new Exception("Invalid data file: "+file+" does not exist");
		if(!file.isFile()) throw new Exception("Invalid data file: "+file+" is not a file");
		
		int deep = findDeep(file,root);
		
		if(deep>=2) return findInfo_deep2(file);
		if(deep==1) return findInfo_deep1(file);
		if(deep==0) return findInfo_deep0(file);
		
		throw new Exception("File outside detection range: "+file);
	}
	
	
	
	
	
	
	private String[] findInfo_deep2(File file) throws Exception
	{
		File dir1 = file.getParentFile();
		File dir2 = dir1.getParentFile();

		String patient = dir2.getName();
		String time = extractTime(dir1);

		return new String[]{patient,time};
	}

	
	
	private String[] findInfo_deep1(File file) throws Exception
	{
		File dir1 = file.getParentFile();
		
		String patient = dir1.getName();
		String time = extractTime(dir1);

		return new String[]{patient,time};
	}


	
	private String[] findInfo_deep0(File file) throws Exception
	{
		String patient = PATIENT_INCONNU;
		String time = extractTime(file);

		return new String[]{patient,time};
	}
	
	
	
	
	
	


	
	private String extractTime(File dir) throws Exception
	{return (String) extractTime.t(dir);}
	
	
	
	
	
	private int findDeep(File file, File root)
	{
		File p = file.getParentFile();
		int deep = 0;
		while(p!=null && !p.equals(root))
		{
			deep++;
			p = p.getParentFile();
		}
		if(p==null) return -1;
		return deep;
	}
}
