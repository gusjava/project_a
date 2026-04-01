package a.entity.gus06.dir.runtask.corpus.filesdico1.report.doubloons;

import a.framework.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150723";}
	
	private Charset charset = Charset.forName("UTF-8");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	
	private String now() throws Exception
	{return sdf.format(new Date());}
	



	private Service mostRecent;
	
	public EntityImpl() throws Exception
	{
		mostRecent = Outside.service(this,"gus06.dir.listing0.timestamped.mostrecent");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		
		List list = (List) mostRecent.t(dir);
		if(progress!=null) ((V)progress).v("size",""+list.size());
			
		Map m_md5 = new HashMap();
		
		for(Object f:list)
		{
			handleListing(m_md5,(File) f);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) return;
		}
		
		List results = new ArrayList();
		
		Iterator it = m_md5.keySet().iterator();
		while(it.hasNext())
		{
			String md5 = (String) it.next();
			Set set = (Set) m_md5.get(md5);
			if(set.size()>1) results.add(set);
		}
		
		Collections.sort(results,new Comparator(){
			public int compare(Object o1, Object o2)
			{
				String[] n1 = (String[]) ((Set)o1).iterator().next();
				String[] n2 = (String[]) ((Set)o2).iterator().next();
				
				Long size1 = Long.valueOf(n1[2]);
				Long size2 = Long.valueOf(n2[2]);
				
				return size2.compareTo(size1);
			}
		});
		
		
		File repFile = new File(dir.getAbsolutePath()+"_"+now()+"_doubloons.txt");
		if(repFile.exists()) repFile.delete();
		
		PrintStream p = new PrintStream(repFile,charset.name());
		
		for(int i=0;i<results.size();i++)
		{
			Set set = (Set) results.get(i);
			printDoubloonSet(p,set);
			p.println();
		}
		
		p.close();
	}
	
	
	
	
	
	private void handleListing(Map m, File f)
	{
		try
		{
			String fileName = f.getName();
			
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(fis,charset);
			BufferedReader br = new BufferedReader(isr);
			
			String line = null;
			while((line = br.readLine())!=null)
			{
				String[] n = line.split("\t");
				if(n.length!=5) throw new Exception("Invalid line: "+line);
				
				String md5 = n[0];
				String date = n[1];
				String size = n[2];
				String location = n[3];
				String name = n[4];
				
				if(size.equals("0")) continue;
				
				String[] data = new String[]{md5,date,size,fileName,location,name};
				addToMap(m,md5,data);
			}
			fis.close();
		}
		catch(Exception e)
		{Outside.err(this,"handleListing(Map,File)",e);}
	}
	
	
	
	private void addToMap(Map map, String key, Object value)
	{
		if(!map.containsKey(key))
			map.put(key,new HashSet());
		((Set) map.get(key)).add(value);
	}
	
	
	
	private void printDoubloonSet(PrintStream p, Set set)
	{
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String[] n = (String[]) it.next();
			int length = n.length;
			for(int i=0;i<length;i++)
			{
				p.print(n[i]);
				if(i<length-1) p.print("\t");
			}
			p.println();
		}
	}
}
