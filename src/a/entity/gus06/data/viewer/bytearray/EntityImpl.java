package a.entity.gus06.data.viewer.bytearray;

import a.framework.*;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20190924";}


	private Service tab;
	private Service viewer1;
	private Service viewer2;

	private byte[] data;
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		viewer1 = Outside.service(this,"*gus06.data.viewer.bytearray.area");
		viewer2 = Outside.service(this,"*gus06.sys.bytearray.viewer1.gui.maingui");
		
		tab.v("Hexa",viewer1.i());
		tab.v("Convertor",viewer2.i());
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	public void p(Object obj) throws Exception
	{
		data = (byte[]) obj;
		
		viewer1.p(data);
		viewer2.p(data);
	}
}
