package a.entity.gus06.appli.usbwebprint.fileregister.preparemap;

import a.framework.*;
import java.io.File;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140915";}

	public static final String KEY_FILE = "file";
	public static final String KEY_ROOT = "root";
	public static final String KEY_EXAM_PATIENT = "exam_patient";
	public static final String KEY_EXAM_TIME = "exam_time";
	
	

	private Service examInfo;

	public EntityImpl() throws Exception
	{examInfo = Outside.service(this,"gus06.appli.usbwebprint.exam.findinfo");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File[] f = (File[]) obj;
		if(f.length!=2) throw new Exception("Wrong data number: "+f.length);
		
		String[] t = (String[]) examInfo.t(f);
		if(t.length!=2) throw new Exception("Wrong data number (2): "+t.length);

		HashMap map = new HashMap();
		
		map.put(KEY_FILE,f[0]);
		map.put(KEY_ROOT,f[1]);
		map.put(KEY_EXAM_PATIENT,t[0]);
		map.put(KEY_EXAM_TIME,t[1]);
		
		return map;
	}
}
