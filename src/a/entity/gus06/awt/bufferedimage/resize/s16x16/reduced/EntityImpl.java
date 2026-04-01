package a.entity.gus06.awt.bufferedimage.resize.s16x16.reduced;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250828";}

	public static final String MODE_FIT = "fit";
	public static final int[] DIM = new int[]{16,16};
	public static final Color BACKGROUND = Color.WHITE;
	

	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.awt.bufferedimage.resize.perform");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		BufferedImage image = (BufferedImage) o[0];
		int length = ((Integer) o[1]).intValue();
		
		if(image.getWidth()==length && image.getHeight()==length) return image;
		
		int[] dim = new int[]{length,length};
		return perform.t(new Object[]{image,dim,BACKGROUND,MODE_FIT});
	}
}