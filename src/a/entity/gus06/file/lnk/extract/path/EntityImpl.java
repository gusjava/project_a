package a.entity.gus06.file.lnk.extract.path;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141210";}


	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		if(file==null) return null;
		if(!file.exists()) return file;
		if(file.length()==0) return file;
		
		if(isLnkFile(file)) return extractPath(file);
		return file;
	}
	
	
	private File extractPath(File file) throws Exception
	{
		LnkParser parser = new LnkParser(file);
		return new File(parser.getTargetFile());
	}
	
	
	private boolean isLnkFile(File file)
	{return file.isFile() && file.getName().toLowerCase().endsWith(".lnk");}
}
