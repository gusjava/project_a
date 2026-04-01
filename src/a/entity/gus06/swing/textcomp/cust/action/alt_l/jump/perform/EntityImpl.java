package a.entity.gus06.swing.textcomp.cust.action.alt_l.jump.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220519";}


	private Service jump;
	private Service clipboard;
	
	public EntityImpl() throws Exception
	{
		jump = Outside.service(this,"gus06.swing.textcomp.caret.jump.byrule");
		clipboard = Outside.service(this,"gus06.clipboard.access.string");
	}
	
	
	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		String rule = findRule();
		if(rule==null) return;
		
		if(!isValidPositionRule(rule) && !isValidKeyword(rule))
		{
			String msg = "Invalid jump rule: "+rule;
			JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		jump.p(new Object[]{comp,rule});
	}
	
	
	
	private String findRule() throws Exception
	{
		return (String) clipboard.g();
	}
	
	
	
	private boolean isValidPositionRule(String rule)
	{
		if(rule.matches("[0-9]+(-[0-9]+)?")) return true;
		if(rule.matches(":[0-9]+(-[0-9]+)?")) return true;
		if(rule.matches("[0-9]+:[0-9]+(-[0-9]+)?")) return true;
		return false;
	}
	
	private boolean isValidKeyword(String rule)
	{
		return rule.equals("first") || rule.equals("last");
	}
}
