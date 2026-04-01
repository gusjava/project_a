package a.entity.gus06.convert.buttontos;

import a.framework.*;
import javax.swing.AbstractButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161216";}

	
	
	public Object t(Object obj) throws Exception
	{
		AbstractButton button = (AbstractButton) obj;
		return new Holder(button);
	}
	
	
	private class Holder implements S
	{
		private AbstractButton button;
		public Holder(AbstractButton button)
		{this.button = button;}
		
		public void addActionListener(ActionListener listener)
		{button.addActionListener(listener);}
		
		public void removeActionListener(ActionListener listener)
		{button.removeActionListener(listener);}
		
		public List listeners()
		{
			ActionListener[] arr = button.getActionListeners();
			return new ArrayList(Arrays.asList(arr));
		}
	}
}
