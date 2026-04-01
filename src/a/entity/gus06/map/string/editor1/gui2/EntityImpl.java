package a.entity.gus06.map.string.editor1.gui2;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.text.JTextComponent;
import javax.swing.JComponent;

public class EntityImpl extends S1 implements Entity, I, P, V, R {

	public String creationDate() {return "20200206";}


	private Service textEditor;
	private Service undoRedo;
	private Service onEscape;
	private Service onCtrlS;
	private Service onCtrlN;
	private Service onCtrlP;

	private Map map;
	
	private JPanel panel;
	private JTextComponent textComp;
	private JLabel label;
	private JButton button_cancel;
	private JButton button_save;
	
	private String editedKey;


	public EntityImpl() throws Exception
	{
		textEditor = Outside.service(this,"*gus06.data.editor.string.textarea.editor1");
		undoRedo = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_zy.undoredo");
		onEscape = Outside.service(this,"gus06.swing.comp.cust3.execute.escape");
		onCtrlS = Outside.service(this,"gus06.swing.comp.cust3.execute.ctrl_s");
		onCtrlN = Outside.service(this,"gus06.swing.comp.cust3.execute.ctrl_n");
		onCtrlP = Outside.service(this,"gus06.swing.comp.cust3.execute.ctrl_p");
		
		label = new JLabel(" ");
		textComp = (JTextComponent) textEditor.r("comp");
		
		E executeCancel = this::cancel;
		onEscape.p(new Object[]{textComp,executeCancel});
		
		E executeSave = this::save;
		onCtrlS.p(new Object[]{textComp,executeSave});
		
		E executeNext = this::next;
		onCtrlN.p(new Object[]{textComp,executeNext});
		
		E executePrevious = this::previous;
		onCtrlP.p(new Object[]{textComp,executePrevious});
		
		button_cancel = new JButton("Cancel");
		button_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {cancel();}
		});
		button_save = new JButton("Save");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {save();}
		});
		
		JPanel panel_buttons = new JPanel(new GridLayout());
		panel_buttons.add(button_cancel);
		panel_buttons.add(button_save);
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add((JComponent) textEditor.i(),BorderLayout.CENTER);
		panel.add(panel_buttons,BorderLayout.SOUTH);
		
		panel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		refresh();
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("editedKey"))
		{
			editedKey = (String) obj;
			refresh();
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("comp")) return textComp;
		if(key.equals("keys")) return new String[]{"comp"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void refresh() throws Exception
	{
		if(map==null || editedKey==null) return;
		
		String value = map.containsKey(editedKey) ? (String) map.get(editedKey) : "";
		label.setText(editedKey);
		textEditor.p(value);
	}
	
	
	
	private void cancel()
	{
		try
		{
			label.setText(" ");
			textEditor.p("");
			done();
		}
		catch(Exception e)
		{Outside.err(this,"cancel()",e);}
	}
	
	
	private void save()
	{
		try
		{
			if(map==null || editedKey==null) return;
			
			String value = (String) textEditor.g();
			map.put(editedKey,value);
			
			editedKey = null;
			label.setText(" ");
			textEditor.p("");
		}
		catch(Exception e)
		{Outside.err(this,"save()",e);}
		
		done();
	}
	
	private void next()
	{
		try
		{
			if(map==null || editedKey==null) return;
			
			String value = (String) textEditor.g();
			map.put(editedKey,value);
			
			editedKey = findNextKey();
			refresh();
		}
		catch(Exception e)
		{Outside.err(this,"next()",e);}
	}
	
	
	private void previous()
	{
		try
		{
			if(map==null || editedKey==null) return;
			
			String value = (String) textEditor.g();
			map.put(editedKey,value);
			
			editedKey = findPreviousKey();
			refresh();
		}
		catch(Exception e)
		{Outside.err(this,"previous()",e);}
	}
	
	
	
	private String findNextKey()
	{
		if(map==null || editedKey==null) return null;
		if(map.isEmpty()) return null;
		
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		int index = keys.indexOf(editedKey);
		if(index==-1) return null;
		
		index++;
		if(index==keys.size()) index = 0;
		return (String) keys.get(index);
	}
	
	
	private String findPreviousKey()
	{
		if(map==null || editedKey==null) return null;
		if(map.isEmpty()) return null;
		
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		int index = keys.indexOf(editedKey);
		if(index==-1) return null;
		
		index--;
		if(index==-1) index = keys.size()-1;
		return (String) keys.get(index);
	}
	
	
	
	private void done()
	{send(this,"done()");}
	
}
