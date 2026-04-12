package a.entity.gus06.y.entityeditor1.gui1.src.java.f1.autocomplete.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251124";}

	public static final String KEY_HANDLER = "F1_handler";


	private Service input;
	private Service find;
	private Service apply;
	
	private String rule0;
	
	
	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog");
		find = Outside.service(this,"gus06.y.entityeditor1.gui1.src.java.f1.autocomplete.find");
		apply = Outside.service(this,"gus06.y.entityeditor1.gui1.src.java.f1.autocomplete.apply");
	}
	
	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	private void perform(JTextComponent comp) throws Exception
	{
		boolean applied = apply.f(new Object[]{comp,null,KEY_HANDLER});
		if(applied) return;
		
		String rule = (String) input.t("Type rule:");
		
		if(rule!=null && rule.equals("")) rule = rule0;
		if(rule==null || rule.equals("")) return;
		
		rule0 = rule;
		handleRule(rule,comp);
	}
	
	private void handleRule(String rule, JTextComponent comp) throws Exception
	{
		String[] n = rule.split(" ",2);
		Object obj = find(n[0]);
		
		if(n.length==1) ((P)obj).p(comp);
		else ((V)obj).v(n[1],comp);
	}
	
	private Object find(String name) throws Exception
	{return find.t(name);}
}