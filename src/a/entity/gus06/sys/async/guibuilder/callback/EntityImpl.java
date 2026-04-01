package a.entity.gus06.sys.async.guibuilder.callback;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141206";}


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
	{return new JPanel1(obj);}
	
	
	
	
	
	
	
	private JPanel waitPanel() throws Exception
	{return (JPanel) buildWaitPanel.i();}
	
	
	private G asyncG(String name) throws Exception
	{return (G) manager.t(name);}
	
	
	
	private JComponent findComp(G g, P p)
	{
		try
		{
			Object result = g.g();
			if(p!=null) p.p(result);
			
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
		private P p;
		
		public JPanel1(Object obj) throws Exception
		{
			super(new BorderLayout());
			handleInput(obj);
			
			if(g.g()!=null)
			{updateGui();return;}
			
			add(waitPanel(),BorderLayout.CENTER);
			((S) g).addActionListener(this);
		}
		
		private void handleInput(Object obj) throws Exception
		{
			if(obj instanceof String)
			{g = asyncG((String) obj);return;}
			
			if(obj instanceof Object[])
			{
				Object[] o = (Object[]) obj;
				if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
				
				g = asyncG((String) o[0]);
				p = (P) o[1];
				return;
			}
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
		
		
		public void actionPerformed(ActionEvent e)
		{updateGui();}
		
		
		private void updateGui()
		{
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {updateGui_();}
			});
		}
		
		private void updateGui_()
		{
			JComponent comp = findComp(g,p);
			removeAll();
			if(comp!=null) add(comp,BorderLayout.CENTER);
			
			synchronized(getTreeLock())
			{validateTree();}

			if(isDisplayable())
			{validate();repaint();}
		}
	}
}
