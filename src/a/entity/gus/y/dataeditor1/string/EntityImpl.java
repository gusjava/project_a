package a.entity.gus.y.dataeditor1.string;

import java.awt.BorderLayout;
import java.io.File;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import javax.swing.undo.UndoManager;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EntityImpl extends S1 implements Entity, P, I, R, G, DocumentListener {
	public String creationDate() {return "20240712";}
	
	private Service buildArea;
	private Service buildScroll;
	private Service buildHighSup;
	private Service buildHighScroll;
	private Service buildHighCount;
	private Service buildCaretPos;
	private Service buildSmartLabel;
	private Service barFactory;
	private Service custArea;
	
	private JPanel panel;
	private JTextArea area;
	private JScrollPane scroll;
	private JComponent countComp;
	private JComponent caretPosComp;
	private JComponent smartComp;
	private JToolBar bar2;


	public EntityImpl() throws Exception {
		buildArea = Outside.service(this,"gus.y.swing1.textarea1.factory");
		buildScroll = Outside.service(this,"gus.y.swing1.textarea.buildscrollpane.linenb");
		buildHighSup = Outside.service(this,"gus.y.swinghigh1.support");
		buildHighScroll = Outside.service(this,"gus.y.swinghigh1.scrollpaint");
		buildHighCount = Outside.service(this,"gus.y.swinghigh1.countbar");
		buildCaretPos = Outside.service(this,"gus.x.swing.textcomp.build.caretpositionlabel");
		buildSmartLabel = Outside.service(this,"gus.y.quickreplace1.holder.find.label");
		barFactory = Outside.service(this,"gus.x.swing.toolbar.factory1");
		custArea = Outside.service(this,"gus.y.entityeditor1.gui1.src.java.custcomp");
		
		area = (JTextArea) buildArea.i();
		scroll = (JScrollPane) buildScroll.t(area);
		custArea.p(area);
		
		S highSup = (S) buildHighSup.t(area);
		
		countComp = (JComponent) buildHighCount.t(area);
		caretPosComp = (JComponent) buildCaretPos.t(area);
		smartComp = (JComponent) buildSmartLabel.t(area);
		
		Object highScroll = buildHighScroll.t(scroll);
		highSup.addActionListener((ActionListener) countComp);
		highSup.addActionListener((ActionListener) highScroll);
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(bottomPanel(),BorderLayout.SOUTH);
		
		area.getDocument().addDocumentListener(this);
	}
	
	public void p(Object obj) throws Exception {
		String text = obj==null ? "" : (String) obj;
		
		area.getDocument().removeDocumentListener(this);
		area.setText(text);
		area.getDocument().addDocumentListener(this);

		area.setCaretPosition(0);
	}
	
	public Object r(String key) throws Exception {
		if (key.equals("area")) return area;
		if (key.equals("keys"))
			return new String[] { "area" };
		throw new Exception("Unknown key: " + key);
	}

	public Object i() throws Exception {
		return panel;
	}
	
	public Object g() throws Exception {
		return area.getText();
	}

	public void insertUpdate(DocumentEvent e) {
		modified();
	}

	public void removeUpdate(DocumentEvent e) {
		modified();
	}

	public void changedUpdate(DocumentEvent e) {
	}

	private void modified() {
		send(this,"modified()");
	}

	private JComponent bottomPanel() throws Exception {
		bar2 = (JToolBar) barFactory.i();
		bar2.add(countComp);
		bar2.addSeparator();
		bar2.add(caretPosComp);
		bar2.addSeparator();
		bar2.add(smartComp);
		bar2.addSeparator();
		return bar2;
	}

	/*
	 * CARET POSITION
	 */

	private void setCaretPosition(int caretPosition) {
		int len = area.getText().length();
		if (caretPosition > len)
			caretPosition = len;
		area.setCaretPosition(caretPosition);
	}
}
