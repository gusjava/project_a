package a.entity.gus06.dir.runtask.corpus.image.report.histogram10.hue;

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
	
	public EntityImpl() throws Exception
	{
		readImage = Outside.service(this,"gus06.file.read.image.generic");
		buildInfos = Outside.service(this,"gus06.awt.bufferedimage.color.histogram10.hue");
		buildListing = Outside.service(this,"gus06.dir.listing0.ext.image");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		
		File reportFile = new File(dir.getAbsolutePath()+"_"+now()+"_histogram10_hue.txt");
		PrintStream p = new PrintStream(reportFile);
		
		File[] listing = (File[]) buildListing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+listing.length);
		
		for(File file:listing)
		{
			printInfos(p,file);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) return;
		}
		p.close();
	}
	
	
	
	private void printInfos(PrintStream p, File file)
	{
		try
		{
			String line = buildLine(file);
			p.println(line);
		}
		catch(Exception e)
		{Outside.err(this,"printInfos(PrintStream,File)",e);}
	}
	
	
	private String buildLine(File file) throws Exception
	{
		BufferedImage image = (BufferedImage) readImage.t(file);
		double[] infos = (double[]) buildInfos.t(image);
		
		StringBuffer b = new StringBuffer();
		b.append(file.getName());
		
		for(double info : infos)
		b.append("\t"+info);
		
		return b.toString();
	}
}
