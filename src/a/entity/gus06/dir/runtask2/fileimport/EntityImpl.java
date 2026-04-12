package a.entity.gus06.dir.runtask2.fileimport;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150619";}
	
	public static final String KEY_FILEIMPORT_TARGET = "fileimport.target";
	public static final String KEY_FILEIMPORT_ARCHIVE = "fileimport.archive";
	public static final String KEY_FILEIMPORT_ROOTPATH = "fileimport.rootpath";

	private Service listing;
	private Service importToDir;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		importToDir = Outside.service(this,"gus06.file.perform.importtodir");
	}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		File dir = (File) o[1];
		Object progress = o[2];
		Set interrupt = (Set) o[3];
		
		String target = get(map,KEY_FILEIMPORT_TARGET);
		String archive = get(map,KEY_FILEIMPORT_ARCHIVE);
		String root = get(map,KEY_FILEIMPORT_ROOTPATH);
		
		String[] targets = target!=null?target.split(";"):null;
		String[] archives = archive!=null?archive.split(";"):null;
		File rootDir = new File(root);
		
		List list = (List) listing.t(rootDir);
		if(progress!=null) ((V)progress).v("size",""+list.size());
		
		for(int i=0;i<list.size();i++)
		{
			File f = (File) list.get(i);
			if(isType(f,targets))
				importToDir.p(new File[]{f,dir});
			if(isType(f,archives))
				importToDir.p(new File[]{f,dir});
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	private boolean isType(File f, String[] nn)
	{
		if(nn==null) return true;
		String name = f.getName();
		for(String n:nn) if(name.endsWith("."+n)) return true;
		return false;
	}
}
