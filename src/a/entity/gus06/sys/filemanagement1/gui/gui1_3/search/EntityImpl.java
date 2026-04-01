package a.entity.gus06.sys.filemanagement1.gui.gui1_3.search;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import java.util.Map;
import javax.swing.JTextField;
import java.awt.Insets;
import java.util.List;
import javax.swing.JSplitPane;

public class EntityImpl implements Entity, ActionListener, I, P, Runnable {

	public String creationDate() {return "20191107";}


	private Service buildResults;
	private Service resultPanel;
	private Service clearOnEscape;
	
	private JPanel panel;
	private JTextField field;
	
	private Object engine;
	private Thread t;
	

	public EntityImpl() throws Exception
	{
		buildResults = Outside.service(this,"gus06.sys.filemanagement1.gui.gui1_3.search.buildresults");
		resultPanel = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_3.search.resultpanel1");
		clearOnEscape = Outside.service(this,"gus06.swing.textcomp.cust.action.escap.clear");
		
		field = new JTextField();
		field.setMargin(new Insets(3,3,3,3));
		field.addActionListener(this);
		clearOnEscape.p(field);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add((JComponent) resultPanel.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{engine = obj;}


	public void actionPerformed(ActionEvent e)
	{triggerSearch();}
	
	
	private void triggerSearch()
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{
		field.setEnabled(false);
		perform();
		field.setEnabled(true);
	}
	
	private void perform()
	{
		try
		{
			if(engine==null) return;
			
			resultPanel.p(null);
			
			String input = field.getText().trim();
			if(input.equals("")) return;
			
			List results = (List) buildResults.t(new Object[]{engine,input});
			resultPanel.p(new Object[]{engine,results});
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
}