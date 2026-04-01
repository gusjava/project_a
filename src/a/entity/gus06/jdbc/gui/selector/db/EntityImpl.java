package a.entity.gus06.jdbc.gui.selector.db;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.Set;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.util.Vector;
import java.util.Collections;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, I, P, G {

	public String creationDate() {return "20161114";}

	public static final String TITLE = "Databases";


	private Service findDb;
	private Service rendering;
	private Service listDelay;

	private JPanel panel;
	private JList list;
	private JLabel titleLabel;
        private JLabel numberLabel;


	public EntityImpl() throws Exception
	{
		findDb = Outside.service(this,"gus06.jdbc.generic.perform.find.dbset");
		rendering = Outside.service(this,"gus06.jdbc.gui.selector.db.listrendering");
		listDelay = Outside.service(this,"gus06.swing.list.delaysupport.selection");
		
		list = new JList();
		rendering.p(list);
		numberLabel = new JLabel();
		
		titleLabel = new JLabel(TITLE);
        	titleLabel.setHorizontalAlignment(JLabel.CENTER);
        	titleLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		panel = new JPanel(new BorderLayout());
		panel.add(titleLabel,BorderLayout.NORTH);
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		panel.add(numberLabel,BorderLayout.SOUTH);
		
		S list_s = (S) listDelay.t(list);
        	list_s.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{selected();}
		});
	}
	
	
	public Object i() throws Exception
	{return panel;}


	
	public Object g() throws Exception
	{return list.getSelectedValue();}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{
			numberLabel.setText(" ");
			list.setListData(new Vector());
			return;
		}
		
		Connection cx = (Connection) obj;
		Set set = (Set) findDb.t(cx);
		Vector vec = set!=null?new Vector(set):new Vector();
		Collections.sort(vec);
		
		numberLabel.setText(" "+vec.size()+" ");
		list.setListData(vec);
	}
	
	
	private void selected()
	{send(this,"selected()");}
}