package a.entity.gus06.awt.bufferedimage.transform.color.hue;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151004";}

	
	private Service toBufferedImage;
	private Service findHue;
	
	public EntityImpl() throws Exception
	{
		toBufferedImage = Outside.service(this,"gus06.find.bufferedimage");
		findHue = Outside.service(this,"gus06.find.float1.ashuecolor");
	}
	
	

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		BufferedImage image = (BufferedImage) toBufferedImage.t(o[0]);
		float hue = ((Float) findHue.t(o[1])).floatValue();
		
		int width = image.getWidth();
		int heigth = image.getHeight();
		
		BufferedImage buffImg = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		
		for(int i=0;i<width;i++) for(int j=0;j<heigth;j++)
		{
			int rgb = image.getRGB(i,j);
			Color c = new Color(rgb);
			
			float[] hsb = Color.RGBtoHSB(c.getRed(),c.getGreen(),c.getBlue(),null);
			float saturation = hsb[1];
			float brightness = hsb[2];
			
			g.setColor(Color.getHSBColor(hue,saturation,brightness));
			g.drawRect(i,j,0,0);
		}
		g.dispose();
		return buffImg;
	}
}
