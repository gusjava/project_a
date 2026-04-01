package a.entity.gus06.sys.filemanagement1.scan.store.preview;

import a.framework.*;
import java.io.File;
import java.net.URL;
import java.util.Map;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20191128";}
	
	public static final String CONFIG_MODE = "scan.preview.mode";


	private Service checkMode;
	private Service generate;

	public EntityImpl() throws Exception
	{
		checkMode = Outside.service(this,"gus06.sys.filemanagement1.scan.store.preview.mode");
		generate = Outside.service(this,"gus06.file.perform.generate.jpg.preview");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		File file = (File) o[1];
		String[] row = (String[]) o[2];
		
		String md5 = row[4];
		
		boolean ok = checkMode.f(new Object[]{engine,md5});
		if(!ok) return false;
		
		File previewFile = (File) ((R) engine).r("previewFile:"+md5);
		generate(file,previewFile);
		return true;
	}
	
	
	
	private void generate(File file, File previewFile)
	{
		try{generate.p(new File[]{file,previewFile});}
		catch(Exception e)
		{Outside.err(this,"generate(File,File)",e);}
	}
}