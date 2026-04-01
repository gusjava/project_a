package a.entity.gus06.appli.gusexplorer.gui.editor.fillbar.applytocomp.p;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;
import java.util.Map;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20220616";}

	

	public EntityImpl() throws Exception
	{
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		JTextArea comp = (JTextArea) o[0];
		String text = (String) o[1];
		String objKey = (String) o[2];
		
		if(text==null) text = findText(comp);
			
		P p = findP(comp, objKey);
		if(p!=null) {p.p(text);return true;}
		
		return false;
	}
	
	
	
	
	private P findP(JTextComponent comp, String objKey) throws Exception
	{
		if(!(comp instanceof R)) return null;
		Map data = (Map) ((R) comp).r("data");
		if(data==null) return null;
		return (P) get(data, objKey);
	}
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	private String findText(JTextComponent comp)
	{
		String s = comp.getSelectedText();
		if(s!=null && !s.equals("")) return s;
		return comp.getText();
	}
}