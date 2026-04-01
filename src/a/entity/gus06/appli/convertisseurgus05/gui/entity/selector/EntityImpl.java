package a.entity.gus06.appli.convertisseurgus05.gui.entity.selector;

import a.framework.*;
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
import javax.swing.JComponent;


public class EntityImpl extends S1 implements Entity, I, G, E, ActionListener, ListSelectionListener {

	public String creationDate() {return "20150525";}

	public static final String ICONID = "entity_gus05";
	public static final String PERSIST_KEY = EntityImpl.class.getName();

	private Service fieldHolder;
	private Service buildKeys;
	private Service buildJList;
	private Service linkerListField;
	private Service fieldPersister;
	
	private Service executeOpenDir;
	private Service executeOpenFiles;
	private Service executeConvert;
	private Service executeCheck;

	private JPanel panel;
	private JComponent field;
	private JList list;
	private JLabel label;


	public EntityImpl() throws Exception
	{
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		buildKeys = Outside.service(this,"gus06.appli.convertisseurgus05.gui.entity.selector.buildkeys");
		buildJList = Outside.service(this,"gus06.swing.list.build.fromicon");
		linkerListField = Outside.service(this,"gus06.swing.list.textfield.linker");
		fieldPersister = Outside.service(this,"gus06.swing.textcomp.persister.text");
		
		executeOpenDir = Outside.service(this,"gus06.appli.convertisseurgus05.execute.selected.opendir");
		executeOpenFiles = Outside.service(this,"gus06.appli.convertisseurgus05.execute.selected.openfiles");
		executeConvert = Outside.service(this,"gus06.appli.convertisseurgus05.execute.selected.convert");
		executeCheck = Outside.service(this,"gus06.appli.convertisseurgus05.execute.selected.checkgus06");
		
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
		field.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0),new AbstractAction() {
			public void actionPerformed(ActionEvent e) {execute(null);}
		});

		list.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				int code = e.getKeyCode();
				
				if(code==KeyEvent.VK_F5) refresh();
			
				else if(code==KeyEvent.VK_SPACE) execute(executeOpenDir);
				else if(code==KeyEvent.VK_F1) execute(executeOpenFiles);
				else if(code==KeyEvent.VK_F2) execute(executeCheck);
				else if(code==KeyEvent.VK_F3) execute(executeConvert);
			}
		});

		fieldHolder.addActionListener(this);
		list.addListSelectionListener(this);
		linkerListField.p(new Object[]{list,field});
		
		fieldPersister.v(PERSIST_KEY+"_field",field);
		//refresh();
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
			Vector keys = toVector((List) buildKeys.t(input));
			list.setListData(keys);
			label.setText(" number: "+keys.size());
			
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




	private void selectionChanged()
	{send(this,"selectionChanged()");}



	
	
	private void execute(Service s)
	{
		try{s.e();}
		catch(Exception e) {Outside.err(this,"execute(Service)",e);}
	}
}
