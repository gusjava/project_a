package a.entity.gus06.appli.allocinesearch.gui.resultview;

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

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20150210";}


	private Service buildMoviePanel;

	private List data;

	private JPanel panel;
	private JScrollPane scroll;
	private JPanel panelCenter;
	private JLabel labelNumber;
	

	public EntityImpl() throws Exception
	{
		buildMoviePanel = Outside.service(this,"gus06.appli.allocinesearch.movie.buildpanel");
		
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
	
	
	
	public void p(Object obj) throws Exception
	{
		data = (List) obj;
		panelCenter.removeAll();
		
		for(int i=0;i<data.size();i++)
		{
			JComponent c = (JComponent) buildMoviePanel.t(data.get(i));
			panelCenter.add(c);
		}
		
		if(data.isEmpty()) labelNumber.setText(" ");
		labelNumber.setText(" Number: "+data.size());
	}
}
