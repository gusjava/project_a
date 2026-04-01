package a.entity.gus06.awt.focus.focussupport;

import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, PropertyChangeListener {

	public String creationDate() {return "20151015";}


	private Object lastFocused;
	

	public EntityImpl() throws Exception
	{
		KeyboardFocusManager focusManager =
			KeyboardFocusManager.getCurrentKeyboardFocusManager();
		focusManager.addPropertyChangeListener(this);
	}
	
	
	
	public Object g() throws Exception
	{return lastFocused;}
	
	
	
	public void propertyChange(PropertyChangeEvent e)
	{
		String prop = e.getPropertyName();
		if(prop.equals("focusOwner"))
		{
			lastFocused = e.getNewValue();
			focusChanged();
		}
	}
	
	
	private void focusChanged()
	{send(this,"focusChanged()");}
}
