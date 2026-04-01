package a.entity.gus06.image.description;

import a.framework.*;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.awt.image.ColorModel;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191008";}


	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof BufferedImage) return desc((BufferedImage) obj);
		if(obj instanceof RenderedImage) return desc((RenderedImage) obj);
		if(obj instanceof Image) return desc((Image) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String desc(BufferedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();
		int bands = image.getData().getNumBands();
		int type = image.getType();
		return "["+w+","+h+"] bands="+bands+" type="+typeDesc(type);
	}
	
	
	private String desc(Image image)
	{
		int w = image.getWidth(null);
		int h = image.getHeight(null);
		return "["+w+","+h+"]";
	}
	
	
	private String desc(RenderedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();
		int bands = image.getData().getNumBands();
		return "["+w+","+h+"] bands="+bands;
	}
	
	
	private String typeDesc(int type)
	{
		if(type==BufferedImage.TYPE_CUSTOM) return "TYPE_CUSTOM";
		if(type==BufferedImage.TYPE_INT_RGB) return "TYPE_INT_RGB";
		if(type==BufferedImage.TYPE_INT_ARGB) return "TYPE_INT_ARGB";
		if(type==BufferedImage.TYPE_INT_ARGB_PRE) return "TYPE_INT_ARGB_PRE";
		if(type==BufferedImage.TYPE_INT_BGR) return "TYPE_INT_BGR";
		if(type==BufferedImage.TYPE_3BYTE_BGR) return "TYPE_3BYTE_BGR";
		if(type==BufferedImage.TYPE_4BYTE_ABGR) return "TYPE_4BYTE_ABGR";
		if(type==BufferedImage.TYPE_4BYTE_ABGR_PRE) return "TYPE_4BYTE_ABGR_PRE";
		if(type==BufferedImage.TYPE_USHORT_565_RGB) return "TYPE_USHORT_565_RGB";
		if(type==BufferedImage.TYPE_USHORT_555_RGB) return "TYPE_USHORT_555_RGB";
		if(type==BufferedImage.TYPE_BYTE_GRAY) return "TYPE_BYTE_GRAY";
		if(type==BufferedImage.TYPE_USHORT_GRAY) return "TYPE_USHORT_GRAY";
		if(type==BufferedImage.TYPE_BYTE_BINARY) return "TYPE_BYTE_BINARY";
		if(type==BufferedImage.TYPE_BYTE_INDEXED) return "TYPE_BYTE_INDEXED";
		
		return "[Unknown value "+type+"]";
	}
}
