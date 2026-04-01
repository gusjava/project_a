package a.entity.gus06.sys.base2.gui.panel1.preview;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20221104";}

	public static final String TITLE = "PREVISUALISATION";


	private Service buildTitleLabel;

	private Object base;
	private JPanel panel;
	
	public EntityImpl() throws Exception
	{
		buildTitleLabel = Outside.service(this,"gus06.swing.label.build.titlelabel2");
		
		JLabel labelTitle = (JLabel) buildTitleLabel.r(TITLE);
		
		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle, BorderLayout.NORTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		base = obj;
		
	}
}
