package a.entity.gus06.swing.comp.build.compreplacer;

import java.awt.Component;
import javax.swing.JSplitPane;


public class Holder_SplitPane extends Holder {

	
	private JSplitPane parent;
	private Component rightComp;
	private Component leftComp;
	private int dividerLocation;
	
	

	public Holder_SplitPane(JSplitPane parent)
	{
		super(null);
		this.parent = parent;
		rightComp = parent.getRightComponent();
		leftComp = parent.getLeftComponent();
		dividerLocation = parent.getDividerLocation();
	}

	
	protected void replaceComp()
	{
		parent.setRightComponent(rightComp);
		parent.setLeftComponent(leftComp);
		parent.setDividerLocation(dividerLocation);
	}
}
