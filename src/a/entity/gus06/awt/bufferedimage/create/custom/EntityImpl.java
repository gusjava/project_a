package a.entity.gus06.awt.bufferedimage.create.custom;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250627";}
	
	public static final String KEY_HEIGHT = "height";
	public static final String KEY_WIDTH = "width";
	public static final String KEY_DIMENSION = "dimension";
	public static final String KEY_COLOR = "color";


	private Service create;
	private Service findColor;

	public EntityImpl() throws Exception
	{
		create = Outside.service(this,"gus06.awt.bufferedimage.create");
		findColor = Outside.service(this,"gus06.find.color");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Object dim = findDimension(map);
		Color color = findColor(map);
		
		BufferedImage img = (BufferedImage) create.t(dim);
		
		Graphics2D g = img.createGraphics();  
		g.setColor(color);  
		g.fillRect(0,0,img.getWidth(),img.getHeight());  
		
		return img;
	}
	
	private Object findDimension(Map map) throws Exception
	{
		if(map.containsKey(KEY_DIMENSION))
			return map.get(KEY_DIMENSION);
			
		if(map.containsKey(KEY_HEIGHT) && map.containsKey(KEY_WIDTH)) {
			Integer height = (Integer) map.get(KEY_HEIGHT);
			Integer width = (Integer) map.get(KEY_WIDTH);
			return new int[]{height, width};
		}
		throw new Exception("Dimension not found for custom image creation");
	}
	
	private Color findColor(Map map) throws Exception
	{
		if(!map.containsKey(KEY_COLOR)) return Color.WHITE;
		return (Color) findColor.t(map.get(KEY_COLOR));
	}
}
