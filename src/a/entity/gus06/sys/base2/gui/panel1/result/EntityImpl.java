package a.entity.gus06.sys.base2.gui.panel1.result;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20221104";}

	public static final String TITLE = "RESULTATS";


	private Service buildTitleLabel;

	private Object base;
	
	private JPanel panel;
	private JLabel labelTitle;
	private JLabel labelNumber;
	
	public EntityImpl() throws Exception
	{
		buildTitleLabel = Outside.service(this,"gus06.swing.label.build.titlelabel2");
		
		labelTitle = (JLabel) buildTitleLabel.r(TITLE);
		labelNumber = new JLabel(" Nombre: ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle, BorderLayout.NORTH);
		panel.add(labelNumber, BorderLayout.SOUTH);
		
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		base = obj;
		
	}
}