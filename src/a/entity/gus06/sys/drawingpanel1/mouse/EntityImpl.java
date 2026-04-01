package a.entity.gus06.sys.drawingpanel1.mouse;

import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl implements Entity, G, V {

	public String creationDate() {return "20170820";}


	private Service mousePosition;
	private Service buildInv;


	private Object dimension;
	private JComponent comp;
	

	public EntityImpl() throws Exception
	{
		mousePosition = Outside.service(this,"gus06.swing.comp.mouse.position");
		buildInv = Outside.service(this,"gus06.sys.drawingpanel1.build.point2d.inv");
	}
	
	
	
	public synchronized void v(String key, Object obj) throws Exception
	{
		if(key.equals("dimension")) {dimension = obj;return;}
		if(key.equals("comp")) {comp = (JComponent) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object g() throws Exception
	{
		if(comp==null) throw new Exception("Comp not initialized yet");
		if(dimension==null) throw new Exception("Dimension not initialized yet");
		
		int[] p = (int[]) mousePosition.t(comp);
		if(p==null) return null;
		
		return buildInv.t(new Object[]{p,comp,dimension});
	}
}
