package a.entity.gus06.dir.runtask.corpus.properties.fieldstats.analyzer1;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170601";}


	private Service listing;
	private Service readProp;
	private Service statCollector;
	private Service writeProp;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		readProp = Outside.service(this,"gus06.file.read.properties");
		statCollector = Outside.service(this,"*gus06.sys.statistics1.collector2");
		writeProp = Outside.service(this,"gus06.file.write.properties");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File dir1 = new File(dir.getAbsolutePath()+"_stats1");
		dir1.mkdirs();
		
		
		File[] ff = (File[]) listing.t(dir);
		
		int size = ff.length;
		if(progress!=null) ((V)progress).v("size",""+size);
		
		statCollector.e();
		
		for(File f:ff)
		{
			Map prop = (Map) readProp.t(f);
			statCollector.p(prop);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		Map result = (Map) statCollector.g();
		
		Iterator it = result.keySet().iterator();
		while(it.hasNext())
		{
			String field = (String) it.next();
			R holder = (R) result.get(field);
			Map m = holderToMap(holder);
			
			File file = new File(dir1,field+".properties");
			writeProp.p(new Object[]{file,m});
		}
	}
	
	
	private Map holderToMap(R r) throws Exception
	{
		Map m = new HashMap();
		
		ttt(m,r,"nb_null");
		ttt(m,r,"nb_empty");
		ttt(m,r,"nb_number");
		ttt(m,r,"nb_true");
		ttt(m,r,"nb_false");
		
		ttt(m,r,"data_sum");
		ttt(m,r,"data_min");
		ttt(m,r,"data_max");
		
		ttt(m,r,"length_sum");
		ttt(m,r,"length_min");
		ttt(m,r,"length_max");
		
//		if(key.equals("list_data")) return list_data;
//		if(key.equals("list_length")) return list_length;
//		if(key.equals("list_number")) return list_number;
//		if(key.equals("set_symbols")) return set_symbols;
//		if(key.equals("freq")) return freq;

		return m;
	}
	
	private void ttt(Map m, R r, String key) throws Exception
	{m.put(key,""+r.r(key));}
}
