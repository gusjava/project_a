package a.entity.gus06.data.viewer.action;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.Action;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140731";}


	private Action data;
	private JButton button;

	public EntityImpl() throws Exception
	{
		button = new JButton();
		button.setEnabled(false);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return button;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Action) obj;
		button.setAction(data);
		button.setEnabled(data!=null);
	}
}
