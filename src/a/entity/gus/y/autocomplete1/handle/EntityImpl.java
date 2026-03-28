package a.entity.gus.y.autocomplete1.handle;

import a.framework.*;
import java.util.Set;
import java.util.Iterator;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, V, P {

	public String creationDate() {return "20240713";}
	
	private Service perform;
	private Service findCaretWord;
	private Service replaceCaretWord;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus.y.autocomplete1.perform");
		findCaretWord = Outside.service(this,"gus.y.addjavaimport1.caret.word.find");
		replaceCaretWord = Outside.service(this,"gus.y.autocomplete1.caret.word.replace");
	}
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String query = (String) findCaretWord.t(comp);
		if(query==null) return;
		
		String output = (String) perform.t(new Object[] {comp, query});
		if(output!=null) replaceCaretWord.v(output, comp);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		perform.t(new Object[] {obj, key});
	}
}