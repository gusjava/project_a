package a.entity.gus06.file.perform.generate.jar.from.gusscript;

import a.framework.*;
import java.io.File;
import java.util.jar.Manifest;
import java.util.jar.JarInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.jar.JarOutputStream;
import java.io.FileOutputStream;
import java.util.jar.JarEntry;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161014";}
	
	public static final String ROOT = "gus06/resource/gus/gyem/";
	


	private Service jarModifier;
	private Service buildProp;

	public EntityImpl() throws Exception
	{
		jarModifier = Outside.service(this,"gus06.app.jarfile.modifier1");
		buildProp = Outside.service(this,"gus06.file.perform.generate.jar.from.gusscript.prop");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File script = (File) o[0];
		File jarFile1 = (File) o[1];
		
		Properties prop = (Properties) buildProp.g();
		
		Map map = new HashMap();
		map.put(ROOT+"prop",prop);
		map.put(ROOT+"script/main.gus",script);
		
		jarModifier.p(new Object[]{jarFile1,map});
	}
}
