package a.entity.gus06.awt.bufferedimage.transform.color.h.torgb.blue;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180411";}

	
	private Service toBufferedImage;
	private Service applyColor;
	
	public EntityImpl() throws Exception
	{
		toBufferedImage = Outside.service(this,"gus06.find.bufferedimage");
		applyColor = Outside.service(this,"gus06.awt.color.apply.h.torgb.blue");
	}
	
	

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		BufferedImage image = (BufferedImage) toBufferedImage.t(o[0]);
		H h = (H) o[1];
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		BufferedImage buffImg = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		
		for(int i=0;i<width;i++) for(int j=0;j<height;j++)
		{
			int rgb = image.getRGB(i,j);
			Color c = new Color(rgb);
			
			Color c1 = (Color) applyColor.t(new Object[]{c,h});
			
			g.setColor(c1);
			g.drawRect(i,j,0,0);
		}
		g.dispose();
		return buffImg;
	}
}
