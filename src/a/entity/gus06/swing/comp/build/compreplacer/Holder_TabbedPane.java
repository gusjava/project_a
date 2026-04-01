package a.entity.gus06.swing.comp.build.compreplacer;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;


public class Holder_TabbedPane extends Holder {

	
	private JTabbedPane parent;
	
	private int index;
	
	private Component tabComponent;
	private Icon icon;
	private String title;
	private String tip;
	

	public Holder_TabbedPane(JTabbedPane parent, JComponent comp)
	{
		super(comp);
		this.parent = parent;
		index = parent.indexOfComponent(comp);
		
		tabComponent = parent.getTabComponentAt(index);
		icon = parent.getIconAt(index);
		title = parent.getTitleAt(index);
		tip = parent.getToolTipTextAt(index);
	}

	protected void replaceComp()
	{
		parent.insertTab(title,icon,comp,tip,index);
		parent.setTabComponentAt(index,tabComponent);
		parent.setSelectedComponent(comp);
	}
}
