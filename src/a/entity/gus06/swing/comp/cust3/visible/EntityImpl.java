package a.entity.gus06.swing.comp.cust3.visible;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140801";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception();
		
		JComponent c = (JComponent) o[0];
		G g = (G) o[1];
		new Holder(c,g);
	}
	
	
	private class Holder implements ActionListener
	{
		private JComponent c;
		private G g;
	
		public Holder(JComponent c, G g) throws Exception
		{
			this.c = c;
			this.g = g;
			checkVisible(c,g);
			((S)g).addActionListener(this);
		}
		public void actionPerformed(ActionEvent e)
		{checkVisible(c,g);}
	}
	
	
	
	private void checkVisible(JComponent c, G g)
	{
		try{c.setVisible(g.g()!=null);}
		catch(Exception e){Outside.err(this,"checkVisible(JComponent,G)",e);}
	}
}
