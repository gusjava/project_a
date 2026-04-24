package a.entity.gus06.java.introspection.gui.selector;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;


public class EntityImpl extends S1 implements Entity, I, G, ActionListener, ListSelectionListener {

	public String creationDate() {return "20140819";}
	
	public static final String ICONID = "UTIL_class";


	private Service fieldHolder;
	private Service buildKeys;
	private Service buildJList;
	private Service linkerListField;
	
	private JPanel panel;
	private JComponent field;
	private JList list;
	private JLabel label;
	

	public EntityImpl() throws Exception
	{
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		buildKeys = Outside.service(this,"gus06.java.introspection.gui.selector.buildkeys");
		buildJList = Outside.service(this,"gus06.swing.list.build.fromicon");
		linkerListField = Outside.service(this,"gus.x.swing.list.textfield.linker");
		
		label = new JLabel(" ");
		field = (JComponent) fieldHolder.i();
		list = (JList) buildJList.t(ICONID);

		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);

		fieldHolder.addActionListener(this);
		list.addListSelectionListener(this);
		linkerListField.p(new Object[]{list,field});
		
		refresh();
	}
	
	
	public Object g() throws Exception
	{return list.getSelectedValue();}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void actionPerformed(ActionEvent e)
	{refresh();}


	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}



	private void refresh()
	{
		try
		{
			String input = (String) fieldHolder.g();
			List keys = (List) buildKeys.t(input);
			list.setListData(new Vector(keys));
			label.setText(" number: "+keys.size());
			
			field.requestFocusInWindow();
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}




	private void selectionChanged()
	{send(this,"selectionChanged()");}
}
