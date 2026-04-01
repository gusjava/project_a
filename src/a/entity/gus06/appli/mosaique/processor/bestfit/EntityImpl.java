package a.entity.gus06.appli.mosaique.processor.bestfit;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141122";}


	private Service getImages;
	private Service getDistance;
	private Service readFile;


	public EntityImpl() throws Exception
	{
		getImages = Outside.service(this,"gus06.appli.mosaique.data.dir.images");
		getDistance = Outside.service(this,"gus06.appli.mosaique.data.dist");
		readFile = Outside.service(this,"gus06.file.read.image.imageio.cache");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		BufferedImage img = (BufferedImage) obj;
    	
    		File[] f = (File[]) getImages.g();
		if(f==null || f.length==0) return img;
		
		T dist = (T) getDistance.g();
		if(dist==null) return img;
		
		return bestFit(img,f,dist);
	}
	
	
	
	private BufferedImage bestFit(BufferedImage img, File[] f, T dist) throws Exception
	{
		double min = Double.MAX_VALUE;
		File file1 = null;
		
    		for(int i=0;i<f.length;i++)
    		{
    			double d = computeDistance(img,f[i],dist);
    			if(d < min)
			{
				min = d;
				file1 = f[i];
			}
    		}
		return readFile(file1);
	}
	
	
	
	
	private double computeDistance(BufferedImage img, File f, T dist)
	{
		try
		{
			BufferedImage img1 = readFile(f);
			if(img1==null) throw new Exception("Failed to read image from file: "+f);
		
			Double val =  (Double) dist.t(new BufferedImage[]{img,img1});
			return val.doubleValue();
		}
		catch(Exception e)
		{Outside.err(this,"computeDistance(BufferedImage,File,T)",e);}
		return Double.MAX_VALUE;
	}
	
	
	private BufferedImage readFile(File f) throws Exception
	{return (BufferedImage) readFile.t(f);}
}
