package a.entity.gus06.sys.hddmanagement1.perform.scan;

import a.framework.*;
import java.util.Properties;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191126";}
	
	public static final String KEY_TIME = "time";
	public static final String KEY_NAME = "name";
	public static final String KEY_SERIAL = "serial";
	
	public static final String KEY_DIRS = "dirs";
	public static final String KEY_PATH = "path";
	public static final String KEY_USED = "used";
	public static final String KEY_USABLE = "usable";
	public static final String KEY_TOTAL = "total";
	public static final String KEY_FREE = "free";
	public static final String KEY_RATIO = "ratio";
	


	private Service saveProp;
	private Service buildNow;

	public EntityImpl() throws Exception
	{
		saveProp = Outside.service(this,"gus06.file.write.properties");
		buildNow = Outside.service(this,"gus06.time.now");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		File hdd = (File) o[1];
		String name = (String) o[2];
		String serial = (String) o[3];
		
		String now = (String) buildNow.g();
		String path = hdd.getAbsolutePath();
		
		long total = hdd.getTotalSpace();
		long free = hdd.getFreeSpace();
		long usable = hdd.getUsableSpace();
		
		if(total>0 && free>0 && !name.equals("") && !serial.equals(""))
		{
			long used = total-free;
			double ratio = (double)used/(double) total;
			String dirs = buildDirs(hdd);
			
			Properties prop = new Properties();
			
			prop.put(KEY_TIME,now);
			prop.put(KEY_NAME,name);
			prop.put(KEY_SERIAL,serial);
			prop.put(KEY_PATH,path);
			prop.put(KEY_DIRS,dirs);
			
			prop.put(KEY_TOTAL,""+total);
			prop.put(KEY_FREE,""+free);
			prop.put(KEY_USABLE,""+usable);
			prop.put(KEY_USED,""+used);
			prop.put(KEY_RATIO,""+ratio);
			
			File propFile = new File(dir,name+"_"+serial+".properties");
			saveProp.p(new Object[]{propFile,prop});
		}
	}
	
	
	private String buildDirs(File hdd)
	{
		List list = new ArrayList();
		File[] dd = hdd.listFiles();
		for(File d : dd) if(d.isDirectory())
		{
			String name = d.getName();
			String name0 = name.toLowerCase();
			if(!name0.equals("system volume information") && !name0.startsWith("$"))
			list.add(name);
		}
		Collections.sort(list);
		
		int nb = list.size();
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++)
		{
			b.append((String) list.get(i));
			if(i<nb-1) b.append(";");
		}
		return b.toString();
	}
}
