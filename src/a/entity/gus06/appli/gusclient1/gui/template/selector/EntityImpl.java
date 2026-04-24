package a.entity.gus06.appli.gusclient1.gui.template.selector;

import a.framework.*;
import javax.swing.*;
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


public class EntityImpl extends S1 implements Entity, I, G, ActionListener, ListSelectionListener {

	public String creationDate() {return "20140905";}
	
	public static final String ICONID = "UTIL_template";


	private Service fieldHolder;
	private Service buildKeys;
	private Service buildJList;
	private Service linkerListField;
	
	private Service rename;
	private Service remove;
	private Service duplicate;

	private JPanel panel;
	private JComponent field;
	private JList list;
	private JLabel label;


	public EntityImpl() throws Exception
	{
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		buildKeys = Outside.service(this,"gus06.appli.gusclient1.gui.template.selector.buildkeys");
		buildJList = Outside.service(this,"gus06.swing.list.build.fromicon");
		linkerListField = Outside.service(this,"gus.x.swing.list.textfield.linker");
		
		rename = Outside.service(this,"gus06.appli.gusclient1.execute.template.rename");
		remove = Outside.service(this,"gus06.appli.gusclient1.execute.template.remove");
		duplicate = Outside.service(this,"gus06.appli.gusclient1.execute.template.duplicate");
		
		
		label = new JLabel(" ");
		field = (JComponent) fieldHolder.i();
		list = (JList) buildJList.t(ICONID);

		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
		

		field.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0),new AbstractAction() {
			public void actionPerformed(ActionEvent e) {refresh();}
		});

		list.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				int code = e.getKeyCode();
				
				if(code==KeyEvent.VK_F5) refresh();
			
				else if(code==KeyEvent.VK_F2) execute(rename);
				else if(code==KeyEvent.VK_F3) execute(duplicate);
				else if(code==KeyEvent.VK_DELETE) execute(remove);
			}
		});

		fieldHolder.addActionListener(this);
		list.addListSelectionListener(this);
		linkerListField.p(new Object[]{list,field});
		
		refresh();
	}
	
	
	public Object g() throws Exception
	{return list.getSelectedValue();}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void e() throws Exception
	{refresh();}



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



	
	
	private void execute(Service s)
	{
		try{s.e();}
		catch(Exception e) {Outside.err(this,"execute(Service)",e);}
	}
}
