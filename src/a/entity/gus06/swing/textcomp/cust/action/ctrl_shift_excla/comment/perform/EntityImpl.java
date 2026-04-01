package a.entity.gus06.swing.textcomp.cust.action.ctrl_shift_excla.comment.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170223";}

	public static final String COMMENT_START = "<!--";
	public static final String COMMENT_END = "-->";



	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JTextArea)
		perform((JTextComponent) obj);
	}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		String selected = comp.getSelectedText();
		if(selected==null) return;
		
		int start = comp.getSelectionStart();
		
		boolean isComment = selected.startsWith(COMMENT_START) && selected.endsWith(COMMENT_END);
		String text = isComment ? decomment(selected) : comment(selected);
		
		comp.replaceSelection(text);
		comp.select(start,start+text.length());
	}
	
	
	private String decomment(String s)
	{return s.substring(COMMENT_START.length(),s.length()-COMMENT_END.length());}
	
	private String comment(String s)
	{return COMMENT_START+s+COMMENT_END;}
}
