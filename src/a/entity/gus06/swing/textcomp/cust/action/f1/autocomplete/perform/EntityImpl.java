package a.entity.gus06.swing.textcomp.cust.action.f1.autocomplete.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140818";}

	public static final String KEY_HANDLER = "F1_handler";


	private Service input;
	private Service subMap;
	private Service applyToComp;
	
	private String rule0;
	
	
	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog");
		subMap = Outside.service(this,"gus06.app.prop.submap.autocomplete");
		applyToComp = Outside.service(this,"gus06.appli.gusexplorer.gui.editor.fillbar.applytocomp.p");
	}
	
	
	public void p(Object obj) throws Exception
	{
		perform((JTextComponent) obj);
	}
	
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		boolean applied = applyToComp.f(new Object[]{comp,null,KEY_HANDLER});
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
	{
		Map map = (Map) subMap.g();
		if(!map.containsKey(name))
			throw new Exception("Unknown autocomplete action: "+name);
		String rule = (String) map.get(name);
		return Outside.service(this,rule);
	}
}