package a.entity.gus06.swing.textcomp.cust.action.ctrl_g.regex.perform.findexp;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160428";}


	private Service inferRegexp;
	private Service regexpFromRule;
	private Service ask;
	
	public EntityImpl() throws Exception
	{
		inferRegexp = Outside.service(this,"gus06.string.transform.regexp.infer");
		regexpFromRule = Outside.service(this,"gus06.string.transform.regexp.fromrule");
		ask = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_g.regex.perform.findexp.ask");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String s = comp.getSelectedText();
		
		if(empty(s))
		{
			s = askForRule();
			if(s==null) return null;
			return (String) regexpFromRule.t(s);
		}
		return inferRegexp.t(s);
	}
	
	
	private boolean empty(String s)
	{return s==null || s.equals("");}
	
	
	private String askForRule() throws Exception
	{return (String) ask.g();}
}
