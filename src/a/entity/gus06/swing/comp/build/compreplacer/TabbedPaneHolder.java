package a.entity.gus06.swing.comp.build.compreplacer;

import a.framework.*;


import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;


public class TabbedPaneHolder implements E {

	
	private JComponent comp;
	private JTabbedPane parent;
	
	private int index;
	private Icon icon;
	private String title;
	private String tip;
	

	public TabbedPaneHolder(JTabbedPane parent, JComponent comp)
	{
		this.comp = comp;
		this.parent = parent;
		
		index = parent.indexOfComponent(comp);
		icon = parent.getIconAt(index);
		title = parent.getTitleAt(index);
		tip = parent.getToolTipTextAt(index);
	}

	
	public void e() throws Exception
	{
		parent.insertTab(title,icon,comp,tip,index);
		parent.setSelectedComponent(comp);
	}

}
