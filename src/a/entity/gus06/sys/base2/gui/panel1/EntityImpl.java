package a.entity.gus06.sys.base2.gui.panel1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20221104";}


	private Service guiSearch;
	private Service guiPreview;
	private Service guiResult;

	private Object base;
	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		guiSearch = Outside.service(this,"*gus06.sys.base2.gui.panel1.search");
		guiPreview = Outside.service(this,"*gus06.sys.base2.gui.panel1.preview");
		guiResult = Outside.service(this,"*gus06.sys.base2.gui.panel1.result");
		
		JPanel panelTop = new JPanel(new GridLayout(1,2));
		panelTop.add((JComponent) guiSearch.i());
		panelTop.add((JComponent) guiPreview.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(panelTop, BorderLayout.NORTH);
		panel.add((JComponent) guiResult.i(), BorderLayout.CENTER);
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		base = obj;
		
		guiSearch.p(obj);
		guiPreview.p(obj);
		guiResult.p(obj);
	}
}