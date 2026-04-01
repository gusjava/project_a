package a.entity.gus06.swing.textcomp.cust.action.ctrl_shift_v.paste.perform;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200326";}


	private Service findPainter;
	private Service highlight;
	private Service clipboard;

	public EntityImpl() throws Exception
	{
		findPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.findatposition");
		highlight = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_v.paste.perform.highlightpainter");
		clipboard = Outside.service(this,"gus06.sys.clipboardwatcher1.chooser2");
	}
	
	
	public void p(Object obj) throws Exception
	{
		perform((JTextComponent) obj);
	}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		if(hasSelection(comp))
		{pasteAtSelection(comp);return;}
		
		Object painter = findPainter.t(comp);
		if(painter!=null)
		{
			highlight.p(new Object[]{comp,painter});
			return;
		}
		
		pasteAtCaret(comp);
	}
	
	
	private boolean hasSelection(JTextComponent comp)
	{
		String s = comp.getSelectedText();
		return s!=null && !s.equals("");
	}
	
	
	private void pasteAtSelection(JTextComponent comp) throws Exception
	{
		String s = (String) clipboard.g();
		if(s==null) return;
		
		comp.replaceSelection(s);
	}
	
	private void pasteAtCaret(JTextComponent comp) throws Exception
	{
		String s = (String) clipboard.g();
		if(s==null) return;
		
		int pos = comp.getCaretPosition();
		comp.getDocument().insertString(pos,s,null);
	}
}
