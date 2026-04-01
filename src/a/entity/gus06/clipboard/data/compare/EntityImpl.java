package a.entity.gus06.clipboard.data.compare;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20210103";}

	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return compareObj(o[0],o[1]);
	}
	
	
	private boolean compareObj(Object data1, Object data2) throws Exception
	{
		if(data1==null && data2==null) return true;
		if(data1==null || data2==null) return false;
		
		if(data1 instanceof String && data2 instanceof String)
			return compareString((String) data1, (String) data2);
		
		if(data1 instanceof List && data2 instanceof List)
			return compareList((List) data1, (List) data2);
		
		if(data1 instanceof BufferedImage && data2 instanceof BufferedImage)
			return compareImage((BufferedImage) data1, (BufferedImage) data2);
		
		throw new Exception("Invalid object types: "+data1+" & "+data2);
	}
	
	private boolean compareString(String data1, String data2)
	{return data1.equals(data2);}
	
	
	private boolean compareList(List data1, List data2)
	{
		if(data1.size()!=data2.size()) return false;
		for(int i=0;i<data1.size();i++)
		{
			File f1 = (File) data1.get(i);
			File f2 = (File) data2.get(i);
			if(!f1.getAbsolutePath().equals(f2.getAbsolutePath())) return false;
		}
		return true;
	}
	
	private boolean compareImage(BufferedImage data1, BufferedImage data2)
	{
		int h1 = data1.getHeight();
		int h2 = data2.getHeight();
		if(h1!=h2) return false;
		
		int w1 = data1.getWidth();
		int w2 = data2.getWidth();
		if(w1!=w2) return false;
		
		for(int x=0; x<w1; x++) 
		for(int y=0; y<h1; y++) 
                if (data1.getRGB(x,y) != data2.getRGB(x,y)) return false;

		return true;
	}
}
