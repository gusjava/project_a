package a.entity.gus06.sys.filetool.ext.entityimporter1.holder;

import a.framework.*;
import java.util.Map;
import java.io.File;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.JScrollPane;

public class EntityImpl implements Entity, ActionListener, I, P {

	public String creationDate() {return "20250625";}
	
	public static final String INPUT = "input";
	public static final String OUTPUT = "output";


	private Service engine;
	
	private Map map;
	
	private JPanel panel;
	private JButton button;
	private JTextArea area;
	
	private File inputDir;
	private File outputDir;
	


	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.filetool.ext.entityimporter1.engine");
		
		button = new JButton("Import");
		button.addActionListener(this);
		
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		
		inputDir = new File(get0(INPUT));
		outputDir = new File(get0(OUTPUT));
	}
	
	private String get0(String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}


	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	private void perform()
	{
		try
		{
			String text = area.getText();
			String[] lines = text.split("\n");
			
			String line0 = null;
			for (String line : lines)
			if (!line.trim().equals(""))
			{
				if (line0 == null) line0 = line;
				else
				{
					engine.p(new Object[] { inputDir, line0, outputDir, line });
					line0 = null;
				}
			}
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
}
