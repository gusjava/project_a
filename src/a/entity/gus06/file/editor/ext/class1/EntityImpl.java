package a.entity.gus06.file.editor.ext.class1;

import a.framework.*;
import java.io.File;
import javax.swing.JComponent;
import java.util.Set;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.Insets;
import javax.swing.JScrollPane;
import java.util.Map;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140807";}

	
	private Service formPanel;
	private Service jdkMapping;
	private Service analyze;
	private Service setToString;
	
	private JPanel panel;
	private JTextArea area;
	
	private File file;
	


	public EntityImpl() throws Exception
	{
		formPanel = Outside.service(this,"*gus06.swing.panel.formpanel.panel1");
		jdkMapping = Outside.service(this,"gus06.java.jdk.versionmapping");
		analyze = Outside.service(this,"gus06.java.bytecode.jdepend.analyze.data");
		setToString = Outside.service(this,"gus06.tostring.set");
		
		area = new JTextArea();
		area.setEditable(false);
		area.setMargin(new Insets(3,3,3,3));
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) formPanel.i(),BorderLayout.NORTH);
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile() || file.length()==0) resetGui();
		else updateGui();
	}
	
	
	
	private void resetGui() throws Exception
	{
		formPanel.e();
		area.setText("");
	}
	
	
	private void updateGui() throws Exception
	{
		Map data = (Map) analyze.t(file);
		
		Integer minor = (Integer) data.get("minorversion");
		Integer major = (Integer) data.get("majorversion");
		Set imports = (Set) data.get("imports");
		
		String jdkVer = (String) jdkMapping.t(major);
		String imports_ = (String) setToString.t(imports);
	
		formPanel.e();
		formPanel.v("JDK version",jdkVer);
		formPanel.v("Minor version",""+minor);
		formPanel.v("Major version",""+major);
		formPanel.v("Imports nb",""+imports.size());
		
		area.setText(imports_);
	}
}