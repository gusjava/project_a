package a.entity.gus06.sys.scriptgusview1.mainpanel.show;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250319";}
	
	public static final String TITLE = "SCRIPT_jar#Scripts inside JAR";
	public static final Dimension DIM = new Dimension(1000, 400);


	private Service show;
	private Service newViewer;

	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show2");
		newViewer = Outside.service(this,"factory#gus06.sys.scriptgusview1.mainpanel");
	}
	
	private Object comp(Object obj) throws Exception
	{
		Object viewer = newViewer.g();
		((P)viewer).p(obj);
		return ((I)viewer).i();
	}
	
	
	public void p(Object obj) throws Exception
	{show.p(new Object[]{comp(obj), DIM, TITLE});}
}
