package a.entity.gus06.sys.editor16x16.clipboard.g2;

import a.framework.*;
import java.awt.Color;
import java.io.File;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20250319";}

	public final static int NB = 16;
	public final static String TRANSPARENT = "255-255-255-0";


	private Service accessImage;
	private Service accessString;
	private Service accessFile;
	private Service readImage;
	private Service resizeImage;
	private Service imageToData;


	public EntityImpl() throws Exception
	{
		accessImage = Outside.service(this,"gus06.clipboard.access.image");
		accessString = Outside.service(this,"gus06.clipboard.access.string");
		accessFile = Outside.service(this,"gus06.clipboard.access.file");
		readImage = Outside.service(this,"gus06.file.read.image.imageio");
		resizeImage = Outside.service(this,"gus06.awt.bufferedimage.resize.s16x16");
		imageToData = Outside.service(this,"gus06.sys.editor16x16.t.imagetodata");
	}

	
	
	public Object g() throws Exception
	{
		Object file = accessFile.g();
		if(file!=null) return fromFile((File) file);
		
		Object img = accessImage.g();
		if(img!=null) return fromImage((BufferedImage) img);
		
		Object string = accessString.g();
		if(string!=null) return fromString((String) string);
		
		return null;
	}
	
	
	private Object fromFile(File file) throws Exception
	{
		Object img = readImage.t(file);
		return fromImage((BufferedImage) img);
	}
	
	
	private Object fromImage(BufferedImage img) throws Exception
	{
		img = (BufferedImage) resizeImage.t(img);
		return removeTransparency((String[][]) imageToData.t(img));
	}
	
	
	
	public Object fromString(String s) throws Exception
	{
		if(s.equals("")) return null;
		
		if(!s.contains(";")) return computeColor(s);
		String[] nn = s.replace("\n","").replace("\r","").split(";");
		
		String[][] data = new String[NB][NB];
		
		for(String n : nn)
		{
			String[] t = n.split("=");
			String[] kk = t[0].split(",");
			String c = computeColor(t[1]);
			
			for(String k : kk)
			{
				int[] p = keyToPoint(k);
				if(p[0]<0 || p[0]>=NB) throw new Exception("Invalid x coordinate inside chunk: "+k);
				if(p[1]<0 || p[1]>=NB) throw new Exception("Invalid y coordinate inside chunk: "+k);
				data[p[0]][p[1]] = c;
			}
		}
		return removeTransparency(data);
	}
	
	private String computeColor(String c) throws Exception
	{
		if(!c.matches("[0-9]+\\-[0-9]+\\-[0-9]+\\-[0-9]+")) return null;
		return c;
	}
	
	private int[] keyToPoint(String key)
	{
		String[] n = key.split("-");
		return new int[]{toInt(n[0]), toInt(n[1])};
	}
	
	private int toInt(String s)
	{
		try{return Integer.parseInt(s);}
		catch(NumberFormatException e){return -1;}
	}
	
	private String[][] removeTransparency(String[][] data)
	{
		for(int i=0;i<NB;i++)
		for(int j=0;j<NB;j++)
			if(data[i][j]!=null && data[i][j].equals(TRANSPARENT))
			data[i][j] = null;
		return data;
	}
}