package a.entity.gus06.appli.gusexplorer.handle.fullpanel;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.Container;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200402";}


	private Service centralPane;
	private Service onKey;
	private Service compReplacer;
	private Service isInside;


	public EntityImpl() throws Exception
	{
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		compReplacer = Outside.service(this,"gus06.swing.comp.build.compreplacer");
		isInside = Outside.service(this,"gus06.app.mainframe.check.isinside");
	}
	
	private void initCentralPane() throws Exception
	{
		if(centralPane==null)
		centralPane = Outside.service(this,"gus06.appli.gusexplorer.gui.centralpane");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JComponent)
		{
			JComponent comp = (JComponent) obj;
			new Holder1(comp);
		}
		else if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			JComponent comp1 = (JComponent) o[0];
			JComponent comp2 = (JComponent) o[1];
			new Holder2(comp1,comp2);
		}
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class Holder1 implements E
	{
		private JComponent comp1;
		private boolean isFull = false;
		
		public Holder1(JComponent comp1) throws Exception
		{
			this.comp1 = comp1;
			onKey.p(new Object[]{comp1,"ESCAPE",this});
		}
		
		public void e() throws Exception
		{
			if(isFull) goBack();
			else fullPanel();
		}
		
		private void fullPanel() throws Exception
		{
			Container comp2 = comp1.getParent();
			if(!isInside.f(comp2)) return;
			
			initCentralPane();
			E replace = (E) compReplacer.t(comp2);
			centralPane.p(new Object[]{comp2,replace});
			isFull = true;
		}
		
		private void goBack() throws Exception
		{
			initCentralPane();
			centralPane.e();
			isFull = false;
		}
	}
	
	
	
	
	private class Holder2 implements E
	{
		private JComponent comp1;
		private JComponent comp2;
		private boolean isFull = false;
		
		public Holder2(JComponent comp1, JComponent comp2) throws Exception
		{
			this.comp1 = comp1;
			this.comp2 = comp2;
			
			onKey.p(new Object[]{comp1,"ESCAPE",this});
		}
		
		public void e() throws Exception
		{
			if(isFull) goBack();
			else fullPanel();
		}
		
		private void fullPanel() throws Exception
		{
			if(!isInside.f(comp2)) return;
			
			initCentralPane();
			E replace = (E) compReplacer.t(comp2);
			centralPane.p(new Object[]{comp2,replace});
			isFull = true;
		}
		
		private void goBack() throws Exception
		{
			initCentralPane();
			centralPane.e();
			isFull = false;
		}
	}
}
