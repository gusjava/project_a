package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.rectangle;

import a.framework.*;
import java.awt.Rectangle;
import java.awt.Point;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170107";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		Rectangle rec = (Rectangle) oo[0];
		int[] r = new int[]{rec.x,rec.y,rec.width,rec.height};
		
		for(int i=1;i<oo.length;i++) add(r,oo[i]);
		return new Rectangle(r[0],r[1],r[2],r[3]);
	}
	
	
	private void add(int[] r, Object o) throws Exception
	{
		if(o==null) throw new Exception("Invalid null value");
		
		if(o instanceof Integer)
		{
			int n = ((Integer) o).intValue();
			r[0] += n;
			r[1] += n;
			return;
		}
		
		if(o instanceof Point)
		{
			Point p = (Point) o;
			r[0] += p.x;
			r[1] += p.y;
			return;
		}
		
		if(o instanceof Rectangle)
		{
			Rectangle rec = (Rectangle) o;
			r[0] += rec.x;
			r[1] += rec.y;
			r[2] += rec.width;
			r[3] += rec.height;
			return;
		}
		
		if(o instanceof int[])
		{
			int[] nn = (int[]) o;
			if(nn.length==4)
			{
				for(int i=0;i<4;i++) r[i] += nn[i];
				return;
			}
			if(nn.length==2)
			{
				r[0] += nn[0];
				r[1] += nn[1];
				return;
			}
			throw new Exception("Invalid array length for sum: "+nn.length);
		}
	}
}
