package a.entity.gus06.swing.frame.show.text;

import a.framework.*;

public class EntityImpl implements Entity, T, V, P {

	public String creationDate() {return "20160331";}


	private Service show;
	private Service newViewer;


	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show");
		newViewer = Outside.service(this,"factory#gus06.data.viewer.string.textarea.editor1");
	}
	
	
	private Object comp(Object obj) throws Exception
	{
		Object viewer = newViewer.g();
		((P)viewer).p(obj);
		return ((I)viewer).i();
	}
	
	
	public void p(Object obj) throws Exception
	{show.p(comp(obj));}
	
	public void v(String key, Object obj) throws Exception
	{show.v(key,comp(obj));}
	
	public Object t(Object obj) throws Exception
	{return show.t(comp(obj));}
}