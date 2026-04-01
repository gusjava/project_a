package a.entity.gus06.swing.textcomp.persister.text.togp;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200402";}


	private Service buildDelayed;


	public EntityImpl() throws Exception
	{
		buildDelayed = Outside.service(this,"gus06.swing.textcomp.textchanged.delayed");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length==2) handle((JTextComponent) o[0],(G) o[1],(P) o[1]);
		else if(o.length==3) handle((JTextComponent) o[0],(G) o[1],(P) o[2]);
		else throw new Exception("Wrong data number: "+o.length);
	}
	
		
	private void handle(JTextComponent comp, G g, P p) throws Exception
	{
		String text = (String) g.g();
		if(text!=null) comp.setText(text);
		new Holder(comp,p);
	}
	
	
	private class Holder implements ActionListener
	{
		private JTextComponent comp;
		private P p;
		
		public Holder(JTextComponent comp, P p) throws Exception
		{
			this.comp = comp;
			this.p = p;
			
			S delayed = (S) buildDelayed.t(comp);
			delayed.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{save(comp,p);}
	}
	
	
	private void save(JTextComponent comp, P p)
	{
		try
		{
			String s = comp.getText();
			p.p(s);
		}
		catch(Exception e)
		{Outside.err(this,"save(JTextComponent,P)",e);}
	}
}
