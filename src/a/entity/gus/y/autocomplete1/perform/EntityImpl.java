package a.entity.gus.y.autocomplete1.perform;

import a.framework.*;
import java.util.Set;
import java.util.Iterator;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240713";}
	
	private Service input;
	private Service find;
	
	private String rule0;
	
	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog");
		find = Outside.service(this,"gus.y.autocomplete1.find");
	}
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String rule = (String) input.t("Type rule:");
		
		if(rule!=null && rule.equals("")) rule = rule0;
		if(rule==null || rule.equals("")) return;
		
		rule0 = rule;
		handleRule(rule,comp);
	}
	
	private void handleRule(String rule, JTextComponent comp) throws Exception
	{
		String[] n = rule.split(" ",2);
		Object obj = find.t(n[0]);
		
		if(n.length==1) ((P)obj).p(comp);
		else ((V)obj).v(n[1],comp);
	}
}
