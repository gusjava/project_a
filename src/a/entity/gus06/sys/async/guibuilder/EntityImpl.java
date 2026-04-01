package a.entity.gus06.sys.async.guibuilder;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20141028";}


	private Service buildWaitPanel;
	private Service errGuiBuilder;
	private Service manager;
	private Service findComp;
	
	
	public EntityImpl() throws Exception
	{
		buildWaitPanel = Outside.service(this,"gus06.swing.panel.build.waitpanel1");
		errGuiBuilder = Outside.service(this,"errguibuilder");
		manager = Outside.service(this,"gus06.sys.async.manager");
		findComp = Outside.service(this,"gus06.find.jcomponent");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{return new JPanel1((String) obj);}
	
	public Object r(String key) throws Exception
	{return new JPanel1(key);}
	
	
	
	
	
	private JPanel waitPanel() throws Exception
	{return (JPanel) buildWaitPanel.i();}
	
	
	
	
	
	private JComponent findComp(G g)
	{
		try
		{
			Object result = g.g();
			if(result instanceof Exception)
				return errComp((Exception) result);
			return (JComponent) findComp.t(result);
		}
		catch(Exception e)
		{
			Outside.err(this,"findComp(G)",e);
			return errComp(e);
		}
	}
	
	
	private JComponent errComp(Exception e)
	{
		try{return (JComponent) errGuiBuilder.t(e);}
		catch(Exception e1){Outside.err(this,"errComp(Exception)",e1);}
		return null;
	}
	
	
	
	
	
	
	
	private class JPanel1 extends JPanel implements ActionListener
	{
		private G g;
		
		public JPanel1(String entityName) throws Exception
		{
			super(new BorderLayout());
			g = (G) manager.t(entityName);
			if(g.g()!=null) {updateGui();return;}
			
			add(waitPanel(),BorderLayout.CENTER);
			((S) g).addActionListener(this);
		}
		
		
		public void actionPerformed(ActionEvent e)
		{updateGui();}
		
		
		private void updateGui()
		{
			JComponent comp = findComp(g);
			removeAll();
			if(comp!=null) add(comp,BorderLayout.CENTER);
			
			synchronized(getTreeLock())
			{validateTree();}

			if(isDisplayable())
			{validate();repaint();}
		}
	}
}
