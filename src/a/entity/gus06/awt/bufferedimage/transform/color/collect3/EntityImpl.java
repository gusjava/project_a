package a.entity.gus06.awt.bufferedimage.transform.color.collect3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180411";}

	
	private Service toBufferedImage;
	private Service applyColor;
	
	public EntityImpl() throws Exception
	{
		toBufferedImage = Outside.service(this,"gus06.find.bufferedimage");
		applyColor = Outside.service(this,"gus06.awt.color.apply.h.torgb");
	}
	
	

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		BufferedImage image = (BufferedImage) toBufferedImage.t(o[0]);
		T t = (T) o[1];
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		BufferedImage buffImg = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		
		for(int i=0;i<width;i++) for(int j=0;j<height;j++)
		{
			int rgb = image.getRGB(i,j);
			Color c = new Color(rgb);
			
			double fx = (double)i/(double)width;
			double fy = (double)j/(double)height;
			
			int red = c.getRed();
			int green = c.getGreen();
			int blue = c.getBlue();
			
			float[] hsb = Color.RGBtoHSB(red,green,blue,null);
			
			Map map = new HashMap();
			
			map.put("x",Integer.valueOf(i));
			map.put("y",Integer.valueOf(j));
			
			map.put("fx",Double.valueOf(fx));
			map.put("fy",Double.valueOf(fy));
			
			map.put("color",c);
			map.put("image",image);
			map.put("width",width);
			map.put("height",height);
			
			map.put("r",Integer.valueOf(red));
			map.put("g",Integer.valueOf(green));
			map.put("b",Integer.valueOf(blue));
			
			map.put("r1",Integer.valueOf(255-red));
			map.put("g1",Integer.valueOf(255-green));
			map.put("b1",Integer.valueOf(255-blue));
			
			map.put("H",Float.valueOf(hsb[0]));
			map.put("S",Float.valueOf(hsb[1]));
			map.put("B",Float.valueOf(hsb[2]));
			
			Color c1 = (Color) t.t(map);
			
			g.setColor(c1);
			g.drawRect(i,j,0,0);
		}
		g.dispose();
		return buffImg;
	}
}
