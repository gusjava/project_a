package a.entity.gus06.dir.runtask.corpus.image.report.histogram10.hue.r;

import a.framework.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180505";}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	
	private String now() throws Exception
	{return sdf.format(new Date());}
	



	private Service readImage;
	private Service buildInfos;
	private Service buildListing;
	private Service findLatest;
	private Service readFileAsArray;
	
	public EntityImpl() throws Exception
	{
		readImage = Outside.service(this,"gus06.file.read.image.generic");
		buildInfos = Outside.service(this,"gus06.awt.bufferedimage.color.histogram10.hue");
		buildListing = Outside.service(this,"gus06.dir.listing0.ext.image");
		findLatest = Outside.service(this,"gus06.dir.timestampedname.latest");
		readFileAsArray = Outside.service(this,"gus06.file.read.string.array");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String part1 = dir.getName();
		String part2 = "histogram10_hue.txt";
		
		File f0 = (File) findLatest.t(new Object[]{dir.getParentFile(),part1,part2});
		File f1 = new File(dir.getAbsolutePath()+"_"+now()+"_"+part2);
		
		Map previousData = buildPreviousData(f0);
		PrintStream p = new PrintStream(f1);
		
		
		File[] listing = (File[]) buildListing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+listing.length);
		
		for(File file:listing)
		{
			printInfos(p,file,previousData);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) return;
		}
		p.close();
	}
	
	
	
	private void printInfos(PrintStream p, File file, Map previousData)
	{
		try
		{
			String line = buildLine(file,previousData);
			p.println(line);
		}
		catch(Exception e)
		{Outside.err(this,"printInfos(PrintStream,File,Map)",e);}
	}
	
	
	private String buildLine(File file, Map previousData) throws Exception
	{
		String key = file.getName();
		if(previousData.containsKey(key))
		return key+"\t"+previousData.get(key);
		
		BufferedImage image = (BufferedImage) readImage.t(file);
		double[] infos = (double[]) buildInfos.t(image);
		
		StringBuffer b = new StringBuffer();
		b.append(key);
		
		for(double info : infos)
		b.append("\t"+info);
		
		return b.toString();
	}
	
	
	
	private Map buildPreviousData(File f) throws Exception
	{
		Map map = new HashMap();
		if(f==null || !f.exists()) return map;
		
		String[] nn = (String[]) readFileAsArray.t(f);
		for(String n : nn)
		{
			String[] k = n.split("\t",2);
			map.put(k[0],k[1]);
		}
		return map;
	}
}
