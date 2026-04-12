package a.entity.gus06.swing.comp.build.compreplacer;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;


public class Holder_LayeredPane extends Holder {

	private JLayeredPane parent;
	

	public Holder_LayeredPane(JLayeredPane parent, JComponent comp)
	{
		super(comp);
		this.parent = parent;
	}

	
	protected void replaceComp()
	{
		parent.add(comp);
		parent.revalidate();
		parent.repaint();
	}

}
