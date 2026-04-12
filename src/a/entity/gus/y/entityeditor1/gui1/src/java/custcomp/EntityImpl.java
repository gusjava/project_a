package a.entity.gus.y.entityeditor1.gui1.src.java.custcomp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextArea;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240113";}

	public static final Font FONT = new Font(Font.MONOSPACED,Font.PLAIN,14);
	public static final Insets MARGIN = new Insets(0,5,0,5);
	public static final Color SELECTION = Color.BLACK;
	public static final Color SELECTED = Color.WHITE;
	
	private Service undoRedo;
	private Service paintCaretLine;
	private Service slashComment;
	private Service autoEdit1;
	private Service custComp1;
	private Service addJavaImport1;
	private Service autocomplete1;

	public EntityImpl() throws Exception {
		undoRedo = Outside.service(this,"gus.y.swing1.textcomp.cust.action.ctrl_zy.undoredo");
		paintCaretLine = Outside.service(this,"gus.y.swing1.textarea1.p.paint.caretline");
		slashComment = Outside.service(this,"gus.y.swing1.textcomp.cust.action.ctrl_shift_slash.comment");
		autoEdit1 = Outside.service(this,"gus.y.swing1.textcomp.cust.autoedit1");
		custComp1 = Outside.service(this,"gus.y.swingactions1.custcomp1");
		addJavaImport1 = Outside.service(this,"gus.y.addjavaimport1.action.f2");
		autocomplete1 = Outside.service(this,"gus.y.autocomplete1.action.f1");
	}
	
	public void p(Object obj) throws Exception {
		JTextArea comp = (JTextArea) obj;
		
		comp.setMargin(MARGIN);
		comp.setFont(FONT);
		comp.setSelectionColor(SELECTION);
		comp.setSelectedTextColor(SELECTED);
		
		paintCaretLine.p(comp);
		undoRedo.p(comp);
		slashComment.p(comp);
		autoEdit1.p(comp);
		custComp1.p(comp);
		addJavaImport1.p(comp);
		autocomplete1.p(comp);
	}
}
