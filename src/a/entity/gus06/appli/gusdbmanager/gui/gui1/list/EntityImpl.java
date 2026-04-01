package a.entity.gus06.appli.gusdbmanager.gui.gui1.list;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.util.Collections;
import java.util.Map;
import java.util.Vector;

public class EntityImpl extends S1 implements Entity, I, G, ActionListener {

	public String creationDate() {return "20150613";}

	
	public static final String TITLE = "Servers";

	
	
	private Service trigger;
	private Service connectors;
	private Service renderer;
	private Service listDelay;
	
	private JLabel titleLabel;
	private JLabel numberLabel;
	private JList list;

	private JPanel panel;
	private String selectedId;
	




	public EntityImpl() throws Exception
	{
		trigger = Outside.service(this,"gus06.appli.gusdbmanager.connection.trigger");
		connectors = Outside.service(this,"gus06.appli.gusdbmanager.data.connectors");
		renderer = Outside.service(this,"gus06.appli.gusdbmanager.gui.gui1.list.renderer");
		listDelay = Outside.service(this,"gus06.swing.list.delaysupport.selection");
		
		titleLabel = new JLabel(TITLE);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		numberLabel = new JLabel(" ");
		
		list = new JList();
		S list_s = (S) listDelay.t(list);
		list_s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {changeSelection();}
		});
		
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2) connect();}
		});
		
		renderer.p(list);
		
		panel = new JPanel(new BorderLayout());
		panel.add(titleLabel,BorderLayout.NORTH);
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		panel.add(numberLabel,BorderLayout.SOUTH);
		
		connectors.addActionListener(this);
		update();
	}



	public Object i() throws Exception
	{return panel;}
	
	
	
	public Object g() throws Exception
	{return selectedId;}



	public void actionPerformed(ActionEvent e)
	{update();}


	
	
	private void update()
	{
		try
		{
			Map map = (Map) connectors.g();
			
			Vector vec = new Vector(map.keySet());
			Collections.sort(vec);
			
			numberLabel.setText(" Number: "+vec.size());
			list.setListData(vec);
		}
		catch(Exception e)
		{Outside.err(this,"update()",e);}
	}
	
	
	
	
	
	
	private void changeSelection()
	{
		try
		{
			selectedId = (String) list.getSelectedValue();
			selectionChanged();
		}
		catch(Exception e)
		{Outside.err(this,"changeSelection()",e);}
	}
	
	
	
	
	
	private void connect()
	{
		try
		{
			String id = (String) list.getSelectedValue();
			if(id==null) return;
			trigger.p(id);
		}
		catch(Exception e)
		{Outside.err(this,"connect()",e);}
	}
	
	
	
	
	
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
}
