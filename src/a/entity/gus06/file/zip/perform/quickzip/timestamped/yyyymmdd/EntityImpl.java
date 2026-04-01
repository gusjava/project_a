package a.entity.gus06.file.zip.perform.quickzip.timestamped.yyyymmdd;

import a.framework.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180131";}
	
	public static final String FORMAT = "yyyyMMdd";
	
	private SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
	private String now() {return sdf.format(new Date());}


	private Service runZip;
	private Service getName;
	
	public EntityImpl() throws Exception
	{
		runZip = Outside.service(this,"gus06.file.zip.run.zip");
		getName = Outside.service(this,"gus06.file.getname0");
	}


	
	public void p(Object obj) throws Exception
	{
		File input = (File) obj;
		
		File parent = input.getParentFile();
		String name = (String) getName.t(input);
		File zipFile = new File(parent,now()+"_"+name+".zip");
		
		runZip.p(new Object[]{input,zipFile,null,null});
	}
}