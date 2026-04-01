package a.entity.gus06.swing.textcomp.cust.action.f2.gusscript.autocomplete.op.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160504";}


	private Service findCaretWord;
	private Service replaceCaretWord;
	private Service chooseOp;
	
	public EntityImpl() throws Exception
	{
		findCaretWord = Outside.service(this,"gus06.swing.textcomp.caret.word.find");
		replaceCaretWord = Outside.service(this,"gus06.swing.textcomp.caret.word.replace");
		chooseOp = Outside.service(this,"gus06.swing.textcomp.cust.action.f2.gusscript.autocomplete.op.perform.chooser");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String word = (String) findCaretWord.t(comp);
		
		String found = chooseOp(word.toLowerCase());
		if(found!=null) replaceCaretWord.v(found,comp);
	}
	
	private String chooseOp(String query) throws Exception
	{return (String) chooseOp.t(query);}
}
