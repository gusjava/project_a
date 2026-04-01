package a.entity.gus06.sys.mapediting2.key.textarea;

import a.framework.*;
import javax.swing.JPanel;
import java.util.Map;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.text.JTextComponent;
import javax.swing.JComponent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, P, V, I, ItemListener {

	public String creationDate() {return "20220518";}


	private Service textEditor;
	private Service textChanged;
	
	private JPanel panel;
	private JCheckBox check;
	private JTextComponent textComp;
	private S textCompHolder;
	
	private Map map;
	private String mapKey;
	private String initValue;

	public EntityImpl() throws Exception
	{
		textEditor = Outside.service(this,"*gus06.data.editor.string.textarea.editor1");
		textChanged = Outside.service(this,"gus06.swing.textcomp.textchanged.delayed");
		
		check = new JCheckBox("Enable");
		check.addItemListener(this);
		
		textComp = (JTextComponent) textEditor.r("comp");
		textCompHolder = (S) textChanged.t(textComp);
		textCompHolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {updateMap();}
		});
		
		panel = new JPanel(new BorderLayout());
		panel.add(check,BorderLayout.NORTH);
		panel.add((JComponent) textEditor.i(), BorderLayout.CENTER);
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
		if(key.equals("mapKey")) {mapKey = (String) obj;return;}
		if(key.equals("initValue")) {initValue = (String) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void refresh() throws Exception
	{
		if(mapKey==null) {reset();return;}
		
		check.setEnabled(true);
		
		String value = get(mapKey);
		if(value==null)
		{
			check.setSelected(false);
			textComp.setEnabled(false);
			
			String _value = get("_"+mapKey);
			updateComp(_value!=null ? _value : "");
		}
		else
		{
			check.setSelected(true);
			textComp.setEnabled(true);
			
			updateComp(value);
		}
	}
	
	private void reset() throws Exception
	{
		check.setSelected(false);
		check.setEnabled(false);
		textComp.setEditable(false);
		
		updateComp("");
	}
	
	
	
	private void updateComp(String text)
	{
		try
		{
			((P)textCompHolder).p("diactivate");
			textEditor.p(text);
			((P)textCompHolder).p("activate");
		}
		catch(Exception e)
		{Outside.err(this,"updateComp(String)",e);}
	}
	
	
	
	private void updateMap()
	{
		try
		{
			String value = (String) textEditor.g();
			map.put(mapKey,value);
			map.remove("_"+mapKey);
		}
		catch(Exception e)
		{Outside.err(this,"updateMap()",e);}
	}




	private String get(String key)
	{
		if(map==null) return null;
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	
	public void itemStateChanged(ItemEvent e)
	{
		if(check.isSelected())
		{
			textComp.setEnabled(true);
			String _value = get("_"+mapKey);
			if(_value!=null)
			{
				map.put(mapKey,_value);
				map.remove("_"+mapKey);
			}
			else
			{
				map.put(mapKey,initValue);
				updateComp(initValue);
			}
		}
		else
		{
			textComp.setEnabled(false);
			String value = get(mapKey);
			if(value!=null)
			{
				if(!value.trim().equals("")) 
					map.put("_"+mapKey,value);
				map.remove(mapKey);
			}
		}
	}
}