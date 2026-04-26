package a.entity.gus06.dir.runtask.corpus.properties.repartition.analyzer2;

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

	public String creationDate() {return "20151020";}


	private Service listing;
	private Service readKeySet;
	private Service readProp;
	private Service categorizer;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		readKeySet = Outside.service(this,"gus06.file.read.properties.keyset");
		readProp = Outside.service(this,"gus.x.file.prop.read");
		categorizer = Outside.service(this,"gus06.string.transform.categorize.code2");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File dir1 = new File(dir.getAbsolutePath()+"_rep2");
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
		
		String[] keys = buildKeys(fields);
		Map pp = buildPrintStreams(fields,dir1);
		
		for(File f:ff)
		{
			handleFile(f,pp);
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		Iterator it = pp.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
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
	
	private void handleFile(File file, Map pp) throws Exception
	{
		Map prop = (Map) readProp.t(file);
		
		Iterator it = pp.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			PrintStream p = (PrintStream) pp.get(key);
			String value = get(prop,key);
			p.print(getCode(value));
		}
	}
	
	
	private String get(Map prop, String key)
	{
		if(!prop.containsKey(key)) return null;
		return (String) prop.get(key);
	}
	
	
	private String getCode(String value) throws Exception
	{return (String) categorizer.t(value);}
}