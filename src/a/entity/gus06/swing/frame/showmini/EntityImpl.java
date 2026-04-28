package a.entity.gus06.swing.frame.showmini;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20221003";}

	public static final int[] MARGIN = new int[]{3,25,3,25};


	private Service draggable;
	private Service removable;
	private Service autoPack;
	private Service emptyBorder;
	private Service findComp;

	public EntityImpl() throws Exception
	{
		draggable = Outside.service(this,"gus.x.swing.comp.cust.dragframe");
		removable = Outside.service(this,"gus06.swing.comp.cust.removable");
		autoPack = Outside.service(this,"gus06.awt.window.autopack");
		emptyBorder = Outside.service(this,"gus06.find.emptyborder");
		findComp = Outside.service(this,"gus06.find.jcomponent");
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	public Object t(Object obj) throws Exception
	{
		JComponent content = (JComponent) findComp.t(obj);
		content = marginedContent(content);
		
		JDialog dialog = new JDialog();
		dialog.setContentPane(content);
		dialog.setUndecorated(true);
		dialog.setAlwaysOnTop(true);
		
		draggable.p(dialog);
		removable.p(dialog);
		autoPack.p(dialog);
		
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		
		return dialog;
	}
	
	private JComponent marginedContent(JComponent content) throws Exception
	{
		JPanel panel = new JPanel(new BorderLayout());
		Border border = (Border) emptyBorder.t(MARGIN);
		panel.setBorder(border);
		panel.add(content,BorderLayout.CENTER);
		return panel;
	}
}