package a.entity.gus06.swing.comp.build.compreplacer;


import javax.swing.JComponent;
import javax.swing.JViewport;

public class Holder_Viewport extends Holder {

	private JViewport parent;
	

	public Holder_Viewport(JViewport parent, JComponent comp)
	{
		super(comp);
		this.parent = parent;
	}

	protected void replaceComp()
	{parent.setView(comp);}
}
