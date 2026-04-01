package a.entity.gus06.swing.textcomp.cust.action.ctrl_shift_b.copy.perform;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20240412";}

	public static final String KEY_HANDLER = "ctrl_shift_b_handler";
	

	private Service perform2;
	private Service applyToComp;

	public EntityImpl() throws Exception
	{
		perform2 = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_b.copy.perform2");
		applyToComp = Outside.service(this,"gus06.appli.gusexplorer.gui.editor.fillbar.applytocomp.p");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JTextArea)
		{
			JTextArea comp = (JTextArea) obj;
			String text = findText(comp);
			
			boolean applied = applyToComp.f(new Object[]{comp,text,KEY_HANDLER});
			if(applied) return;
			
			perform2.p(new Object[]{comp,text});
		}
	}
	
	
	
	private String findText(JTextArea comp) throws Exception
	{
		PlainDocument document = (PlainDocument) comp.getDocument();
		if(hasSelection(comp)) return comp.getSelectedText();
		
		int p = comp.getCaretPosition();
		Element element = document.getParagraphElement(p);
		int start = element.getStartOffset();
		int end = element.getEndOffset();
		
		return document.getText(start,end-start);
	}
	
	private boolean hasSelection(JTextArea comp)
	{
		String s = comp.getSelectedText();
		return s!=null && !s.equals("");
	}
}