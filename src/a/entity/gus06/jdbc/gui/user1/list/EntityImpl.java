package a.entity.gus06.jdbc.gui.user1.list;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.util.Vector;

public class EntityImpl extends S1 implements Entity, I, P, G, ActionListener {

	public String creationDate() {return "20150625";}
	
	public static final String TITLE = "Users";


	private Service control;
	private Service rendering;
	private Service listDelay;
	private Service updater;



	private JPanel panel;
	
	private JLabel titleLabel;
        private JLabel numberLabel;
        private JList list;
	
	private G holder;
	private String selected;
	
	
	public EntityImpl() throws Exception
	{
		control = Outside.service(this,"*gus06.jdbc.gui.user1.list.control");
		rendering = Outside.service(this,"gus06.jdbc.gui.user1.list.rendering");
		updater = Outside.service(this,"gus06.jdbc.gui.user1.list.updater");
		listDelay = Outside.service(this,"gus06.swing.list.delaysupport.selection");
		
		titleLabel = new JLabel(TITLE);
        	titleLabel.setHorizontalAlignment(JLabel.CENTER);
        	titleLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        	
        	numberLabel = new JLabel(" ");
        	
        	list = new JList();
        	rendering.p(list);

        	S list_s = (S) listDelay.t(list);
        	list_s.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{changeSelection();}
		});
		
		control.v("selector",this);
        	
    		
    		JPanel p_bottom = new JPanel(new BorderLayout());
        	p_bottom.setBorder(BorderFactory.createRaisedBevelBorder());
        	p_bottom.add(numberLabel,BorderLayout.CENTER);
        	p_bottom.add((JComponent) control.i(),BorderLayout.EAST);

		panel = new JPanel(new BorderLayout());
		panel.add(titleLabel,BorderLayout.NORTH);
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		panel.add(p_bottom,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return selected;}
	
	
	
	public void p(Object obj) throws Exception
	{
		setHolder(obj);
		control.p(holder);
		updateGui();
	}
	
	
	
	private void setHolder(Object obj) throws Exception
	{
		if(holder!=null) ((S) holder).removeActionListener(this);
		holder = (G) obj;
		if(holder!=null) ((S) holder).addActionListener(this);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	
	private void updateGui()
	{
		try{updater.p(new Object[]{holder,list,numberLabel});}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	

	
	
	
	private void changeSelection()
	{
		selected = (String) list.getSelectedValue();
		selectionChanged();
	}
		
	private void selectionChanged()
	{send(this,"selectionChanged()");}
}
