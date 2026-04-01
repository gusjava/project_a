package a.entity.gus06.swing.label.perform.copy;

import a.framework.*;
import javax.swing.JLabel;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140821";}

	private Service toClipboard;
	
	public EntityImpl() throws Exception
	{
		toClipboard = Outside.service(this,"gus06.clipboard.access.string");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JLabel label = (JLabel) obj;
		toClipboard.p(label.getText());
	}
}
