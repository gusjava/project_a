package a.entity.gus06.appli.gusexplorer.labelcust.manager;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class EntityImpl extends S1 implements Entity, R, P, V {

	public String creationDate() {return "20230118";}

	private Service persister;
	private Service buildDisplay;
	private Service popupDisplay;
	
	private Map map;

	public EntityImpl() throws Exception
	{
		persister = Outside.service(this,"gus06.app.persister1");
		buildDisplay = Outside.service(this,"gus06.file.getdisplay");
		popupDisplay = Outside.service(this,"gus06.data.editor.string.display.dialog");
		
		map = (Map) persister.r(getClass().getName()+"_map");
		if(map==null) map = new HashMap();
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof String) askDisplayForPath((String) obj);
		else if(obj instanceof File) askDisplayForFile((File) obj);
		else if(obj instanceof Object[]) setDisplay((Object[]) obj);
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private void askDisplayForFile(File file) throws Exception
	{askDisplayFor(file, file.getAbsolutePath());}
	
	
	private void askDisplayForPath(String path) throws Exception
	{askDisplayFor(new File(path), path);}
	
	
	private void askDisplayFor(File file, String path) throws Exception
	{
		String display = get(path);
		String fileDisplay = fileDisplay(file);
		
		String newDisplay = (String) popupDisplay.t(display!=null ? display : fileDisplay);
		if(Objects.equals(newDisplay, fileDisplay) || Objects.equals(newDisplay, ""))
		{
			if(display!=null) 
			{
				map.remove(path);
				persist();
			}
		}
		else if(!Objects.equals(newDisplay, display))
		{
			map.put(path, newDisplay);
			persist();
		}
	}
	
	
	
	private void setDisplay(Object[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String path = toPath(o[0]);
		String newDisplay = (String) o[1];
		
		if(newDisplay==null)
		{
			if(map.containsKey(path))
			{
				map.remove(path);
				persist();
			}
		}
		else
		{
			String display = get(path);
			if(!Objects.equals(newDisplay, display))
			{
				map.put(path, newDisplay);
				persist();
			}
		}
	}
	
	
	
	public Object r(String key) throws Exception
	{return get(key);}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("refactorPath")) {refactorPath((File[]) obj);return;}
		if(key.equals("removePath")) {removePath((File) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private void refactorPath(File[] f) throws Exception
	{
		if(f.length!=2) throw new Exception("Wrong data number: "+f.length);
		String path1 = f[0].getAbsolutePath();
		String path2 = f[1].getAbsolutePath();
		
		if(path1.equals(path2)) return;
		
		String display = get(path1);
		if(display==null) return;
		
		map.remove(path1);
		map.put(path2, display);
		persist();
	}
	
	private void removePath(File path) throws Exception
	{
		map.remove(path);
		persist();
	}
	
	
	String toPath(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return ((File) obj).getAbsolutePath();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String get(String path)
	{return map.containsKey(path) ? (String) map.get(path) : null;}
	
	
	private String fileDisplay(File file) throws Exception
	{return (String) buildDisplay.t(file);}
	
	
	private void persist() throws Exception
	{
		persister.v(getClass().getName()+"_map", map);
		modified();
	}
	
	
	private void modified()
	{send(this,"modified()");}
}