package a.entity.gus06.sys.editor16x16.t.imagetodata;

import a.framework.*;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250310";}
	
	public final static int NB = 16;
	public final static String TRANSPARENT = "255-255-255-0";
	
	
	public Object t(Object obj) throws Exception
	{
		BufferedImage image = (BufferedImage) obj;
		if(image==null) return null;
		
		if(image.getWidth(null)!=NB) throw new Exception("Invalid 16x16 image");
		if(image.getHeight(null)!=NB) throw new Exception("Invalid 16x16 image");
		
		String[][] data = new String[NB][NB];
		for(int i=0;i<NB;i++)
		for(int j=0;j<NB;j++)
		data[i][j] = encodeFromImage(image, i,j);
		return data;
	}
	
	
	private String encodeFromImage(BufferedImage image, int x, int y)
	{
		int rgba = image.getRGB(y,x);
		
		int a = (rgba >> 24) & 0xFF;
		int r = (rgba >> 16) & 0xFF;
		int g = (rgba >> 8)  & 0xFF;
		int b = rgba         & 0xFF;
		
		if(a==0) return TRANSPARENT;
		return r+"-"+g+"-"+b+"-"+a;
	}
}