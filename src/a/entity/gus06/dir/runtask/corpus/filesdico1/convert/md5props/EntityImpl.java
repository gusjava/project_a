package a.entity.gus06.dir.runtask.corpus.filesdico1.convert.md5props;

import a.framework.*;
import java.io.*;
import java.util.*;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170615";}
	
	private Charset charset = Charset.forName("UTF-8");
	

	private Service listing;
	private Service seqValue;
	private Service readProp;
	private Service writeProp;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0l");
		seqValue = Outside.service(this,"gus06.map.string.seqvalue.complete.sorted");
		readProp = Outside.service(this,"gus.x.file.prop.read");
		writeProp = Outside.service(this,"gus06.file.write.properties");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File dir1 = new File(dir.getAbsolutePath()+"_md5prop");
		dir1.mkdirs();
		
		
		List list = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+list.size());
		
		for(Object f:list)
		{
			handleListing(dir1,(File) f);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) return;
		}
	}
	
	
	
	
	
	private void handleListing(File dir1, File f)
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
				
				
				File propFile = new File(dir1,md5+".properties");
				Properties prop = buildProp(propFile);
				
				prop.put("md5",md5);
				prop.put("size",size);
				
				seqValue.p(new Object[]{prop,"names",name});
				int count_n = prop.getProperty("names").split(";").length;
				prop.put("count_n",""+count_n);
				
				seqValue.p(new Object[]{prop,"locations",location});
				int count_l = prop.getProperty("locations").split(";").length;
				prop.put("count_l",""+count_l);
				
				
				writeProp.p(new Object[]{propFile,prop});
			}
			fis.close();
		}
		catch(Exception e)
		{Outside.err(this,"handleListing(Map,File)",e);}
	}
	
	
	
	private Properties buildProp(File f) throws Exception
	{
		if(!f.exists()) return new Properties();
		return (Properties) readProp.t(f);
	}
}
