package a.entity.gus06.sys.filemanagement1.gui.detailpanel.file.doubloons;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;

public class EntityImpl implements Entity, ActionListener, I, P, Runnable {

	public String creationDate() {return "20250609";}
	

	private Service buildResults;
	private Service resultPanel;
	
	private Object engine;
	private Map selected;
	private Map prop;
	private String md5;
	
	private JPanel panel;
	private JButton button;
	private Thread t;
	
	
	public EntityImpl() throws Exception
	{
		buildResults = Outside.service(this,"gus06.sys.filemanagement1.gui.detailpanel.file.doubloons.buildresults");
		resultPanel = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.file.doubloons.resultpanel");
		
		button = new JButton("Search for doubloons");
		button.addActionListener(this);
		button.setEnabled(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) resultPanel.i(), BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{
			engine = null;
			selected = null;
			prop = null;
			md5 = null;
			button.setEnabled(false);
			return;
		}
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		selected = (Map) o[1];
		prop = (Map) o[2];
		
		if(prop!=null && engine!=null)
		{
			md5 = (String) prop.get("md5");
			button.setEnabled(true);
		}
		else
		{
			md5 = null;
			button.setEnabled(false);
		}
	}
	


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
		button.setEnabled(false);
		perform();
		button.setEnabled(true);
	}
	
	private void perform()
	{
		try
		{
			if(engine==null) return;
			if(md5==null) return;
			
			resultPanel.p(null);
			
			List results = (List) buildResults.t(new Object[]{engine,md5});
			resultPanel.p(results);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
}