package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir.summary;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200108";}

	private Service preview;
	private Service form;
	private Service toolbar;
	
	private JPanel panel;
	
	public EntityImpl() throws Exception
	{
		preview = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.dir.summary.preview");
		form = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.dir.summary.form");
		toolbar = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.dir.summary.toolbar");
		
		panel = nc(ce(form.i(),toolbar.i()),preview.i());
	}
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		form.p(obj);
		preview.p(obj);
		toolbar.p(obj);
	}
	
	private JPanel nc(Object n, Object c)
	{
		JPanel panel = new JPanel(new BorderLayout());
		if(n!=null) panel.add((JComponent) n,BorderLayout.NORTH);
		if(c!=null) panel.add((JComponent) c,BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel ce(Object c, Object e)
	{
		JPanel panel = new JPanel(new BorderLayout());
		if(c!=null) panel.add((JComponent) c,BorderLayout.CENTER);
		if(e!=null) panel.add((JComponent) e,BorderLayout.EAST);
		return panel;
	}
}