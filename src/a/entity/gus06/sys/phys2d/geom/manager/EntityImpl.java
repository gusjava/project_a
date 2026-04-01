package a.entity.gus06.sys.phys2d.geom.manager;

import java.awt.Point;
import java.awt.Rectangle;
import a.framework.*;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20200516";}


	private Service mvtManager;
	private Service sizeManager;


	public EntityImpl() throws Exception
	{
		mvtManager = Outside.service(this,"gus06.sys.phys2d.mvt.manager");
		sizeManager = Outside.service(this,"gus06.sys.phys2d.size.manager");
	}
	


	public Object r(String key) throws Exception
	{
		double[] position = (double[]) mvtManager.r("position."+key);
		if(position==null) return null;
		
		int x = (int) position[0];
		int y = (int) position[1];
		
		double[] size = (double[]) sizeManager.r(key);
		if(size==null) return new Point(x,y);
		
		int w = (int) size[0];
		int h = (int) size[1];
		
		return new Rectangle(x,y,w,h);
	}


}
