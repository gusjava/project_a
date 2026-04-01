package a.entity.gus06.swing.comp.build.compreplacer;

import java.awt.Window;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import a.framework.*;


public abstract class Holder implements E, Runnable {

	
	protected JComponent comp;
	
	
	public Holder(JComponent comp)
	{
		this.comp = comp;
	}
	
	public void e() throws Exception
	{
		replaceComp();
		SwingUtilities.invokeLater(this);
	}

	public void run()
	{
		try
		{
			Window w = SwingUtilities.getWindowAncestor(comp);
			w.setBounds(w.getX(),w.getY(),w.getWidth()+1,w.getHeight());
		}
		catch(Throwable e){}
	}
	
	
	protected abstract void replaceComp();

}
