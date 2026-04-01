package a.entity.gus06.file.editor.ext.css;

import java.awt.BorderLayout;
import java.io.File;
import a.framework.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionListener;
import javax.swing.undo.UndoManager;
import java.util.Map;
import java.awt.Color;


public class EntityImpl implements Entity, I, P, G, R, V {

	public String creationDate() {return "20170226";}
	
	public static final Color BACKGROUND = new Color(245,245,245);

	
	private Service txtEditor;
	private Service buildShowColor;
	
	public EntityImpl() throws Exception
	{
		txtEditor = Outside.service(this,"*gus06.file.editor.ext.txt");
		buildShowColor = Outside.service(this,"gus06.file.editor.ext.css.toogle.showcolor");
		
		JTextComponent comp = (JTextComponent) txtEditor.r("comp");
		JToolBar bar = (JToolBar) txtEditor.r("bar1");
		
		JComponent showColorToggle = (JComponent) buildShowColor.t(comp);
		bar.add(showColorToggle);
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