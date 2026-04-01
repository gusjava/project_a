package a.entity.gus06.sys.filemanagement1.gui.detailpanel.file.handling;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, ActionListener, I, P {

	public String creationDate() {return "20200110";}
	
	private Service tab;
	
	private Service guiProp;
	private Service guiPreview;
	private Service guiAllocine;
	
	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		
		guiProp = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.file.handling.prop");
		guiPreview = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.file.handling.preview");
		guiAllocine = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.file.handling.allocine");
		
		tab.v("PROP#Prop",			guiProp.i());
		tab.v("IMG#Preview",			guiPreview.i());
		tab.v("WEBSITE_allocine#Allocine",	guiAllocine.i());
		
		guiProp.addActionListener(this);
		guiPreview.addActionListener(this);
		guiAllocine.addActionListener(this);
	}
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	public void p(Object obj) throws Exception
	{
		guiProp.p(obj);
		guiPreview.p(obj);
		guiAllocine.p(obj);
	}


	public void actionPerformed(ActionEvent e)
	{updated();}
	
	private void updated()
	{send(this,"updated()");}
}