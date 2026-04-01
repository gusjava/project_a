package a.entity.gus06.swing.comp.build.compreplacer;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class Holder_PanelBorderLayout extends Holder {

	private JPanel parent;
	private Object constraints;
	

	public Holder_PanelBorderLayout(JPanel parent, JComponent comp)
	{
		super(comp);
		this.parent = parent;
		
		BorderLayout layout = (BorderLayout) parent.getLayout();
		constraints = layout.getConstraints(comp);
	}

	
	protected void replaceComp()
	{parent.add(comp,constraints);}
}
