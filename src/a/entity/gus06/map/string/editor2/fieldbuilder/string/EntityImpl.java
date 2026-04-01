package a.entity.gus06.map.string.editor2.fieldbuilder.string;

import a.framework.*;
import java.util.Map;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230215";}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Field((String) obj);
	}
	
	private class Field implements I, P, DocumentListener, ActionListener
	{
		private boolean compToMap = false;
		private boolean mapToComp = false;
		
		private String key;
		private Map map;
		
		private JTextField comp;
		
		public Field(String key)
		{
			this.key = key;
			comp = new JTextField();
			comp.getDocument().addDocumentListener(this);
		}
		
		public Object i() throws Exception
		{return comp;}
		
		
		public void p(Object obj) throws Exception
		{
			if(map!=null && map instanceof S)
			((S)map).removeActionListener(this);
			
			map = (Map) obj;
			if(map!=null && map instanceof S)
			((S)map).addActionListener(this);
			
			mapToComp();
		}
	
	
		public void changedUpdate(DocumentEvent e) {}
		public void insertUpdate(DocumentEvent e) {if(!mapToComp) compToMap();}
		public void removeUpdate(DocumentEvent e) {if(!mapToComp) compToMap();}
	
		public void actionPerformed(ActionEvent e) {if(!compToMap) mapToComp();}
		
		private void compToMap()
		{
			if(map==null) return;
			compToMap = true;
			try
			{
				String value = comp.getText();
				map.put(key,value);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"compToMap()",e);}
			compToMap = false;
		}
	
		private void mapToComp()
		{
			if(map==null) return;
			mapToComp = true;
			try
			{
				String value = map.containsKey(key) ? (String) map.get(key) : "";
				comp.setText(value);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"mapToComp()",e);}
			mapToComp = false;
		}
	}
}