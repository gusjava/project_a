package a.entity.gus06.sys.base1.gui.viewer1.editor;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.util.HashMap;

public class EntityImpl implements Entity, I, P, V, G {

	public String creationDate() {return "20170507";}



	private Service mapEditor;
	private Object base;
	
	private JPanel panel;
	private JLabel label;
	

	public EntityImpl() throws Exception
	{
		mapEditor = Outside.service(this,"*gus06.data.editor.map.stringmap");
		
		label = new JLabel(" ");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createRaisedBevelBorder());
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add((JComponent) mapEditor.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return mapEditor.g();}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(base==null) throw new Exception("Base not initialized yet");
			
		String id = (String) obj;
		if(id!=null)
		{
			Map map = (Map) ((R)base).r("map_"+id);
			label.setText(id);
			mapEditor.p(map);
		}
		else resetEditor();
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("base")) {initBase(obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	private void initBase(Object base) throws Exception
	{
		this.base = base;
		resetEditor();
	}

	
	private void resetEditor() throws Exception
	{
		label.setText(" ");
		mapEditor.p(new HashMap());
	}
}
