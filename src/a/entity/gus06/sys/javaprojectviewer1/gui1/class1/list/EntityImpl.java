package a.entity.gus06.sys.javaprojectviewer1.gui1.class1.list;

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

	public String creationDate() {return "20170220";}

	public static final String TITLE = "Classes";


	private Service control;
	private Service rendering;
	private Service listDelay;
	private Service updater;



	private JPanel panel;
	
	private JLabel titleLabel;
        private JLabel numberLabel;
        private JList list;
	
	private Object data;
	private Object selected;
	
	
	public EntityImpl() throws Exception
	{
		control = Outside.service(this,"*gus06.sys.javaprojectviewer1.gui1.class1.list.control");
		rendering = Outside.service(this,"gus06.sys.javaprojectviewer1.gui1.class1.list.rendering");
		updater = Outside.service(this,"gus06.sys.javaprojectviewer1.gui1.class1.list.updater");
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
		data = obj;
		control.p(data);
		updateGui();
	}
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	
	private synchronized void updateGui()
	{
		try{updater.p(new Object[]{data,list,numberLabel});}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	private synchronized void changeSelection()
	{
		Object[] tab = (Object[]) list.getSelectedValue();
		selected = tab!=null ? tab[1] : null;
		selectionChanged();
	}
		
	private void selectionChanged()
	{send(this,"selectionChanged()");}
}
