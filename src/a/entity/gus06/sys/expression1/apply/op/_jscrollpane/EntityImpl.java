package a.entity.gus06.sys.expression1.apply.op._jscrollpane;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import java.awt.Image;
import java.awt.image.RenderedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180402";}


	private Service screenBuilder;

	public EntityImpl() throws Exception
	{
		screenBuilder = Outside.service(this,"factory#gus06.swing.scrollpane.screen.image");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof JComponent) return compToJPanel((JComponent) obj);
		if(obj instanceof RenderedImage) return imageToJPanel(obj);
		if(obj instanceof Image) return imageToJPanel(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private JScrollPane compToJPanel(JComponent comp)
	{
		return new JScrollPane(comp);
	}
	
	private JScrollPane imageToJPanel(Object obj) throws Exception
	{
		Object viewer = screenBuilder.g();
		((P) viewer).p(obj);
		return (JScrollPane) ((I) viewer).i();
	}
}
