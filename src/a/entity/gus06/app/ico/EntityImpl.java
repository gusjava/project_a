package a.entity.gus06.app.ico;

import a.framework.*;
import java.util.Map;
import javax.swing.Icon;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150608";}

	public static final String APPICO = "app.ico";
	
	
	private Service writeIco;
	private Service extract;
	private Service appIcon;
	
	private Map prop;
	private File storeDir;
	private File icoFile;
	
	
	
	
	public EntityImpl() throws Exception
	{
		writeIco = Outside.service(this,"gus06.file.write.ico");
		extract = Outside.service(this,"gus06.app.resource.extracttofile");
		appIcon = Outside.service(this,"gus06.app.icon");
		
		prop = (Map) Outside.resource(this,"props");
		storeDir = (File) Outside.resource(this,"defaultdir");
		icoFile = new File(storeDir,"app.ico");
	}
	
	
	public Object g() throws Exception
	{
		if(!icoFile.exists()) init();
		return icoFile.exists()?icoFile:null;
	}
	
	
	
	
	private void init() throws Exception
	{
		if(has(APPICO))
		{
			String icoPath = prop(APPICO);
			extract.v(icoPath,icoFile);
			return;
		}
		
		Icon icon = (Icon) appIcon.g();
		if(icon!=null)
		{
			writeIco.p(new Object[]{icoFile,icon});
			return;
		}
	}
	
	
	
	private String prop(String key)
	{return (String) prop.get(key);}
	
	private boolean has(String key)
	{return prop.containsKey(key);}
}
