package a.entity.gus06.dir.runtask.corpus.filesdico1.scandir.choose;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150723";}
	
	public static final String KEY_FILEPATH = "file.path";


	private Service runtask2;
	private Service chooseDir;
	private Service abslocation;
	
	public EntityImpl() throws Exception
	{
		runtask2 = Outside.service(this,"gus06.dir.runtask2.report.filesdico1");
		chooseDir = Outside.service(this,"gus06.file.choose.open.dir");
		abslocation = Outside.service(this,"gus06.dirfile.find.abslocation");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File targetDir = (File) chooseDir.g();
		if(targetDir==null) return;
		
		File f = new File(dir,nameForRoot(targetDir));
		Map map = new HashMap();
		map.put(KEY_FILEPATH,f.getAbsolutePath());
		
		runtask2.p(new Object[]{map,targetDir,progress,interrupt});
	}
	
	
	
	
	private String nameForRoot(File targetDir) throws Exception
	{
		String location = (String) abslocation.t(targetDir);
		return now()+"_"+location+".txt";
	}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	private String now() throws Exception
	{return sdf.format(new Date());}
}
