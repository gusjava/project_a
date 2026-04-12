package a.entity.gus06.swing.comp.build.compreplacer;

import a.framework.*;


import javax.swing.JComponent;
import javax.swing.JViewport;


public class ViewportHolder implements E {

	private JComponent comp;
	private JViewport parent;
	

	public ViewportHolder(JViewport parent, JComponent comp)
	{
		this.comp = comp;
		this.parent = parent;
	}

	
	public void e() throws Exception
	{parent.setView(comp);}

}
