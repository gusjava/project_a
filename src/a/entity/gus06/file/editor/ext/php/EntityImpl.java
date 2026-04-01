package a.entity.gus06.file.editor.ext.php;

import a.framework.*;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintStream;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.undo.UndoManager;
import java.util.Map;


public class EntityImpl implements Entity, I, P, G, R {

	public String creationDate() {return "20141215";}

	
	private Service custComp;
	private Service buildComp;
	private Service buildScroll;
	private Service buildHighSup;
	private Service buildHighCount;
	private Service buildHighScroll;
	private Service buildUndoManager;
	private Service autoSaver;
	
	private JPanel panel;
	private JTextComponent comp;
	private JScrollPane scroll;
	private JComponent countBar;
	
	private File file;
	private UndoManager undo;
	
	

	public EntityImpl() throws Exception
	{
		custComp = Outside.service(this,"gus06.file.editor.ext.php.custcomp");
		buildComp = Outside.service(this,"gus06.swing.textarea.factory1");
		buildScroll = Outside.service(this,"gus06.swing.textarea.buildscrollpane.linenb");
		buildHighSup = Outside.service(this,"gus06.swing.textcomp.highlight.sys1.support");
		buildHighCount = Outside.service(this,"gus06.swing.textcomp.highlight.sys1.countbar");
		buildHighScroll = Outside.service(this,"gus06.swing.textcomp.highlight.sys1.scrollpaint");
		buildUndoManager = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_zy.undoredo");
		autoSaver = Outside.service(this,"*gus06.file.editor.holder.text.autosaver");
		
		comp = (JTextComponent) buildComp.i();
		undo = (UndoManager) buildUndoManager.t(comp);
		
		S1 highSup = (S1) buildHighSup.t(comp);
		
		scroll = (JScrollPane) buildScroll.t(comp);
		countBar = (JComponent) buildHighCount.t(comp);
		
		custComp.p(comp);
		
		Object highScroll = buildHighScroll.t(scroll);
		highSup.addActionListener((ActionListener) countBar);
		highSup.addActionListener((ActionListener) highScroll);
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(countBar,BorderLayout.SOUTH);
		
		autoSaver.v("comp",comp);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("comp")) return comp;
		if(key.equals("file")) return file;
		if(key.equals("keys")) return new String[]{"comp","file"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		((Map) ((R)comp).r("data")).put("file",file);
		
		boolean loaded = autoSaver.f(file);
		if(loaded) undo.discardAllEdits();
	}
}
