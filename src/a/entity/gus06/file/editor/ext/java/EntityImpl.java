package a.entity.gus06.file.editor.ext.java;

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


public class EntityImpl implements Entity, I, P, G, R, V {

	public String creationDate() {return "20140723";}

	
	private Service custComp;
	private Service buildComp;
	private Service buildScroll;
	private Service buildHighSup;
	private Service buildHighScroll;
	private Service autoSaver;
	private Service toolbarBuilder;
	private Service buildUndoManager;
	private Service jumpTo;
	
	private Service buildHighCount;
	private Service buildFocusLabel;
	private Service buildCaretLabel;
	private Service buildAutoCopyLabel;
	private Service buildHiddenLabel;
	private Service buildSmartLabel;
	private Service buildHighErr;
	private Service buildCompiler;
	private Service autoImport;
	
	
	private JPanel panel;
	private JTextComponent comp;
	private JScrollPane scroll;
	private JToolBar bar;
	
	private JComponent countComp;
	private JComponent focusComp;
	private JComponent caretComp;
	private JComponent autoCopyComp;
	private JComponent hiddenComp;
	private JComponent smartComp;
	
	private File file;
	private UndoManager undo;
	
	private P highErr;
	private P compiler;


	public EntityImpl() throws Exception
	{
		custComp = Outside.service(this,"gus06.file.editor.ext.txt.custcomp");
		buildComp = Outside.service(this,"gus06.swing.textarea.factory1");
		buildScroll = Outside.service(this,"gus06.swing.textarea.buildscrollpane.linenb");
		buildHighSup = Outside.service(this,"gus06.swing.textcomp.highlight.sys1.support");
		buildHighCount = Outside.service(this,"gus06.swing.textcomp.highlight.sys1.countbar");
		buildHighScroll = Outside.service(this,"gus06.swing.textcomp.highlight.sys1.scrollpaint");
		autoSaver = Outside.service(this,"*gus06.file.editor.holder.text.autosaver");
		buildUndoManager = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_zy.undoredo");
		toolbarBuilder = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		jumpTo = Outside.service(this,"gus06.swing.textcomp.caret.jump.byrule");
		
		buildFocusLabel = Outside.service(this,"gus06.swing.textcomp.textfocus.label");
		buildCaretLabel = Outside.service(this,"gus06.swing.textcomp.buildlabel.caretposition");
		buildAutoCopyLabel = Outside.service(this,"gus06.swing.textcomp.autocopy.label");
		buildHiddenLabel = Outside.service(this,"gus06.swing.textcomp.buildlabel.hiddenchars");
		buildSmartLabel = Outside.service(this,"gus06.sys.quickreplace.holder.find.label");
		buildHighErr = Outside.service(this,"gus06.file.editor.ext.java.high.err");
		buildCompiler = Outside.service(this,"gus06.file.editor.ext.java.compiler");
		autoImport = Outside.service(this,"gus06.swing.textcomp.cust.action.f2.java.autoimport");
		
		comp = (JTextComponent) buildComp.i();
		undo = (UndoManager) buildUndoManager.t(comp);
			
		S1 highSup = (S1) buildHighSup.t(comp);
		scroll = (JScrollPane) buildScroll.t(comp);
		
		countComp = (JComponent) buildHighCount.t(comp);
		focusComp = (JComponent) buildFocusLabel.t(comp);
		caretComp = (JComponent) buildCaretLabel.t(comp);
		autoCopyComp = (JComponent) buildAutoCopyLabel.t(comp);
		hiddenComp = (JComponent) buildHiddenLabel.t(comp);
		smartComp = (JComponent) buildSmartLabel.t(comp);
		
		Object highScroll = buildHighScroll.t(scroll);
		highSup.addActionListener((ActionListener) countComp);
		highSup.addActionListener((ActionListener) highScroll);
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(bottomBar(),BorderLayout.SOUTH);
		
		highErr = (P) buildHighErr.t(comp);
		compiler = (P) buildCompiler.t(comp);
		
		custComp.p(comp);
		autoImport.p(comp);
		autoSaver.v("comp",comp);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	private JComponent bottomBar() throws Exception
	{
		bar = (JToolBar) toolbarBuilder.i();
		
		bar.add(caretComp);
		bar.addSeparator();
		
		bar.add(countComp);
		bar.addSeparator();
		
		bar.add(focusComp);
		bar.addSeparator();
		
		bar.add(smartComp);
		bar.addSeparator();
		
		bar.add(autoCopyComp);
		bar.addSeparator();
		
		bar.add(hiddenComp);
		bar.addSeparator();
		
		return wc(bar,null);
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("file")) return file;
		if(key.equals("comp")) return comp;
		if(key.equals("bar")) return bar;
		if(key.equals("scroll")) return scroll;
		if(key.equals("charset")) return autoSaver.r("charset");
		if(key.equals("keys")) return new String[]{"file","comp","bar","scroll","charset"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("charset")) {autoSaver.v("charset",obj);return;}
		if(key.equals("jumpTo")) {jumpTo(obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		((Map) ((R)comp).r("data")).put("file",file);
		
		highErr.p(file);
		compiler.p(file);
		
		boolean loaded = autoSaver.f(file);
		if(loaded) undo.discardAllEdits();
	}
	
	
	
	private JPanel wc(JComponent w, JComponent c)
	{
		if(c==null) c = new JPanel();
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(w,BorderLayout.WEST);
		p.add(c,BorderLayout.CENTER);
		return p;
	}
	
	
	private void jumpTo(Object rule) throws Exception
	{
		jumpTo.f(new Object[]{comp,rule});
	}
}