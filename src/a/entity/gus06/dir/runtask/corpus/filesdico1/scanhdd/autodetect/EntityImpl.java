package a.entity.gus06.dir.runtask.corpus.filesdico1.scanhdd.autodetect;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150711";}
	
	public static final String KEY_FILEPATH = "file.path";


	private Service runtask2;
	private Service autoDetectDrive;
	private Service driverName;
	
	public EntityImpl() throws Exception
	{
		runtask2 = Outside.service(this,"gus06.dir.runtask2.report.filesdico1");
		autoDetectDrive = Outside.service(this,"gus06.dir.hdd.detectnewhdd");
		driverName = Outside.service(this,"gus06.dir.hdd.drivername");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File root = (File) autoDetectDrive.g();
		if(root==null) return;
		
		File f = new File(dir,nameForRoot(root));
		Map map = new HashMap();
		map.put(KEY_FILEPATH,f.getAbsolutePath());
		
		runtask2.p(new Object[]{map,root,progress,interrupt});
	}
	
	
	
	
	private String nameForRoot(File root) throws Exception
	{
		String name = (String) driverName.t(root);
		return now()+"_"+name+".txt";
	}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	private String now() throws Exception
	{return sdf.format(new Date());}
}
