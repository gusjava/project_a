package a.entity.gus.y.addjavaimport1.handle;

import a.framework.*;
import java.util.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, V, P {
	public String creationDate() {return "20240712";}
	
	private Service perform;
	private Service findCaretWord;
	private Service replaceCaretWord;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus.y.addjavaimport1.perform");
		findCaretWord = Outside.service(this,"gus.y.addjavaimport1.caret.word.find");
		replaceCaretWord = Outside.service(this,"gus.y.addjavaimport1.caret.word.replace");
	}
	
	public void p(Object obj) throws Exception
	{
		String query = (String) findCaretWord.t(obj);
		if(query==null) return;
		
		String className = (String) perform.t(new Object[] {obj, query});
		if(className!=null) replaceCaretWord.v(className, obj);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		perform.t(new Object[] {obj, key});
	}
}