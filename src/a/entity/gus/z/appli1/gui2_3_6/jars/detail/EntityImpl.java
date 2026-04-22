package a.entity.gus.z.appli1.gui2_3_6.jars.detail;

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

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20260421";}


	private Service engine;
	private Service textGui;
	private Service findAll;
	private Service listToString;

	private JLabel labelNumber;
	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this, "gus.y.entitysys1.engine");
		textGui = Outside.service(this,"*gus06.data.editor.string.textarea.editor1");
		findAll = Outside.service(this,"gus06.y.entitydb1.jar.findentitynames.bysha1");
		listToString = Outside.service(this,"gus06.tostring.list.join.n");
		
		labelNumber = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) textGui.i(), BorderLayout.CENTER);
		panel.add(labelNumber, BorderLayout.SOUTH);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
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
	
	private void handle(List sha1) throws Exception
	{handle(new HashSet(sha1));}
	
	private void handle(Set sha1) throws Exception
	{
		Connection cx = (Connection) ((R) engine).r("cx");
		List entityNames = (List) findAll.t(new Object[]{cx, sha1});
		String s = (String) listToString.t(entityNames);
		
		textGui.p(s);
		labelNumber.setText(" "+entityNames.size());
	}
}
