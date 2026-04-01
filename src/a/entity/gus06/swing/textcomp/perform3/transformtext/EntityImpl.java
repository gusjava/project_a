package a.entity.gus06.swing.textcomp.perform3.transformtext;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141105";}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		T t = (T) o[1];
		
		if(hasSelection(comp))
			transformSelection(comp,t);
		else transformAll(comp,t);
		
		if(comp instanceof R)
		((Map) ((R) comp).r("data")).put("lastTransform",t);
	}
	
	
	
	private void transformAll(JTextComponent comp, T t) throws Exception
	{
		String s0 = comp.getText();
		String s1 = (String) t.t(s0);
		if(s1==null) return;
		
		int p = comp.getCaretPosition();
		int l = s1.length();
		int p1 = Math.min(l,p);
		
		comp.setText(s1);
		comp.requestFocus();
		comp.setCaretPosition(p1);
	}
	
	
	private void transformSelection(JTextComponent comp, T t) throws Exception
	{
		String s0 = comp.getSelectedText();
		String s1 = (String) t.t(s0);
		
		if(s1==null) return;
		
		int start = comp.getSelectionStart();
		int length = s1.length();
		
		comp.replaceSelection(s1);
		comp.requestFocus();
		comp.select(start,start+length);
	}
	
	
	private boolean hasSelection(JTextComponent comp)
	{
		String s = comp.getSelectedText();
		return s!=null && !s.equals("");
	}
}