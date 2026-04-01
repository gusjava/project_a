package a.entity.gus06.dir.runtask.corpus.image.report.hsbinfos1;

import a.framework.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.nio.charset.Charset;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180411";}
	
	private Charset charset = Charset.forName("UTF-8");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	
	private String now() throws Exception
	{return sdf.format(new Date());}
	



	private Service readImage;
	private Service buildInfos;
	private Service buildListing;
	
	public EntityImpl() throws Exception
	{
		readImage = Outside.service(this,"gus06.file.read.image.generic");
		buildInfos = Outside.service(this,"gus06.awt.bufferedimage.color.hsbinfos1");
		buildListing = Outside.service(this,"gus06.dir.listing0.ext.image");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		
		File f = new File(dir.getAbsolutePath()+"_"+now()+"_hsbinfos.txt");
		if(f.exists()) f.delete();
		PrintStream p = new PrintStream(f,charset.name());
		
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
