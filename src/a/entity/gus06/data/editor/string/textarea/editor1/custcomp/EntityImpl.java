package a.entity.gus06.data.editor.string.textarea.editor1.custcomp;

import a.framework.*;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.text.JTextComponent;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140831";}

	public static final Font FONT = new Font("Courier",Font.PLAIN,14);
	public static final Insets MARGIN = new Insets(0,5,0,5);
	public static final Color SELECTION = Color.BLACK;
	public static final Color SELECTED = Color.WHITE;
	
	
	
	private List<P> list;

	public EntityImpl() throws Exception
	{
		list = new ArrayList<>();
		
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_b.execute"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_c.copy"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_d.removeline"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_e.doubleline"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_f.search"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_g.regex"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_h.tool"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_j.high1"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_k.repeat"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_l.jump"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_p.wrapline"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_q.keepselection"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_r.keepline"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_t.truncate"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_v.paste"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_w.quickreplace"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_x.cut"));
		
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_down.select.after"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_up.select.before"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_right.select.forward"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_left.select.back"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_plus.increasefontsize"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_minus.decreasefontsize"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_f1.focusshift"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_f2.autocopyshift"));
		
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_b.copy"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_c.copy"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_f.search"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_g.regex.rule"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_h.tool"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_t.truncate.inv"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_v.paste"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_w.smartreplace"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_slash.comment"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_excla.comment"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_sharp.comment"));
		
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_alt_b.copycontent"));
		
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.f1.autocomplete"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.escap.focusback"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.del"));
		
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.alt_c.showoff"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.alt_f.search1"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.alt_q.keepselection"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.alt_h.tool"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.alt_l.jump"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.alt_t.truncate"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.alt_down.gotodown"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.alt_left.gotoleft"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.alt_right.gotoright"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.alt_up.gotoup"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.alt_del"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.alt_backspace"));
		
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.alt_shift_h.tool"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.action.alt_shift_t.truncate.inv"));
		
		list.add(Outside.service(this,"gus06.swing.textcomp.paint.caretline"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.selection.clipboardcolored"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.autoedit.tab1"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.autoedit.enter1"));
		list.add(Outside.service(this,"gus06.swing.textcomp.cust.autoedit.closexml"));
	}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		comp.setMargin(MARGIN);
		comp.setFont(FONT);
		comp.setSelectionColor(SELECTION);
		comp.setSelectedTextColor(SELECTED);
		
		for(P p:list) p.p(comp);
	}
}