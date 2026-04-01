package a.entity.gus06.dir.runtask2.report.filesdico1.r.previousdata;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170110";}


	private Service readDico;
	private Service buildDate;
	
	public EntityImpl() throws Exception
	{
		readDico = Outside.service(this,"gus06.file.read.string.dico.list.autodetect");
		buildDate = Outside.service(this,"gus06.file.lastmodified.timestamp.s");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String p = (String) o[1];
		
		if(p==null) return new HashMap();
		
		File f = new File(p);
		if(!f.isFile()) return new HashMap();
		
		List list =  (List) readDico.t(f);
		if(list==null || list.isEmpty()) return new HashMap();
		
		String c = dir.getAbsolutePath().substring(0,1);
		Map map = new HashMap();
		
		for(int i=0;i<list.size();i++)
		{
			String[] row = (String[]) list.get(i);
			String[] infos = buildInfos(row,c);
			if(infos!=null) map.put(infos[0],infos[1]);
		}
		return map;
	}
	
	
	
	private String[] buildInfos(String[] row, String c) throws Exception
	{
		if(row.length!=5) throw new Exception("Wrong row data number: "+row.length);
		
		String md5 = row[0];
		String date = row[1];
		String size = row[2];
		String location = row[3];
		String name = row[4];
		
		String path = c+location.substring(1)+File.separator+name;
		File file = new File(path);
		
		if(!file.isFile()) return null;
		
		String size1 = ""+file.length();
		if(!size.equals(size1)) return null;
		
		String date1 = (String) buildDate.t(file);
		if(!date.equals(date1)) return null;
		
		String line = md5+"\t"+date+"\t"+size+"\t"+location+"\t"+name;
		
		return new String[]{path,line};
	}
}
