package a.entity.gus06.runtime.exec.map;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180220";}
	
	public static final String KEY_DIR = "dir";
	public static final String KEY_CMD = "cmd";
	public static final String KEY_ARGS = "args";
	public static final String KEY_RESOLVE_EXE = "resolve_exe";
	
	private Service prepare; 
	
	public EntityImpl() throws Exception
	{
		prepare = Outside.service(this,"gus06.runtime.exec.resolveexe");
	}
	
	public Object t(Object obj) throws Exception
	{return process((Map) obj);}
	
	private Process process(Map m) throws Exception
	{
		Object dirObj = get(m,KEY_DIR);
		Object cmdObj = get(m,KEY_CMD);
		Object argsObj = get(m,KEY_ARGS);
		Object resolveExeObj = get(m,KEY_RESOLVE_EXE);
		
		if(cmdObj==null) throw new Exception("Command not found inside map");
		
		boolean resolveExe = isTrue(resolveExeObj);
		String cmd = buildCmd(cmdObj,argsObj, resolveExe);
		File dir = buildDir(dirObj);
		
		if(dir!=null) return Runtime.getRuntime().exec(cmd,null,dir);
		return Runtime.getRuntime().exec(cmd);
	}
	
	private String buildCmd(Object cmdObj, Object argsObj, boolean resolveExe) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		
		if(cmdObj instanceof File)
			sb.append(p((File) cmdObj));
		else if(cmdObj instanceof String)
		{
			if(resolveExe) sb.append((String) prepare.t(cmdObj));
			else sb.append((String) cmdObj);
		}
		else throw new Exception("Invalid cmd type: "+cmdObj.getClass().getName());
		
		if(argsObj==null) return sb.toString();
		
		if(argsObj instanceof String)
		{
			sb.append(" "+p((String) argsObj));
			return sb.toString();
		}
		if(argsObj instanceof String[])
		{
			String[] args = (String[]) argsObj;
			for(String arg : args) sb.append(" "+p(arg));
			return sb.toString();
		}
		throw new Exception("Invalid args type: "+argsObj.getClass().getName());
	}
	
	private File buildDir(Object dirObj) throws Exception
	{
		if(dirObj==null) return null;
		if(dirObj instanceof File) return (File) dirObj;
		throw new Exception("Invalid dir type: "+dirObj.getClass().getName());
	}
	
	private String p(File f)
	{return "\""+f.getAbsolutePath()+"\"";}
	
	private String p(String s)
	{
		if(!s.contains(" ")) return s;
		return "\""+s+"\"";
	}
	
	private boolean isTrue(Object obj) throws Exception
	{
		if(obj==null) return false;
		if(obj instanceof Boolean) return (Boolean) obj;
		if(obj instanceof String) return ((String) obj).toLowerCase().equals("true");
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object get(Map map, String key) 
	{return map.containsKey(key) ? map.get(key) : null;}
}
