package a.entity.gus06.dir.runtask.zip.timestamped.yyyymmdd;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150819";}
	
	public static final String FORMAT = "yyyyMMdd";
	
	private SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
	private String now() {return sdf.format(new Date());}
	


	private Service runZip;
	
	public EntityImpl() throws Exception
	{runZip = Outside.service(this,"gus06.file.zip.run.zip");}
	

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File parent = dir.getParentFile();
		String name = dir.getName();
		
		File zip = new File(parent,now()+"_"+name+".zip");
		
		runZip.p(new Object[]{dir,zip,progress,interrupt});
	}
}
