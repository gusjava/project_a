package a.entity.gus06.dir.perform.generate.jar.from.gusscriptdir;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170401";}
	
	public static final String PACKAGE_ROOT = "gus06/resource/gus/gyem/";
	public static final String FILENAME_PROP = "prop.properties";
	public static final String DIRNAME_ROOT = "root";


	private Service jarModifier;
	private Service buildProp;
	private Service buildPaths;
	private Service findFilesGus;
	private Service findFilesGif;
	private Service readProp;
	private Service readText;

	public EntityImpl() throws Exception
	{
		jarModifier = Outside.service(this,"gus06.app.jarfile.modifier1");
		buildProp = Outside.service(this,"gus06.file.perform.generate.jar.from.gusscript.prop");
		buildPaths = Outside.service(this,"gus06.dir.listing.dirtopathmap");
		findFilesGus = Outside.service(this,"gus06.dir.listing0.ext.gus");
		findFilesGif = Outside.service(this,"gus06.dir.listing0.ext.gif");
		readProp = Outside.service(this,"gus06.file.read.properties");
		readText = Outside.service(this,"gus06.file.read.string");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		File jarFile1 = (File) o[1];
		
		
		Map map = new HashMap();
		
		Properties prop = (Properties) buildProp.g();
		
		File pFile = new File(dir,FILENAME_PROP);
		Properties prop1 = (Properties) readProp.t(pFile);
		if(prop1!=null) customizeProps(prop,prop1);
		
		
		map.put(PACKAGE_ROOT+"prop",prop);
		
		File[] scripts = (File[]) findFilesGus.t(dir);
		for(File script : scripts)
		{
			String path = PACKAGE_ROOT+"script/"+script.getName();
			map.put(path,script);
		}
		
		File[] gifs = (File[]) findFilesGif.t(dir);
		for(File gif : gifs)
		{
			String path = PACKAGE_ROOT+"icon/"+gif.getName();
			map.put(path,gif);
		}
		
		File rootDir = new File(dir,DIRNAME_ROOT);
		if(rootDir.isDirectory())
		{
			Map m = (Map) buildPaths.t(rootDir);
			Iterator it = m.keySet().iterator();
			while(it.hasNext())
			{
				String path0 = (String) it.next();
				File f = (File) m.get(path0);
				
				path0 = path0.replace(File.separator,"/");
				if(path0.startsWith("/")) path0 = path0.substring(1);
				
				String path = PACKAGE_ROOT+path0;
				map.put(path,f);
			}
		}
		
		jarModifier.p(new Object[]{jarFile1,map});
	}
	
	
	
	
	private void customizeProps(Properties prop, Properties prop1)
	{
		if(prop1.containsKey("init"))
		{
			String v = prop1.getProperty("init");
			if(v.equals("clear")) prop.clear();
		}
		
		Iterator it = prop1.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = prop1.getProperty(key);
			
			if(key.startsWith("+")) prop.put(key.substring(1),value);
			else if(key.startsWith("-")) prop.remove(key.substring(1));
		}
	}
}
