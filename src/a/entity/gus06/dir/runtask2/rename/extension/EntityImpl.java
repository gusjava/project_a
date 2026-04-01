package a.entity.gus06.dir.runtask2.rename.extension;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150619";}
	
	public static final String KEY_RENAME_TARGET = "rename.target";
	public static final String KEY_RENAME_EXTENSION = "rename.extension";


	private Service listing;
	private Service renameFile;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		renameFile = Outside.service(this,"gus06.file.perform.rename.changeext");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		File dir = (File) o[1];
		Object progress = o[2];
		Set interrupt = (Set) o[3];
		
		String target = get(map,KEY_RENAME_TARGET);
		String extension = get(map,KEY_RENAME_EXTENSION);
		
		String[] targets = target!=null?target.split(";"):null;
		
		List list = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+list.size());
		
		for(int i=0;i<list.size();i++)
		{
			File f = (File) list.get(i);
			if(isTarget(f,targets))
			renameFile.p(new Object[]{f,extension});
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	
	
	private boolean isTarget(File f, String[] nn)
	{
		if(nn==null) return true;
		String name = f.getName();
		for(String n:nn) if(name.endsWith("."+n)) return true;
		return false;
	}
}
