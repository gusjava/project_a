package a.entity.gus06.sys.entityeditor1.gui.gui2.detail;

import a.framework.*;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.util.Set;
import java.util.HashSet;
import java.sql.Connection;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P, V {

	public String creationDate() {return "20251215";}


	private Service textGui;
	private Service findAll;
	private Service setToString;

	private JLabel labelNumber;
	private JPanel panel;
	
	private Object engine;
	

	public EntityImpl() throws Exception
	{
		textGui = Outside.service(this,"*gus06.data.editor.string.textarea.editor1");
		findAll = Outside.service(this,"gus06.y.entitydb1.entity_import.find2.in");
		setToString = Outside.service(this,"gus06.tostring.set.join.n");
		
		labelNumber = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) textGui.i(), BorderLayout.CENTER);
		panel.add(labelNumber, BorderLayout.SOUTH);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("engine")) {this.engine = obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	public void p(Object obj) throws Exception
	{
		if(engine==null) throw new Exception("Engine not initialized yet");
		
		if(obj==null) reset();
		else if(obj instanceof String) handle(List.of(obj));
		else if(obj instanceof List) handle((List) obj);
		else if(obj instanceof Set) handle((Set) obj);
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private void reset() throws Exception
	{
		labelNumber.setText(" ");
		textGui.p("");
	}
	
	private void handle(List imports) throws Exception
	{handle(new HashSet(imports));}
	
	private void handle(Set imports) throws Exception
	{
		Connection cx = (Connection) ((R) engine).r("cx");
		Set entityNames = (Set) findAll.t(new Object[]{cx, imports});
		String s = (String) setToString.t(entityNames);
		
		textGui.p(s);
		labelNumber.setText(" "+entityNames.size());
	}
}
