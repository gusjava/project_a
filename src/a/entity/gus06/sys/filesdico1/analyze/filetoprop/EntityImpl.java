package a.entity.gus06.sys.filesdico1.analyze.filetoprop;

import a.framework.*;
import java.io.File;
import java.util.Properties;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170125";}

	private Service readDico;
	private Service setToString;
	private Service keepMulti;

	public EntityImpl() throws Exception
	{
		readDico = Outside.service(this,"gus06.file.read.string.dico.list.autodetect");
		setToString = Outside.service(this,"gus06.tostring.set.join.semicolon");
		keepMulti = Outside.service(this,"gus06.map.setmap.keepmulti");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		
		List list =  (List) readDico.t(f);
		if(list==null || list.isEmpty()) return new Properties();
		
		int nb = list.size();
		
		long size_total = 0;
		long size_max = 0;
		long size_min = Long.MAX_VALUE;
		
		long date_max = 0;
		long date_min = Long.MAX_VALUE;
		
		Map map_ext = new HashMap();
		Map map_md5 = new HashMap();
		
		int nb_empty = 0;
		
		
		for(int i=0;i<nb;i++)
		{
			String[] row = (String[]) list.get(i);
			if(row.length!=5) throw new Exception("Wrong row data number: "+row.length);
		
			String md5 = row[0];
			String date = row[1];
			String size = row[2];
			String location = row[3];
			String name = row[4];
			
			String path = location+File.separator+name;
			String ext = getExtension(name);
			long size_ = Long.parseLong(size);
			long date_ = Long.parseLong(date.replace("_",""));
			
			if(size_>0)
			{
				addToMap(map_ext,ext,row);
				addToMap(map_md5,md5,row);
				
				size_total += size_;
				
				if(size_ > size_max) size_max = size_;
				if(size_ < size_min) size_min = size_;
				
				if(date_ > date_max) date_max = date_;
				if(date_ < date_min) date_min = date_;
			}
			else nb_empty++;
		}
		
		Properties p =  new Properties();
		
		p.put("all.nb",""+nb);
		p.put("all.empty.nb",""+nb_empty);
		p.put("all.size.total",""+size_total);
		
		p.put("all.size.min",""+size_min);
		p.put("all.size.max",""+size_max);
		p.put("all.size.avg",""+div(size_total,nb));
		
		p.put("all.date.min",""+date_min);
		p.put("all.date.max",""+date_max);
		
		p.put("all.ext.nb",""+map_ext.size());
		p.put("all.ext.list",""+setToString.t(map_ext.keySet()));
		
		
		Iterator it = map_ext.keySet().iterator();
		while(it.hasNext())
		{
			String ext = (String) it.next();
			Set set = (Set) map_ext.get(ext);
			long[] sizeInfo = (long[]) computeSizeInfo(set);
			int nb1 = set.size();
			
			p.put("ext."+ext+".nb",""+nb1);
			p.put("ext."+ext+".size.min",""+sizeInfo[0]);
			p.put("ext."+ext+".size.max",""+sizeInfo[1]);
			p.put("ext."+ext+".size.total",""+sizeInfo[2]);
			p.put("ext."+ext+".size.avg",""+div(sizeInfo[2],nb1));
		}
		
		keepMulti.p(map_md5);
		
		Map md5_lost = new HashMap();
		long lostTotal = 0;
		int nbFiles1Total = 0;
		
		it = map_md5.keySet().iterator();
		while(it.hasNext())
		{
			String md5 = (String) it.next();
			Set set = (Set) map_md5.get(md5);
			String[] row = (String[]) set.iterator().next();
			long size = Long.parseLong(row[2]);
			int nbFiles1 = set.size()-1;
			
			long lost = size*nbFiles1;
			
			nbFiles1Total += nbFiles1;
			lostTotal += lost;
			
			md5_lost.put(md5,Long.valueOf(lost));
		}
		
		p.put("doubloon.group.nb",""+map_md5.size());
		p.put("doubloon.lostspace",""+lostTotal);
		p.put("doubloon.lostfiles",""+nbFiles1Total);
		
		
		// ajouter la taille perdue
		// le nombre de fichiers en trop
		
		return p;
	}
	
	
	
	private String getExtension(String name)
	{
		if(!name.contains(".")) return "#";
		String[] n = name.split("\\.");
		String ext = n[n.length-1].toLowerCase();
		return ext.length()>4?"#":ext;
	}
	
	private void addToMap(Map map, String key, Object value)
	{
		if(!map.containsKey(key)) map.put(key,new HashSet());
		((Set) map.get(key)).add(value);
	}
	
	private double div(long v, int nb)
	{return (double)v/(double)nb;}
	
	
	private long[] computeSizeInfo(Set set)
	{
		long total = 0;
		long max = 0;
		long min = Long.MAX_VALUE;
		
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String[] row = (String[]) it.next();
			long size = Long.parseLong(row[2]);
			
			total += size;
			if(size > max) max = size;
			if(size < min) min = size;
		}
		
		return new long[]{min,max,total};
	}
}
