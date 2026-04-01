package a.entity.gus06.swing.comp.cust2.border.titledborder1.mp10;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import a.framework.*;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20170923";}


	public static final Color COLOR = new Color(0,204,204);
	public static final Border EMPTY = BorderFactory.createEmptyBorder(10,10,10,10);



	public void v(String key, Object obj) throws Exception
	{
		JComponent comp = toComp(obj);
		TitledBorder b = titled(key);
		
		Border b1 = BorderFactory.createCompoundBorder(EMPTY,b);
		Border b2 = BorderFactory.createCompoundBorder(b1,EMPTY);
		comp.setBorder(b2);
	}

	
	
	private TitledBorder titled(String title)
	{
		TitledBorder b = BorderFactory.createTitledBorder(title);
		b.setTitleColor(COLOR);
		b.setTitleJustification(TitledBorder.CENTER);
		return b;
	}
	
	
	private JComponent toComp(Object obj) throws Exception
	{
		if(obj instanceof JComponent) return (JComponent) obj;
		if(obj instanceof I) return (JComponent) ((I) obj).i();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}

}
