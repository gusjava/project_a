package a.entity.gus06.dir.runtask.corpus.properties.display.charreport.tnr;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151026";}
	
	
	public static final String TITLE = "Characters: \\t \\n \\r";


	private Service read;
	private Service listing;
	private Service showMessage;
	
	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus06.file.read.properties");
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		showMessage = Outside.service(this,"gus06.swing.optionpane.showmessage.info");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		Map map = new HashMap();
		
		File[] ff = (File[]) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		for(File f:ff)
		{
			Map prop = (Map) read.t(f);
			Iterator it = prop.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = (String) prop.get(key);
				
				boolean has_t = hasChar(key,'\t') || hasChar(value,'\t');
				boolean has_n = hasChar(key,'\n') || hasChar(value,'\n');
				boolean has_r = hasChar(key,'\r') || hasChar(value,'\r');
				
				append(map,key,has_t,has_n,has_r);
			}
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		String message = buildMessage(map);
		showMessage.p(new String[]{message,TITLE});
	}
	
	
	private boolean hasChar(String key, char c)
	{return key.indexOf(c)>=0;} 
	
	
	private void append(Map map, String key, boolean has_t, boolean has_n, boolean has_r)
	{
		if(!has_t && !has_n && !has_r) return;
		
		if(!map.containsKey(key))
			map.put(key,new int[]{0,0,0});
		int[] arr = (int[]) map.get(key);
		
		if(has_t) arr[0]++;
		if(has_n) arr[1]++;
		if(has_r) arr[2]++;
	}
	
	
	
	private String buildMessage(Map map)
	{
		if(map.isEmpty()) return "no character found";
		
		StringBuffer b = new StringBuffer();
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String field = (String) it.next();
			int[] arr = (int[]) map.get(field);
			
			b.append(field+":");
			if(arr[0]>0) b.append(" \\t="+arr[0]);
			if(arr[1]>0) b.append(" \\n="+arr[1]);
			if(arr[2]>0) b.append(" \\r="+arr[2]);
			
			b.append("\n");
		}
		return b.toString();
	}                                       
}
