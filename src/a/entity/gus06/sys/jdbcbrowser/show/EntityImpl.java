package a.entity.gus06.sys.jdbcbrowser.show;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190516";}
	
	public static final String TITLE = "TABLE_cell_next#Browser";


	private Service show;
	private Service newViewer;
	
	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show");
		newViewer = Outside.service(this,"factory#gus06.sys.jdbcbrowser.gui.main");
	}
	
	private Object comp(Object obj) throws Exception
	{
		Object viewer = newViewer.g();
		((P)viewer).p(obj);
		return ((I)viewer).i();
	}
	
	
	public void p(Object obj) throws Exception
	{show.v(TITLE,comp(obj));}
}