package a.entity.gus06.sys.pdfmerge.fromfiles;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20161005";}

	
	private Service engine;
	
	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.pdfmerge.engine");
	}
	

	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File[] files = toFiles(obj);
		if(files.length==0) return null;
		
		File root = files[0].getParentFile();
		File pdfFile = new File(root.getAbsolutePath()+".pdf");
		
		engine.v("pdfFile",pdfFile);
		engine.v("files",files);
		engine.e();
		
		return pdfFile;
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
		return path.listFiles();
	}
}
