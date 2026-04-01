package a.entity.gus06.sys.appconfig1.init;

import a.framework.*;
import java.io.PrintStream;
import java.io.File;

public class EntityImpl implements Entity, E, R, V {

	public String creationDate() {return "20250725";}

	private Service startup;
	private Service moveInside;
	private Service cacheManager;
	
	private File rootDir;
	private File startupDir;
	private File fileInitRef;
	private File scriptRefsDir;
	private File file1;
	private File scriptsDir;
	private File scriptsRootDir;
		
	public EntityImpl() throws Exception
	{
		startup = Outside.service(this,"gus06.appli.gusexplorer.scripts.startup.manager");
		moveInside = Outside.service(this,"gus06.dirfile.op.movetodir.replace");
		cacheManager = Outside.service(this,"gus06.sys.cache1");
		
		rootDir = (File) Outside.resource(this,"rootdir");
		startupDir = (File) startup.r("dir");
		fileInitRef = new File(startupDir, "init_ref.gus");
		scriptRefsDir = new File(rootDir, "scripts_ref");
		file1 = new File(scriptRefsDir, "1.gus");
		scriptsDir = new File(rootDir, "SCRIPTS");
		scriptsRootDir = new File(scriptsDir, "root");
	}
	
	
	public void e() throws Exception
	{
		//initialisation de init_ref.gus
		if(!fileInitRef.exists()) writeFileInitRef(fileInitRef);
		
		// initialisation de script_ref 
		if(!scriptRefsDir.exists()) scriptRefsDir.mkdirs();
		
		// initialisation de 1.gus
		if(!file1.exists())
		{
			writeFile1(file1);
			cacheManager.v("1",file1);
		}
		
		// initialisation de SCRIPTS
		if(!scriptsDir.exists()) scriptsDir.mkdirs();
		
		// initialisation de SCRIPTS (root)
		if(!scriptsRootDir.exists()) scriptsRootDir.mkdirs();
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("rootDir")) return rootDir;
		if(key.equals("startupDir")) return startupDir;
		if(key.equals("fileInitRef")) return fileInitRef;
		if(key.equals("scriptRefsDir")) return scriptRefsDir;
		if(key.equals("file1")) return file1;
		if(key.equals("scriptsDir")) return scriptsDir;
		if(key.equals("scriptsRootDir")) return scriptsRootDir;
		
		if(key.startsWith(":")) return dirForScript(key.substring(1));
		
		if(key.equals("keys")) return new String[]{
			"rootDir",
			"startupDir",
			"fileInitRef",
			"scriptRefsDir",
			"file1",
			"scriptsDir",
			"scriptsRootDir"
		};
		throw new Exception("Unknown key: "+key);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(obj instanceof File)
		{
			File file = (File) obj;
			File scriptDir = dirForScript(key);
			moveInside.p(new File[]{file, scriptDir});
			return;
		}
		if(obj instanceof String)
		{
			String src = (String) obj;
			File scriptFile = fileForScript(key);
			PrintStream p = new PrintStream(scriptFile);
			p.println(src);
			p.close();
			return;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private File dirForScript(String name)
	{
		File dir = new File(scriptsRootDir, name.replace(".",File.separator));
		dir.mkdirs();
		return dir;
	}
	
	private File fileForScript(String name)
	{
		return new File(dirForScript(name), "script.gus");
	}
	
	
	private void writeFileInitRef(File f) throws Exception
	{
		PrintStream p = new PrintStream(f);
		p.println("@code");
		p.println("$root=1._app_rootdir");
		p.println("$refDir=root._dchild1.scripts_ref");
		p.println();
		p.println("each refDir._fchildren");
		p.println(" *v._ref_init#v._name0");
		p.close();
	}
	
	private void writeFile1(File f) throws Exception
	{
		PrintStream p = new PrintStream(f);
		p.println("@code");
		p.println("$root=1._app_rootdir");
		p.println("$dir=root._dchild1.SCRIPTS");
		p.println();
		p.println("&dir_script=dir._dchild1.root");
		p.println("&mapping=dir._fchild1.'mapping.properties'");
		p.println();
		p.println("$m.root=dir_script");
		p.println("$m.map=mapping._read_prop");
		p.println("$m.main='script.gus'");
		p.println("*context.file_mapper._v_map#m");
		p.close();
	}
}