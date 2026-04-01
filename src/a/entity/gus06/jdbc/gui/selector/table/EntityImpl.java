package a.entity.gus06.jdbc.gui.selector.table;

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

	public static final String TITLE = "Tables";


	private Service findTable;
	private Service rendering;
	private Service listDelay;

	private JPanel panel;
	private JList list;
	private JLabel titleLabel;
        private JLabel numberLabel;


	public EntityImpl() throws Exception
	{
		findTable = Outside.service(this,"gus06.jdbc.generic.perform.find.tableset.db");
		rendering = Outside.service(this,"gus06.jdbc.gui.cx1.db.table.list.rendering");
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
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		
		Set set = (Set) findTable.t(new Object[]{cx,dbName});
		Vector vec = set!=null?new Vector(set):new Vector();
		Collections.sort(vec);
		
		numberLabel.setText(" "+vec.size()+" ");
		list.setListData(vec);
	}
	
	
	private void selected()
	{send(this,"selected()");}
}
