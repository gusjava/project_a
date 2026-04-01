package a.entity.gus06.appli.gusdbmanager.data.holder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, F, G {


	public String creationDate() {return "20150613";}

	public static final String STORENAME = "data.prop_crypt";
	
	
	private Service fileLoader;
	private Service fileCopier;
	
	private Map data;
	private File storeDir;
	private File dataFile;
	

	public EntityImpl() throws Exception
	{
		fileLoader = Outside.service(this,"gus06.crypto.pbe.propfile.loader");
		fileCopier = Outside.service(this,"gus06.file.op.copy");
		
		storeDir = (File) Outside.resource(this,"defaultdir");
		dataFile = new File(storeDir,STORENAME);
		
		data = new HashMap();
		initData();
	}



	public boolean f(Object obj) throws Exception
	{
		File file = (File) obj;

		if(!file.exists() || file.length()==0) return false;
		boolean loaded = loadFile(file);
		if(!loaded) return false;

		fileCopier.p(new File[]{file,dataFile});
		dataLoaded();
		return true;
	}



	public Object g() throws Exception
	{return data;}

	
	
	
	private void initData()
	{
		try
		{
			if(!dataFile.exists() || dataFile.length()==0) return;
			boolean loaded = loadFile(dataFile);
			if(loaded) dataLoaded();
		}
		catch(Exception e)
		{Outside.err(this,"initData()",e);}
	}
	
	
	
	
	private boolean loadFile(File file) throws Exception
	{
		return fileLoader.f(new Object[]{file,data,"fukagyaku"}); // EN MODE DEV !!!!
		//return fileLoader.f(new Object[]{file,data});
	}
	
	
	private void dataLoaded()
	{send(this,"dataLoaded()");}
}
