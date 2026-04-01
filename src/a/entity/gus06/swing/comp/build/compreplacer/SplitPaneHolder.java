package a.entity.gus06.swing.comp.build.compreplacer;

import java.awt.Component;

import a.framework.*;


import javax.swing.JSplitPane;


public class SplitPaneHolder implements E {

	
	private JSplitPane parent;
	private Component rightComp;
	private Component leftComp;
	
	

	public SplitPaneHolder(JSplitPane parent)
	{
		this.parent = parent;
		rightComp = parent.getRightComponent();
		leftComp = parent.getLeftComponent();
	}

	
	public void e() throws Exception
	{
		parent.setRightComponent(rightComp);
		parent.setLeftComponent(leftComp);
	}

}
