/*
 * Created on 5 oct. 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package a.entity.gus06.swing.comp.build.compreplacer;

import a.framework.*;


import javax.swing.JComponent;
import javax.swing.JPanel;


public class PanelGridLayoutHolder implements E {

	private JComponent comp;
	private JPanel parent;
	

	public PanelGridLayoutHolder(JPanel parent, JComponent comp)
	{
		this.comp = comp;
		this.parent = parent;
	}

	
	public void e() throws Exception
	{parent.add(comp);}

}
