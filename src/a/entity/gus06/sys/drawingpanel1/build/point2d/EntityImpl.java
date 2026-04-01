package a.entity.gus06.sys.drawingpanel1.build.point2d;

import a.framework.*;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170425";}


	private Service findPoint;
	private Service findDouble2;
	
	public EntityImpl() throws Exception
	{
		findPoint = Outside.service(this,"gus06.find.point2d");
		findDouble2 = Outside.service(this,"gus06.find.doublearray.len2");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		JComponent comp = (JComponent) o[1];
		Object dimension = o[2];
		
		if(dimension==null) return findPoint.t(data);
		
		double[] p = (double[]) findDouble2.t(data);
		double[] q = (double[]) findDouble2.t(dimension);
		
		double x = p[0]*comp.getWidth()/q[0];
		double y = p[1]*comp.getHeight()/q[1];
		
		return findPoint.t(new double[]{x,y});
	}
}
