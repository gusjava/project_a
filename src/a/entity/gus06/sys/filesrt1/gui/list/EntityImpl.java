package a.entity.gus06.sys.filesrt1.gui.list;

import a.framework.*;
import javax.swing.JPanel;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.util.Vector;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20230106";}


	private Service listRenderer;


	private JPanel panel;

	private List data;
	private JLabel labelNumber;
	private JList list;
	

	public EntityImpl() throws Exception
	{
		listRenderer = Outside.service(this,"gus06.sys.filesrt1.gui.listrenderer");
		
		list = new JList();
		listRenderer.p(list);
		
		labelNumber = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(list), BorderLayout.CENTER);
		panel.add(labelNumber, BorderLayout.SOUTH);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (List) obj;
		if(data==null)
		{
			list.setListData(new Vector());
			labelNumber.setText(" ");
			return;
		}
		
		Vector vec = new Vector(data);
		list.setListData(vec);
		labelNumber.setText(" "+vec.size());
	}
}