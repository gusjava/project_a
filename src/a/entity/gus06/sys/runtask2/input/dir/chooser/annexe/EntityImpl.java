package a.entity.gus06.sys.runtask2.input.dir.chooser.annexe;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;
import javax.swing.JComponent;
import java.io.File;
import java.awt.Dimension;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20251212";}
	
	public static final Dimension DIM = new Dimension(800,400);


	private Service manager;
	private Service readFile;
	private Service viewer;
	
	private JPanel panel;
	private JLabel label;
	
	private String key;
	private File file;
	private String script;
	

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.sys.runtask2.input.dir.manager");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		viewer = Outside.service(this,"*gus06.data.viewer.string");
		
		label = new JLabel(" ");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createEtchedBorder());
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
		
		panel.setPreferredSize(DIM);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		key = (String) obj;
		file = (File) manager.r("file:"+key);
		script = (String) readFile.t(file);
		
		label.setText(key);
		viewer.p(script);
	}
}