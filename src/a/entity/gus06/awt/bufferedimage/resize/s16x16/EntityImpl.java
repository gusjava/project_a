package a.entity.gus06.awt.bufferedimage.resize.s16x16;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201208";}

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
		BufferedImage image = (BufferedImage) obj;
		if(image.getWidth()==16 && image.getHeight()==16) return image;
		
		return perform.t(new Object[]{image,DIM,BACKGROUND,MODE_FIT});
	}
}