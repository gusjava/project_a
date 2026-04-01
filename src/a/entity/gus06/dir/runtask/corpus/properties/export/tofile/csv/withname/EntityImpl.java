package a.entity.gus06.dir.runtask.corpus.properties.export.tofile.csv.withname;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.io.PrintStream;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashSet;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180529";}


	private Service listing;
	private Service formatter;
	private Service readFile;
	private Service readKeySet;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		formatter = Outside.service(this,"gus06.io.printstream.formatter.csv1");
		readFile = Outside.service(this,"gus06.file.read.properties");
		readKeySet = Outside.service(this,"gus06.file.read.properties.keyset");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File csvFile = new File(dir.getAbsolutePath()+".csv");
		PrintStream p = (PrintStream) formatter.t(csvFile);
		
		File[] ff = (File[]) listing.t(dir);
		Set fields = new HashSet();
		
		int size = ff.length*2;
		if(progress!=null) ((V)progress).v("size",""+size);
		
		for(File f:ff)
		{
			if(interrupt!=null && !interrupt.isEmpty())
			{p.close();return;}
			
			fields.addAll((Set) readKeySet.t(f));
			if(progress!=null) ((E)progress).e();
		}
		
		String[] header = buildHeader(fields);
		p.println(header);
		
		for(File f:ff)
		{
			if(interrupt!=null && !interrupt.isEmpty())
			{p.close();return;}
			
			Map m = (Map) readFile.t(f);
			String name = f.getName();
			
			String[] row = buildRow(m,name,header);
			p.println(row);
			
			if(progress!=null) ((E)progress).e();
		}
		
		p.close();
	}
	
	
	private String[] buildHeader(Set fields)
	{
		List list = new ArrayList(fields);
		Collections.sort(list);
		
		String[] array = new String[list.size()+1];
		
		array[0] = "name";
		for(int i=0;i<list.size();i++)
		array[i+1] = (String) list.get(i);
		
		return array;
	}
	
	private String[] buildRow(Map map, String name, String[] header)
	{
		String[] row = new String[header.length];
		row[0] = name;
		for(int i=1;i<header.length;i++)
		row[i] = get(map,header[i]);
		return row;
	}
	
	private String get(Map map, String key)
	{return map.containsKey(key) ? (String) map.get(key) : "";}
}
