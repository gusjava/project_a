package a.entity.gus06.file.editor.ext.xhtml.tab1;

import a.framework.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.Color;

public class EntityImpl implements Entity, I, P, G, R, V {

	public String creationDate() {return "20200103";}
	
	public static final String KEYSTOKE_INDENT = "ctrl i";
	public static final String KEYSTOKE_MINIMIZE = "ctrl shift i";
	public static final Color BACKGROUND = new Color(219,235,255);

	
	private Service txtEditor;
	private Service buildActionIndent;
	private Service buildActionMinimize;
	private Service buildActionRemoveTag;
	private Service putAction;
	

	public EntityImpl() throws Exception
	{
		txtEditor = Outside.service(this,"*gus06.file.editor.ext.txt");
		buildActionIndent = Outside.service(this,"gus06.file.editor.ext.xhtml.action.indent");
		buildActionMinimize = Outside.service(this,"gus06.file.editor.ext.xhtml.action.minimize");
		buildActionRemoveTag = Outside.service(this,"gus06.file.editor.ext.xhtml.action.remove1");
		putAction = Outside.service(this,"gus06.swing.textcomp.cust.putaction");
		
		JTextComponent comp = (JTextComponent) txtEditor.r("comp");
		JToolBar bar = (JToolBar) txtEditor.r("bar1");
		
		Action actionIndent = (Action) buildActionIndent.t(comp);
		Action actionMinimize = (Action) buildActionMinimize.t(comp);
		Action actionRemoveTag = (Action) buildActionRemoveTag.t(comp);
		
		putAction.p(new Object[]{comp,actionIndent,KEYSTOKE_INDENT});
		putAction.p(new Object[]{comp,actionMinimize,KEYSTOKE_MINIMIZE});
		
		bar.add(actionIndent);
		bar.add(actionMinimize);
		bar.add(actionRemoveTag);
		bar.addSeparator();
		
		comp.setBackground(BACKGROUND);
	}
	
	
	
	public Object i() throws Exception
	{return txtEditor.i();}
	
	public Object g() throws Exception
	{return txtEditor.g();}
	
	public Object r(String key) throws Exception
	{return txtEditor.r(key);}
	
	public void v(String key, Object obj) throws Exception
	{txtEditor.v(key, obj);}
	
	public void p(Object obj) throws Exception
	{txtEditor.p(obj);}
}