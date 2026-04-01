package a.entity.gus06.sys.drawingpanel1.build.point2d.inv;

import a.framework.*;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170820";}


	private Service findDouble2;
	
	public EntityImpl() throws Exception
	{
		findDouble2 = Outside.service(this,"gus06.find.doublearray.len2");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		JComponent comp = (JComponent) o[1];
		Object dimension = o[2];
		
		if(dimension==null) return data;
		
		double[] p = (double[]) findDouble2.t(data);
		double[] q = (double[]) findDouble2.t(dimension);
		
		double x = p[0]*q[0]/comp.getWidth();
		double y = p[1]*q[1]/comp.getHeight();
		
		return new double[]{x,y};
	}
}
