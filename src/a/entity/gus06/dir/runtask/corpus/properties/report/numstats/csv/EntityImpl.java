package a.entity.gus06.dir.runtask.corpus.properties.report.numstats.csv;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.io.PrintStream;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180418";}
	
	public static final String[] HEADER = new String[]{"Field","Min","Max","Count","Total","Avg","ET"};

	//0: min
	//1: max
	//2: count
	//3: total
	//4: avg
	//5: et
	

	private Service listing;
	private Service formatter;
	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		formatter = Outside.service(this,"gus06.io.printstream.formatter.csv1");
		readFile = Outside.service(this,"gus.x.file.prop.read");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File csvFile = new File(dir.getAbsolutePath()+"_stats.csv");
		
		File[] ff = (File[]) listing.t(dir);
		Map map = new HashMap();
		
		int size = ff.length*2;
		if(progress!=null) ((V)progress).v("size",""+size);
		
		for(File f:ff)
		{
			if(interrupt!=null && !interrupt.isEmpty())
			{return;}
			
			Map m = (Map) readFile.t(f);
			Iterator it = m.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = (String) m.get(key);
				
				try
				{
					double v = Double.parseDouble(value);
					handle1(map,key,v);
				}
				catch(NumberFormatException e) {}
			}
			if(progress!=null) ((E)progress).e();
		}
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			double[] arr = (double[]) map.get(key);
			arr[4] = arr[3]/arr[2];
		}
		
		for(File f:ff)
		{
			if(interrupt!=null && !interrupt.isEmpty())
			{return;}
			
			Map m = (Map) readFile.t(f);
			it = m.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = (String) m.get(key);
				
				try
				{
					double v = Double.parseDouble(value);
					handle2(map,key,v);
				}
				catch(NumberFormatException e) {}
			}
			if(progress!=null) ((E)progress).e();
		}
		
		PrintStream p = (PrintStream) formatter.t(csvFile);
		p.println(HEADER);
		
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		
		for(Object key : keys)
		{
			double[] arr = (double[]) map.get(key);
			arr[5] = Math.sqrt(arr[5]/arr[2]);
			
			String[] row = new String[]{(String) key,""+arr[0],""+arr[1],""+arr[2],""+arr[3],""+arr[4],""+arr[5]};
			p.println(row);
		}
		p.close();
	}
	
	
	
	private void handle1(Map map, String key, double v)
	{
		if(!map.containsKey(key))
		map.put(key,new double[]{Double.MAX_VALUE,Double.MIN_VALUE,0,0,0,0});
		
		double[] arr = (double[]) map.get(key);
		
		if(v<arr[0]) arr[0] = v;
		if(v>arr[1]) arr[1] = v;
		arr[2]++;
		arr[3]+=v;
	}
	
	
	private void handle2(Map map, String key, double v)
	{
		double[] arr = (double[]) map.get(key);
		
		double dv = v-arr[4];
		arr[5]+= dv*dv;
	}
}
