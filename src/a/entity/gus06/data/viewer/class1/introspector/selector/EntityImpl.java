package a.entity.gus06.data.viewer.class1.introspector.selector;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;


public class EntityImpl extends S1 implements Entity, I, G, P, ActionListener, ListSelectionListener {

	public String creationDate() {return "20140820";}
	


	private Service fieldHolder;
	private Service buildKeys;
	private Service buildJList;
	private Service linkerListField;
	private Service listRenderer;
	
	private JPanel panel;
	private JComponent field;
	private JList list;
	private JLabel label;
	
	private Class c;
	
	

	public EntityImpl() throws Exception
	{
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		buildKeys = Outside.service(this,"gus06.data.viewer.class1.introspector.selector.buildkeys");
		linkerListField = Outside.service(this,"gus06.swing.list.textfield.linker");
		listRenderer = Outside.service(this,"gus06.data.viewer.class1.introspector.listrenderer");
		
		label = new JLabel(" ");
		field = (JComponent) fieldHolder.i();
		
		list = new JList();
		listRenderer.p(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);

		fieldHolder.addActionListener(this);
		list.addListSelectionListener(this);
		linkerListField.p(new Object[]{list,field});
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return list.getSelectedValue();}
	
	
	
	public void p(Object obj) throws Exception
	{
		c = (Class) obj;
		refresh();
	}
	
	
	public void actionPerformed(ActionEvent e)
	{refresh();}


	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}




	private void refresh()
	{
		try
		{
			if(c==null)
			{
				list.setListData(new Vector());
				label.setText(" ");
			}
			else
			{
				String rule = (String) fieldHolder.g();
				List keys = (List) buildKeys.t(new Object[]{c,rule});
				list.setListData(new Vector(keys));
				label.setText(" number: "+keys.size());
			}
			field.requestFocusInWindow();
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}




	private void selectionChanged()
	{send(this,"selectionChanged()");}
}
