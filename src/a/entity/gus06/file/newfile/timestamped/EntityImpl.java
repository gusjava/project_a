package a.entity.gus06.file.newfile.timestamped;

import a.framework.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200205";}

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		String name = file.getName();
		File dir = file.getParentFile();
		return new File(dir,now()+"_"+name);
	}
	
	private String now() throws Exception
	{return sdf.format(new Date());}
}
