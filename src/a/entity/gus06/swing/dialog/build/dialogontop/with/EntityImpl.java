package a.entity.gus06.swing.dialog.build.dialogontop.with;

import a.framework.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.util.Map;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20190519";}


	private Service toComp;
	private Service cust;

	public EntityImpl() throws Exception
	{
		toComp = Outside.service(this,"gus06.swing.comp.find");
		cust = Outside.service(this,"gus06.swing.dialog.cust3.map1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JComponent comp = (JComponent) toComp.t(o[0]);
		Map options = (Map) o[1];
		
    		Dialog1 d = new Dialog1(comp,options);
		cust.p(new Object[]{d,options});
		d.setVisible(true);
	}
	
	
	public Object t(Object obj) throws Exception
	{
    		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JComponent comp = (JComponent) toComp.t(o[0]);
		Map options = (Map) o[1];
		
		Dialog1 d = new Dialog1(comp,options);
		cust.p(new Object[]{d,options});
		return d;
	}
	
	
	
	
	
	private class Dialog1 extends JDialog
	{
		public Dialog1(JComponent content, Object options)
		{
			super((JFrame) null,false);
			
			setUndecorated(true);
			setResizable(false);
			setAlwaysOnTop(true);
			
			setContentPane(content);
			setLocationRelativeTo(null);
		}
	}
}
