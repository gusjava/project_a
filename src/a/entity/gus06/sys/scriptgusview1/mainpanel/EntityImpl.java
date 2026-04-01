package a.entity.gus06.sys.scriptgusview1.mainpanel;

import a.framework.*;
import javax.swing.JSplitPane;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I, P {

	public String creationDate() {return "20250319";}


	private Service selector;
	private Service custSplit;
	private Service view;


	private JSplitPane split;

	public EntityImpl() throws Exception
	{
		selector = Outside.service(this,"*gus06.sys.listchooser1.gui.selector1");
		custSplit = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		view = Outside.service(this,"*gus06.sys.scriptgusview1.view");
		
		split = new JSplitPane();
		split.setLeftComponent((JComponent) selector.i());
		split.setRightComponent((JComponent) view.i());
		
		custSplit.p(split);
		selector.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	public void p(Object obj) throws Exception
	{
		selector.p(obj);
	}


	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("selectionChanged()")) {selectionChanged();return;}
	}
	
	
	
	private void selectionChanged()
	{
		try{view.p(selector.g());}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}