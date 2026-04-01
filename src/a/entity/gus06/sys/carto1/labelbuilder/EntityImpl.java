package a.entity.gus06.sys.carto1.labelbuilder;

import java.awt.Rectangle;
import javax.swing.JLabel;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161202";}

	public static int X0 = 10;
	public static int Y0 = 10;



	public Object t(Object obj) throws Exception
	{
		String name = obj.toString();
		int w = new JLabel(name).getPreferredSize().width;
		/*
		 * PENDING
		 * Use the getStringBounds method of the FontMetrics class
		 */
		return new Rectangle(X0,Y0,w,15);
	}
}
