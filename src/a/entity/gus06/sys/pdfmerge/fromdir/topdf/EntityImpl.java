package a.entity.gus06.sys.pdfmerge.fromdir.topdf;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161005";}

	
	private Service engine;
	
	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.pdfmerge.engine");
	}
	
	

	public void p(Object obj) throws Exception
	{
		File[] f = (File[]) obj;
		if(f.length!=2) throw new Exception("Wrong data number: "+f.length);
		
		File[] files = toFiles(f[0]);
		File pdfFile = f[1];
		
		engine.v("pdfFile",pdfFile);
		engine.v("files",files);
		engine.e();
	}
	
	
	
	
	
	private File[] toFiles(Object obj) throws Exception
	{
		if(obj instanceof File[]) return (File[]) obj;
		if(obj instanceof File) return toFiles2((File)obj);
			
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private File[] toFiles2(File path) throws Exception
	{
		if(path.isFile()) return new File[]{path};
		//return sort(path.listFiles());
		return path.listFiles();
	}
	
	
	private File[] sort(File[] files)
	{
		Comparator comp = new Comparator() {  
			public int compare(Object o1, Object o2)
			{  
				String n1 = ((File) o1).getName();
				String n2 = ((File) o2).getName();
				return n1.compareTo(n2);  
			}};
			
		Arrays.sort(files,comp);
		return files;
	}
}
