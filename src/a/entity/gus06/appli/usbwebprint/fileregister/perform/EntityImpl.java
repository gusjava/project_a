package a.entity.gus06.appli.usbwebprint.fileregister.perform;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl extends S1 implements Entity, P {

	public String creationDate() {return "20140915";}

	public static final boolean MODE_COPY = true;
	public static final String FILENAME_READY = "READY";
	
	public static final String KEY_FILE = "file";
	public static final String KEY_ROOT = "root";
	public static final String KEY_EXAM_PATIENT = "exam_patient";
	public static final String KEY_EXAM_TIME = "exam_time";
	
	
	
	
	private Service getNextDir;
	private Service isLocked;
	private Service fileMove;
	private Service fileCopy;
	
	private PrintStream out;
	


	public EntityImpl() throws Exception
	{
		getNextDir = Outside.service(this,"gus06.appli.usbwebprint.fileregister.nextsubdir");
		isLocked = Outside.service(this,"gus.x.file.filter.islocked");
		fileMove = Outside.service(this,"gus06.file.op.move");
		fileCopy = Outside.service(this,"gus06.file.op.copy");
		
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String patient = (String) get(map,KEY_EXAM_PATIENT);
		String time = (String) get(map,KEY_EXAM_TIME);
		File file0 = (File) get(map,KEY_FILE);
		File root = (File) get_(map,KEY_ROOT);
		
		if(!file0.exists())
		{
			out.println("Le fichier "+file0+" n'est plus accessible");
			return;
		}
		
		if(isLocked.f(file0))
		{
			out.println("Le fichier "+file0+" est v\u00e9rouill\u00e9");
			return;
		}
		
		
		String examId = time+"_"+patient;
		File dir = (File) getNextDir.t(examId);
		File file1 = new File(dir,file0.getName());
		
		out.println("extracting file: "+file0);
		
		if(MODE_COPY) copy(file0,file1);
		else move(file0,file1);
		
		out.println("extraction done");
		
		new File(dir,FILENAME_READY).createNewFile();
		newFilePerformed();
	}
	
	
	
	
	
	
	
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key))
			throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
	
	private Object get_(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	
	
	
	
	private void move(File f1, File f2) throws Exception
	{fileMove.p(new File[]{f1,f2});}
	
	
	private void copy(File f1, File f2) throws Exception
	{fileCopy.p(new File[]{f1,f2});}
	
	
	
	
	private void newFilePerformed()
	{send(this,"newFilePerformed()");}
}
