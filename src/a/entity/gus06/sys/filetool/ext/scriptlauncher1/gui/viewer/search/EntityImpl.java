package a.entity.gus06.sys.filetool.ext.scriptlauncher1.gui.viewer.search;

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
import javax.swing.JTextField;

public class EntityImpl implements Entity, I, P, V {

	public String creationDate() {return "20231028";}


	private Service fieldHolder;
	private Service performSearch;
	
	private JPanel panel;
	private JTextArea area;

	private File root;
	private String scriptName;
	
	private File scriptFile;
	

	public EntityImpl() throws Exception
	{
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		performSearch = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.compute.search.list");
		
		JTextField field = (JTextField) fieldHolder.i();
		
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setEditable(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field, BorderLayout.NORTH);
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
		
		fieldHolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {search();}
		});
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		scriptFile = (File) obj;
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
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void search()
	{
		try
		{
			String query = (String) fieldHolder.g();
			List results = performSearch(query);
			
			StringBuffer b = new StringBuffer();
			for(int i=0;i<results.size();i++)
			b.append(results.get(i)+"\n");
			
			area.setText(b.toString());
		}
		catch(Exception e)
		{Outside.err(this,"search()",e);}
	}
	
	private List performSearch(String query) throws Exception
	{return (List) performSearch.t(new Object[]{root, scriptName, query});}

}