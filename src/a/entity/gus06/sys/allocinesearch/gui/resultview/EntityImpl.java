package a.entity.gus06.sys.allocinesearch.gui.resultview;

import a.framework.*;
import java.util.List;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.util.Vector;
import java.awt.GridLayout;
import javax.swing.JComponent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;

public class EntityImpl extends S1 implements Entity, I, P, G {

	public String creationDate() {return "20200920";}


	private Service newViewer;

	private List dataList;
	private List viewerList;
	
	private Object selectedViewer;

	private JPanel panel;
	private JScrollPane scroll;
	private JPanel panelCenter;
	private JLabel labelNumber;
	

	public EntityImpl() throws Exception
	{
		newViewer = Outside.service(this,"factory#gus06.sys.allocinesearch.movie.viewer");
		
		labelNumber = new JLabel(" ");
		panelCenter = new JPanel(new GridLayout(0,1));
		
		scroll = new JScrollPane(panelCenter);
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(labelNumber,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return selectedViewer!=null ? ((G)selectedViewer).g() : null;}
	
	
	
	public void p(Object obj) throws Exception
	{
		dataList = (List) obj;
		viewerList = new ArrayList();
		panelCenter.removeAll();
		selectedViewer = null;
		
		for(int i=0;i<dataList.size();i++)
		{
			Object item = dataList.get(i);
			Object viewer = newViewer.g();
			((P) viewer).p(item);
			
			JComponent c = (JComponent) ((I)viewer).i();
			panelCenter.add(c);
			viewerList.add(viewer);
			
			new Holder(viewer);
		}
		
		if(dataList.isEmpty()) labelNumber.setText(" ");
		labelNumber.setText(" Number: "+dataList.size());
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
				scroll.getVerticalScrollBar().setValue(0);
			}
		});
	}
	
	
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
	
	
	
	private void unselectAllExceptSelected()
	{
		try
		{
			for(Object viewer : viewerList)
			if(viewer!=selectedViewer) ((V) viewer).v("unselect",null);
		}
		catch(Exception e)
		{Outside.err(this,"unselectAllExceptSelected()",e);}
	}
	
	
	private class Holder implements ActionListener
	{
		private Object viewer;
		
		public Holder(Object viewer) throws Exception
		{
			this.viewer = viewer;
			((S) viewer).addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			selectedViewer = viewer;
			unselectAllExceptSelected();
			selectionChanged();
		}
	}
}
