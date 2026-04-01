package a.entity.gus06.sys.autocomplete1.repeatline;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, V, P {

	public String creationDate() {return "20201130";}


	private Service selectionMono;
	private Service selectionMulti;
	private Service caret;


	public EntityImpl() throws Exception
	{
		selectionMono = Outside.service(this,"gus06.sys.autocomplete1.repeatline.selection.mono");
		selectionMulti = Outside.service(this,"gus06.sys.autocomplete1.repeatline.selection.multi");
		caret = Outside.service(this,"gus06.sys.autocomplete1.repeatline.caret");
	}
	
	
	public void p(Object obj) throws Exception
	{v("2",obj);}
	
	
	public void v(String key, Object obj) throws Exception
	{
		int nb = Integer.parseInt(key);
		if(nb<0) throw new Exception("Invalid nb value: "+nb);
		
		if(obj instanceof JTextArea)
		perform((JTextComponent) obj,nb);
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private void perform(JTextComponent comp, int nb) throws Exception
	{
		if(hasSelectionMulti(comp))
		{
			selectionMulti.v(""+nb,comp);
			return;
		}
		if(hasSelection(comp))
		{
			selectionMono.v(""+nb,comp);
			return;
		}
		caret.v(""+nb,comp);
	}
	
	
	private boolean hasSelection(JTextComponent comp)
	{
		String s = comp.getSelectedText();
		return s!=null && !s.equals("");
	}
	
	private boolean hasSelectionMulti(JTextComponent comp)
	{
		String s = comp.getSelectedText();
		return s!=null && s.contains("\n");
	}
}