package a.entity.gus06.appli.gusdbmanager.gui.cx.bottombar;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150613";}


	private Service statusLabel;
	private Service userLabel;
	

	public EntityImpl() throws Exception
	{
		statusLabel = Outside.service(this,"gus06.appli.gusdbmanager.gui.cx.bottombar.statuslabel");
		userLabel = Outside.service(this,"gus06.appli.gusdbmanager.gui.cx.bottombar.userlabel");
	}



	public Object t(Object obj) throws Exception
	{return new JPanel1(obj);}


	
	
	private class JPanel1 extends JPanel
	{
		public JPanel1(Object holder) throws Exception
		{
			super(new BorderLayout());
			setBorder(BorderFactory.createRaisedBevelBorder());
			
			JComponent statusComp = (JComponent) statusLabel.t(holder);
			JComponent userComp = (JComponent) userLabel.t(holder);
			
			add(statusComp,BorderLayout.WEST);			
			add(userComp,BorderLayout.CENTER);
		}
	}
}
