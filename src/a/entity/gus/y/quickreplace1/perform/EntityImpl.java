package a.entity.gus.y.quickreplace1.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.util.List;

public class EntityImpl implements Entity, F {
	public String creationDate() {return "20240714";}

	public static final String PAINTER_KEY = "replace";

	private Service getPainter;
	private Service highClear;
	
	private DefaultHighlighter.DefaultHighlightPainter painter;
	
	public EntityImpl() throws Exception
	{
		getPainter = Outside.service(this,"gus.y.swingactions1.painter.manager");
		highClear = Outside.service(this,"gus.y.quickreplace1.highlight.clear");
		
		painter = (DefaultHighlighter.DefaultHighlightPainter) getPainter.r(PAINTER_KEY);
	}
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		R info = (R) o[1];
		T trans = (T) o[2];
		
		String selected = comp.getSelectedText();
		boolean partial = ((F) info).f("partial");
		
		if(selected==null)
		{
			if(partial) return false;
			return handleFull(comp,info,trans);
		}
		return handleSelected(comp,info,trans);
	}
	
	
	private boolean handleFull(JTextComponent comp, R info, T trans) throws Exception
	{
		String text = (String) info.r("text");
		int caretPos = (Integer) info.r("caretPos");
		
		List ranges = (List) trans.t(text);
		comp.setText(text);
		
		((V) comp).v("undoable","true");
		
		Highlighter high = comp.getHighlighter();
		highClear.p(new Object[]{comp,painter});
		
		for(int i=ranges.size()-1;i>=0;i--)
		{
			Object[] range = (Object[]) ranges.get(i);
			
			Integer start_ = (Integer) range[0];
			Integer end_ = (Integer) range[1];
			String replacement = (String) range[2];
			
			int start = start_.intValue();
			int end = end_.intValue();
			int end1 = start + replacement.length();
			
			comp.getDocument().insertString(end,replacement,null);
			comp.getDocument().remove(start,end-start);
			
			high.addHighlight(start,end1,painter);
		}
		
		((V) comp).v("undoable","false");
		
		int pos = Math.min(comp.getText().length(), caretPos);
		comp.setCaretPosition(pos);
		comp.requestFocus();
				
		return true;
	}
	
	private boolean handleSelected(JTextComponent comp, R info, T trans) throws Exception
	{
		int selectionStart = comp.getSelectionStart();
		int selectionEnd = comp.getSelectionEnd();
		
		String text = comp.getText();
		List ranges = (List) trans.t(text);
		
		((V) comp).v("undoable","true");
		
		Highlighter high = comp.getHighlighter();
		highClear.p(new Object[]{comp,painter});
		
		for(int i=ranges.size()-1;i>=0;i--)
		{
			Object[] range = (Object[]) ranges.get(i);
			
			Integer start_ = (Integer) range[0];
			Integer end_ = (Integer) range[1];
			String replacement = (String) range[2];
			
			int start = start_.intValue();
			int end = end_.intValue();
			
			if(end<=selectionEnd && start>=selectionStart)
			{
				int end1 = start + replacement.length();
				
				comp.getDocument().insertString(end,replacement,null);
				comp.getDocument().remove(start,end-start);
				
				high.addHighlight(start,end1,painter);
			}
		}
		
		((V) comp).v("undoable","false");
		
		int pos = Math.min(comp.getText().length(), selectionEnd);
		comp.setCaretPosition(pos);
		comp.requestFocus();
		
		return false;
	}
}
