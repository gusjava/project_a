package a.entity.gus06.swing.textcomp.caret.jump.byrule;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20220519";}


	private Service line;
	private Service linepos;
	private Service pos;
	private Service select;

	public EntityImpl() throws Exception
	{
		line = Outside.service(this,"gus06.swing.textcomp.caret.jump.byrule.line");
		linepos = Outside.service(this,"gus06.swing.textcomp.caret.jump.byrule.linepos");
		pos = Outside.service(this,"gus06.swing.textcomp.caret.jump.byrule.pos");
		select = Outside.service(this,"gus06.swing.textcomp.caret.jump.byrule.select");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];;
		String rule = ""+o[1];
		
		String selectionRule = null;
		if(rule.contains("/"))
		{
			String[] n = rule.split("/",2);
			rule = n[0];
			selectionRule = n[1];
		}
		
		if(rule.startsWith(":"))
		{
			boolean done = goToPos(comp, rule.substring(1));
			if(!done) return false;
		}
		else if(rule.contains(":"))
		{
			String[] n = rule.split(":",2);
			boolean done = goToLinePos(comp, n[0], n[1]);
			if(!done) return false;
		}
		else
		{
			boolean done = goToLine(comp, rule);
			if(!done) return false;
		}
		
		if(selectionRule!=null)
		{
			select(comp, selectionRule);
		}
		return true;
	}
	
	
	
	private boolean goToPos(JTextComponent comp, String rule) throws Exception
	{return pos.f(new Object[]{comp, rule});}
	
	private boolean goToLine(JTextComponent comp, String rule) throws Exception
	{return line.f(new Object[]{comp, rule});}
	
	private boolean goToLinePos(JTextComponent comp, String rule1, String rule2) throws Exception
	{return linepos.f(new Object[]{comp, rule1, rule2});}
	
	
	
	private void select(JTextComponent comp, String rule) throws Exception
	{select.p(new Object[]{comp, rule});}
}