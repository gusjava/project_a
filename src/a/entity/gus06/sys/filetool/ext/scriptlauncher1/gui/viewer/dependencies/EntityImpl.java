package a.entity.gus06.sys.filetool.ext.scriptlauncher1.gui.viewer.dependencies;

import a.framework.*;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Insets;
import java.util.List;

public class EntityImpl implements Entity, I, P, V {

	public String creationDate() {return "20231027";}


	private Service findDepList;
	
	private JPanel panel;
	private JTextArea area;
	private JButton button;

	private File root;
	private String scriptName;
	private String scriptPath;
	private File scriptFile;
	

	public EntityImpl() throws Exception
	{
		findDepList = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.find.dependency.list");
		
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setEditable(false);
		
		button = new JButton("Display");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{display();}
		});
		
		panel = new JPanel(new BorderLayout());
		panel.add(button, BorderLayout.NORTH);
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		scriptFile = (File) obj;
		area.setText("");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("root"))
		{
			root = (File) obj;
			return;
		}
		if(key.equals("scriptName"))
		{
			scriptName = (String) obj;
			return;
		}
		if(key.equals("scriptPath"))
		{
			scriptPath = (String) obj;
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void display()
	{
		try
		{
			List depList = findDepList();
			StringBuffer b = new StringBuffer();
			for(int i=0;i<depList.size();i++)
			b.append(depList.get(i)+"\n");
			area.setText(b.toString());
		}
		catch(Exception e)
		{Outside.err(this,"display()",e);}
	}
	
	private List findDepList() throws Exception
	{return (List) findDepList.t(new Object[]{root, scriptName, scriptPath});}

}