package a.entity.gus06.awt.bufferedimage.build.frommap1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import a.framework.*;
import java.util.Map;
import java.util.List;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.RenderingHints;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250825";}

	public static final String KEY_DRAWS = "draws";
	public static final String KEY_BACKGROUND = "background";
	
	private Service findDimension;
	private Service findColor;
	private Service buildDraw;

	public EntityImpl() throws Exception
	{
		findDimension = Outside.service(this,"gus06.convert.maptodimension");
		findColor = Outside.service(this,"gus06.find.color");
		buildDraw = Outside.service(this,"gus06.sys.draw1.main");
	}
	

	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		List draws = findDraws(map);
		Color background = findBackground(map);
		Dimension dim = findDimension(map);
		
		int w = dim.width;
		int h = dim.height;
		
		BufferedImage img = img(w,h);
		Graphics2D g = img.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,	RenderingHints.VALUE_STROKE_PURE);
		
		g.setColor(background);
		g.fillRect(0,0,w,h);
		
		for(int i=0;i<draws.size();i++)
		toDraw(draws.get(i)).p(g);
			
		g.dispose();
		return img;
	}
	
	
	
	private P toDraw(Object obj) throws Exception
	{
		if(obj instanceof P) return (P) obj;
		return (P) buildDraw.t(obj);
	}
	
	
	private List findDraws(Map map) throws Exception
	{
		if(map.containsKey(KEY_DRAWS))
			return (List) map.get(KEY_DRAWS);
		return new ArrayList();
	}
	
	
	private Color findBackground(Map map) throws Exception
	{
		if(map.containsKey(KEY_BACKGROUND)) 
			return (Color) findColor.t(map.get(KEY_BACKGROUND));
		return Color.WHITE;
	}
	
	
	private Dimension findDimension(Map map) throws Exception
	{return (Dimension) findDimension.t(map);}
	
	
	private BufferedImage img(int w, int h)
	{return new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);}
}