package a.entity.gus06.sys.entitysrcviewer1.gui.listing.entity;

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
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;


public class EntityImpl extends S1 implements Entity, P, I, G, ActionListener, ListSelectionListener {

	public String creationDate() {return "20191007";}

	public static final String ICONID = "entity";

	private Service fieldHolder;
	private Service buildJList;
	private Service linkerListField;
	private Service buildActionCopy;
	private Service listFilter;

	private JPanel panel;
	private JComponent field;
	private JList list;
	private JLabel label;
	
	private List listing;


	public EntityImpl() throws Exception
	{
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		buildJList = Outside.service(this,"gus06.swing.list.build.fromicon");
		linkerListField = Outside.service(this,"gus06.swing.list.textfield.linker");
		buildActionCopy = Outside.service(this,"gus06.swing.list.build.action.copy");
		listFilter = Outside.service(this,"gus06.list.filter.rule.one");
		
		label = new JLabel(" ");
		field = (JComponent) fieldHolder.i();
		list = (JList) buildJList.t(ICONID);
		
		Action copyAction = (Action) buildActionCopy.t(list);
		JPanel p_bottom = new JPanel(new BorderLayout());
		p_bottom.add(new JButton(copyAction),BorderLayout.EAST);
		p_bottom.add(label,BorderLayout.CENTER);

		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		panel.add(p_bottom,BorderLayout.SOUTH);
		

		field.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0),new AbstractAction() {
			public void actionPerformed(ActionEvent e) {reload();}
		});

		list.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				int code = e.getKeyCode();
				if(code==KeyEvent.VK_F5) reload();
			}
		});

		fieldHolder.addActionListener(this);
		list.addListSelectionListener(this);
		linkerListField.p(new Object[]{list,field});
	}
	
	
	public Object g() throws Exception
	{return list.getSelectedValue();}
	
	
	public Object i() throws Exception
	{return panel;}


	
	public void p(Object obj) throws Exception
	{
		listing = (List) obj;
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
			String input = (String) fieldHolder.g();
			
			List keys = (List) listFilter.t(new Object[]{listing,input});
			list.setListData(toVector(keys));
			label.setText(buildNumberDisplay(keys));
			
			field.requestFocusInWindow();
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	private Vector toVector(List keys)
	{
		if(keys==null) return new Vector();
		return new Vector(keys);
	}
	
	
	private String buildNumberDisplay(List keys)
	{
		if(keys==null) return "?";
		return " number: "+keys.size();
	}
	
	

	private void selectionChanged()
	{send(this,"selectionChanged()");}
	
	private void reload()
	{send(this,"reload()");}
}
