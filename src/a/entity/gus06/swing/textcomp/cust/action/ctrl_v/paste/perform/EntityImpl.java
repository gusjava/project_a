package a.entity.gus06.swing.textcomp.cust.action.ctrl_v.paste.perform;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160427";}

	public static final String KEY_PASTE_HANDLER = "ctrl_v_handler";

	private Service clipboard;

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.clipboard.access.string.or.filepaths");
	}
	
	public void p(Object obj) throws Exception
	{
		perform((JTextComponent) obj);
	}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		G g = findG(comp);
		String s = (String) (g!=null ? g.g() : clipboard.g());
		if(s==null) return;
		
		if(hasSelection(comp)) pasteAtSelection(comp, s);
		else pasteAtCaret(comp, s);
	}
	
	
	private boolean hasSelection(JTextComponent comp)
	{
		String s = comp.getSelectedText();
		return s!=null && !s.equals("");
	}
	
	
	private void pasteAtSelection(JTextComponent comp, String s) throws Exception
	{
		comp.replaceSelection(s);
	}
	
	private void pasteAtCaret(JTextComponent comp, String s) throws Exception
	{
		int pos = comp.getCaretPosition();
		comp.getDocument().insertString(pos,s,null);
	}
	
	private G findG(JTextComponent comp) throws Exception
	{
		if(!(comp instanceof R)) return null;
		Map data = (Map) ((R) comp).r("data");
		if(data==null) return null;
		return (G) get(data, KEY_PASTE_HANDLER);
	}
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}