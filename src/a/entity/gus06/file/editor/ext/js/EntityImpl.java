package a.entity.gus06.file.editor.ext.js;

import java.awt.BorderLayout;
import java.io.File;
import a.framework.*;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionListener;
import javax.swing.undo.UndoManager;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JPanel;


public class EntityImpl implements Entity, I, P, G, R, V {

	public String creationDate() {return "20200106";}

	
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
	
	private JPanel panel;
	private JTextComponent comp;
	private JScrollPane scroll;
	private JToolBar bar1;
	private JToolBar bar2;
	
	private JComponent countComp;
	private JComponent focusComp;
	private JComponent caretComp;
	private JComponent autoCopyComp;
	private JComponent hiddenComp;
	private JComponent smartComp;
	
	private File file;
	private UndoManager undo;
	

	public EntityImpl() throws Exception
	{
		custComp = Outside.service(this,"gus06.file.editor.ext.txt.custcomp");
		buildComp = Outside.service(this,"gus06.swing.textarea.factory1");
		buildScroll = Outside.service(this,"gus06.swing.textarea.buildscrollpane.linenb");
		buildHighSup = Outside.service(this,"gus06.swing.textcomp.highlight.sys1.support");
		buildHighScroll = Outside.service(this,"gus06.swing.textcomp.highlight.sys1.scrollpaint");
		buildUndoManager = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_zy.undoredo");
		autoSaver = Outside.service(this,"*gus06.file.editor.holder.text.autosaver");
		toolbarBuilder = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		jumpTo = Outside.service(this,"gus06.swing.textcomp.caret.jump.byrule");
		
		buildHighCount = Outside.service(this,"gus06.swing.textcomp.highlight.sys1.countbar");
		buildFocusLabel = Outside.service(this,"gus06.swing.textcomp.textfocus.label");
		buildCaretLabel = Outside.service(this,"gus06.swing.textcomp.buildlabel.caretposition");
		buildAutoCopyLabel = Outside.service(this,"gus06.swing.textcomp.autocopy.label");
		buildHiddenLabel = Outside.service(this,"gus06.swing.textcomp.buildlabel.hiddenchars");
		buildSmartLabel = Outside.service(this,"gus06.sys.quickreplace.holder.find.label");
		
		comp = (JTextComponent) buildComp.i();
		undo = (UndoManager) buildUndoManager.t(comp);
		
		((Map) ((R)comp).r("data")).put("editor",this);
		S1 highSup = (S1) buildHighSup.t(comp);
		scroll = (JScrollPane) buildScroll.t(comp);
		
		bar1 = (JToolBar) toolbarBuilder.i();
		bar2 = (JToolBar) toolbarBuilder.i();
		
		countComp = (JComponent) buildHighCount.t(comp);
		focusComp = (JComponent) buildFocusLabel.t(comp);
		caretComp = (JComponent) buildCaretLabel.t(comp);
		autoCopyComp = (JComponent) buildAutoCopyLabel.t(comp);
		hiddenComp = (JComponent) buildHiddenLabel.t(comp);
		smartComp = (JComponent) buildSmartLabel.t(comp);
		
		custComp.p(comp);
		
		if(comp instanceof JTextArea)
		((JTextArea) comp).setTabSize(4);
		
		Object highScroll = buildHighScroll.t(scroll);
		highSup.addActionListener((ActionListener) countComp);
		highSup.addActionListener((ActionListener) highScroll);
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(bottomPanel(),BorderLayout.SOUTH);
		
		autoSaver.v("comp",comp);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	private JComponent bottomPanel() throws Exception
	{
		bar2.add(caretComp);
		bar2.addSeparator();
		
		bar2.add(countComp);
		bar2.addSeparator();
		
		bar2.add(focusComp);
		bar2.addSeparator();
		
		bar2.add(smartComp);
		bar2.addSeparator();
		
		bar2.add(autoCopyComp);
		bar2.addSeparator();
		
		bar2.add(hiddenComp);
		bar2.addSeparator();
		
		return wc(bar1,wc(bar2,null));
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("file")) return file;
		if(key.equals("comp")) return comp;
		if(key.equals("scroll")) return scroll;
		if(key.equals("bar1")) return bar1;
		if(key.equals("bar2")) return bar2;
		
		if(key.equals("keys")) 
			return new String[]{"file","comp","scroll","bar1","bar2"};
		
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
