package a.entity.gus06.sys.expression1.apply.op._rotate180;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import java.awt.image.RenderedImage;
import java.awt.Image;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151115";}


	private Service perform;
	
	private Service findRenderedImage;
	private Service findImage;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.rotate180");
		
		findRenderedImage = Outside.service(this,"gus06.find.renderedimage");
		findImage = Outside.service(this,"gus06.find.image");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof JPanel) return perform.t(obj);
		if(obj instanceof JTabbedPane) return perform.t(obj);
		if(obj instanceof JSplitPane) return perform.t(obj);
		if(obj instanceof RenderedImage) return findImage.t(perform.t(obj));
		if(obj instanceof Image) return findImage.t(perform.t(findRenderedImage.t(obj)));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
