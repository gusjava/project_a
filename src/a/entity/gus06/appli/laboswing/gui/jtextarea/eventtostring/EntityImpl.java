package a.entity.gus06.appli.laboswing.gui.jtextarea.eventtostring;

import a.framework.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.CaretEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160501";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof DocumentEvent) return documentEvent((DocumentEvent) obj);
		if(obj instanceof CaretEvent) return obj.toString();
		
		return obj.toString();
	}
	
	
	private String documentEvent(DocumentEvent evt)
	{
		int length = evt.getLength();
		int offset = evt.getOffset();	
		return "offset="+offset+", length="+length;
	}
}
