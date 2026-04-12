package a.entity.gus06.sys.filetool.ext.entityeditor1.holder;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250630";}
	
	public static final String KEY_DATADIR = "path.datadir";
	public static final String KEY_SRCDIR = "path.srcdir";
	public static final String KEY_BINDIR = "path.bindir";
	public static final String KEY_LIBDIR = "path.libdir";
	public static final String KEY_JDKDIR = "path.jdkdir";
	public static final String KEY_DEV = "dev";
	public static final String KEY_IGNORE1 = "ignore1";


	private Service gui;	
	private Service engineBuilder;
	
	private Map map;
	
	public EntityImpl() throws Exception
	{
		gui = Outside.service(this,"*gus06.sys.entityeditor1.gui.main");
		engineBuilder = Outside.service(this,"gus06.y.entitysys1.engine");
	}
	
	
	public Object i() throws Exception
	{return gui.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		
		String dataPath = get0(KEY_DATADIR);
		String srcPath = get0(KEY_SRCDIR);
		String binPath = get0(KEY_BINDIR);
		String libPath = get0(KEY_LIBDIR);
		String jdkPath = get0(KEY_JDKDIR);
		String dev = get0(KEY_DEV);
		Set ignore1 = toSet(get0(KEY_IGNORE1));
		
		if(dataPath==null) throw new Exception("Data path not initialized");
		if(srcPath==null) throw new Exception("Src path not initialized");
		if(binPath==null) throw new Exception("Bin path not initialized");
		if(libPath==null) throw new Exception("Lib path not initialized");
		if(jdkPath==null) throw new Exception("Jdk path not initialized");
		if(dev==null) throw new Exception("Dev not initialized");
		
		File dataDir = new File(dataPath);
		File srcDir = new File(srcPath);
		File binDir = new File(binPath);
		File libDir = new File(libPath);
		File jdkDir = new File(jdkPath);
		
		Object engine = engineBuilder.t(new Object[]{dataDir, srcDir, binDir, libDir, jdkDir,dev,ignore1});
		
		gui.p(engine);
	}
	
	private String get0(String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	private Set toSet(String seq) throws Exception
	{
		Set set = new HashSet();
		if(seq==null) return set;
		String[] nn = seq.split(",");
		for(String n : nn) set.add(n);
		return set;
	}
}
