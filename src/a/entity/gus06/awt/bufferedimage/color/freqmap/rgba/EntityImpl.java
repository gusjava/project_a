package a.entity.gus06.awt.bufferedimage.color.freqmap.rgba;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250301";}
	
	public static final int N = 100;

	
	private Service toBufferedImage;
	
	public EntityImpl() throws Exception
	{
		toBufferedImage = Outside.service(this,"gus06.find.bufferedimage");
	}


	public Object t(Object obj) throws Exception
	{
		BufferedImage image = (BufferedImage) toBufferedImage.t(obj);
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		Map map = new HashMap();
		
		for(int i=0;i<width;i++) for(int j=0;j<height;j++)
		{
			int rgba = image.getRGB(i,j);
			
//			Color c = new Color(rgba);
//			int r = c.getRed();
//			int g = c.getGreen();
//			int b = c.getBlue();
//			int a = c.getAlpha();

			int a = (rgba >> 24) & 0xFF;  // Masque sur les 8 bits de poids fort
			int r = (rgba >> 16) & 0xFF;
			int g = (rgba >> 8)  & 0xFF;
			int b = rgba         & 0xFF;
			
			String key = r+"-"+g+"-"+b+"-"+a;
			if(!map.containsKey(key)) map.put(key,1);
			else map.put(key,1+(Integer) map.get(key));
		}
		return map;
	}
}