package a.entity.gus06.dir.runtask.corpus.properties.repartition.analyzer3;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170603";}


	private Service listing;
	private Service readKeySet;
	private Service readProp;
	private Service inferFromField;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		readKeySet = Outside.service(this,"gus06.file.read.properties.keyset");
		readProp = Outside.service(this,"gus06.file.read.properties");
		inferFromField = Outside.service(this,"gus06.filter.string.infer.fromfield");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File dir1 = new File(dir.getAbsolutePath()+"_rep3");
		dir1.mkdirs();
		
		File[] ff = (File[]) listing.t(dir);
		
		int size = ff.length*2;
		if(progress!=null) ((V)progress).v("size",""+size);
		
		Set<String> fields = new HashSet<>();
		for(File f:ff)
		{
			fields.addAll((Set) readKeySet.t(f));
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		Map filters = new HashMap();
		Iterator<String> it = fields.iterator();
		while(it.hasNext())
		{
			String field = it.next();
			F filter = (F) inferFromField.t(field);
			if(filter!=null) filters.put(field,filter);
		}
		
		String[] keys = buildKeys(fields);
		Map pp = buildPrintStreams(fields,dir1);
		
		for(File f:ff)
		{
			handleFile(f,pp,filters);
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		Iterator it2 = pp.keySet().iterator();
		while(it2.hasNext())
		{
			String key = (String) it2.next();
			PrintStream p = (PrintStream) pp.get(key);
			p.close();
		}
	}
	
	
	
	
	
	private String[] buildKeys(Set<String> fields)
	{
		return fields.toArray(new String[fields.size()]);
	}
	
	private Map buildPrintStreams(Set fields, File dir1) throws Exception
	{
		Map pp = new HashMap();
		Iterator it = fields.iterator();
		while(it.hasNext())
		{
			String field = (String) it.next();
			File file = new File(dir1,field+".txt");
			pp.put(field,new PrintStream(file));
		}
		return pp;
	}
	
	private void handleFile(File file, Map pp, Map filters) throws Exception
	{
		Map prop = (Map) readProp.t(file);
		
		Iterator it = pp.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			PrintStream p = (PrintStream) pp.get(key);
			
			String value = (String) get(prop,key);
			F filter = (F) get(filters,key);
			
			p.print(getCode(filter,value));
		}
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	private String getCode(F filter, String value) throws Exception
	{
		if(filter==null) return "0";
		boolean valid = filter.f(value);
		return valid ? "0" : "1";
	}
}
