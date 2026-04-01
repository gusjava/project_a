package a.entity.gus06.sys.editor16x16.r.scale.part;

import a.framework.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.plaf.OptionPaneUI;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250828";}

	public final static String TRANSPARENT = "255-255-255-0";
	public final static int PART_LENGTH = 7;

	private Service resize;
	private Service dataToImage;

	public EntityImpl() throws Exception
	{
		resize = Outside.service(this,"gus06.awt.bufferedimage.resize.s16x16.reduced");
		dataToImage = Outside.service(this,"gus06.sys.editor16x16.t.datatoimage");
	}


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String[][] data = (String[][]) obj;
		int n = data.length;
		
		if(n<=PART_LENGTH) return data;
		
		BufferedImage image = (BufferedImage) dataToImage.t(data);
		image = (BufferedImage) resize.t(new Object[]{image, PART_LENGTH});
		
		int offset = n-PART_LENGTH;
		String[][] newData = new String[n][n];
		for(int i=0;i<n;i++)
		for(int j=0;j<n;j++)
		{
			if(i<offset || j<offset) newData[i][j] = TRANSPARENT;
			else newData[i][j] = encodeFromImage(image, i-offset, j-offset);
		}
		return newData;
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