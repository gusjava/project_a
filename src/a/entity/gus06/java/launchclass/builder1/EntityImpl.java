package a.entity.gus06.java.launchclass.builder1;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.StringJoiner;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250703";}
	
	public static final String KEY_EXE = "exe";
	public static final String KEY_ROOT = "root";
	public static final String KEY_BIN = "bin";
	public static final String KEY_LIB = "lib";


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.exec");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File launchDir = findLaunchDir(obj);
		String exeCall = formatExeCall(obj);
		
		Set cpSet = buildClasspath(obj);
		if(cpSet.isEmpty()) throw new Exception("Classpath not found");
		String cp = formatClasspath(cpSet);
		
		return new Launcher(launchDir, exeCall, cp);
	}
	
	
	private class Launcher implements P, R
	{
		private File launchDir;
		private String exeCall;
		private String cp;
		
		public Launcher(File launchDir, String exeCall, String cp)
		{
			this.launchDir = launchDir;
			this.exeCall = exeCall;
			this.cp = cp;
		}
		
		public void p(Object obj) throws Exception
		{
			String main = (String) obj;
			String cmd = exeCall+" -cp "+cp+" "+main;
			
			if(launchDir!=null)
			{
				Map m = new HashMap();
				m.put("dir",launchDir);
				m.put("cmd",cmd);
				perform.p(m);
			}
			else perform.p(cmd);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("cp")) return cp;
			if(key.equals("launchDir")) return launchDir;
			if(key.equals("exeCall")) return exeCall;
			if(key.equals("cmd")) return exeCall+" -cp "+cp+" <main>";
			if(key.equals("keys")) return new String[]{"cp","launchDir","exeCall","cmd"};
			
			throw new Exception("Unknown key: "+key);
		}
	}
	
	// EXE
	
	private String formatExeCall(Object obj) throws Exception
	{
		if(!(obj instanceof Map)) return "java";
		Map m = (Map) obj;
		if(!m.containsKey(KEY_EXE)) return "java";
		File exeFile = new File((String) m.get(KEY_EXE));
		if(!exeFile.isFile()) throw new Exception("Exe file not found: "+exeFile);
		return "\""+path(exeFile)+"\"";
	}
	
	// LAUNCH DIR
	
	private File findLaunchDir(Object obj) throws Exception
	{
		if(obj instanceof Map) return findLaunchDirFromMap((Map) obj);
		if(obj instanceof File) return (File) obj;
		throw new Exception("Unsupported data type: "+obj.getClass().getName());
	}
	
	private File findLaunchDirFromMap(Map map) throws Exception
	{
		if(map.containsKey(KEY_ROOT))
			return new File((String) map.get(KEY_ROOT));
		if(map.containsKey(KEY_BIN))
			return new File((String) map.get(KEY_BIN));
		return null;
	}
	
	// CLASS PATH
	
	private Set buildClasspath(Object obj) throws Exception
	{
		if(obj instanceof Map) return buildClasspathFromMap((Map) obj);
		if(obj instanceof File) return buildClasspathFromRoot((File) obj);
		throw new Exception("Unsupported data type: "+obj.getClass().getName());
	}
	
	private Set buildClasspathFromMap(Map map) throws Exception
	{
		Set cp = new HashSet();
		if(map.containsKey(KEY_BIN))
		{
			File binDir = new File((String) map.get(KEY_BIN));
			if(!binDir.isDirectory()) throw new Exception("Bin directory not found: "+binDir);
			cp.add(path(binDir));
		}
		if(map.containsKey(KEY_LIB))
		{
			File libDir = new File((String) map.get(KEY_LIB));
			if(!libDir.isDirectory()) throw new Exception("Lib directory not found: "+libDir);
			cp.add(path(libDir)+"/*");
		}
		if(map.containsKey(KEY_ROOT))
		{
			File rootDir = new File((String) map.get(KEY_ROOT));
			cp.addAll(buildClasspathFromRoot(rootDir));
		}
		return cp;
	}
	
	private Set buildClasspathFromRoot(File rootDir) throws Exception
	{
		Set cp = new HashSet();
		if(!rootDir.isDirectory()) throw new Exception("Root directory not found: "+rootDir);
		
		File binDir = new File(rootDir, "bin");
		if(binDir.isDirectory()) cp.add(path(binDir));
		
		File libDir = new File(rootDir, "lib");
		if(libDir.isDirectory()) cp.add(path(libDir)+"/*");
		
		if(cp.isEmpty()) cp.add(path(rootDir));
		return cp;
	}
	
	
	private String formatClasspath(Set cpSet)
	{
		String sep = classpathSeparator();
		List l = new ArrayList(cpSet);
		StringJoiner joiner = new StringJoiner(sep);
		for(int i=0;i<l.size();i++) joiner.add((String) l.get(i));
		return joiner.toString(); 
	}
	
	
	private String classpathSeparator()
	{
		String os = System.getProperty("os.name").toLowerCase();
		return os.contains("win") ? ";" : ":";	
	}
	
	private String path(File dir)
	{
		return dir.getAbsolutePath().replace("\\","/");
	}
}
