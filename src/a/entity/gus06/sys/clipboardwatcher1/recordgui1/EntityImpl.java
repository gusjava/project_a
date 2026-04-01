package a.entity.gus06.sys.clipboardwatcher1.recordgui1;

import a.framework.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20180409";}

	
	public static final String BLANK = "`";
	public static final String DELIM_LINE = "----------------";

	
	private Service watchClipboard;
	private Service boldSelected;
	private Service persist;
	private Service group01;
	private Service openDir;
	
	private File storeDir;
	
	private JPanel panel;
	private JTextArea area;

	private JCheckBox check_lines;
	private JCheckBox check_blocks;
	private JCheckBox check_files;
	private JCheckBox check_reset;
	private JButton button_openDir;
	private JButton button_clearArea;
	
	
	



	public EntityImpl() throws Exception
	{
		watchClipboard = Outside.service(this,"gus06.sys.clipboardwatcher1.engine");
		boldSelected = Outside.service(this,"gus06.swing.button.cust.boldselected");
		persist = Outside.service(this,"gus06.swing.checkbox.persister.selected");
		group01 = Outside.service(this,"gus06.swing.checkbox.group10");
		openDir = Outside.service(this,"gus06.awt.desktop.open");
		
		storeDir = (File) Outside.resource(this,"defaultdir");
		
		check_lines = check("Record as lines");
		check_blocks = check("Record as blocks");
		check_files = check("Record as files");
		check_reset = check("Reset after each record");
		
		button_openDir = new JButton("Open store dir");
		button_openDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {openDir();}
		});
		
		button_clearArea = new JButton("Clear text area");
		button_clearArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {clearArea();}
		});
		
		group01.p(new JCheckBox[]{check_lines,check_blocks,check_files});
		
		persist("check_lines",check_lines);
		persist("check_blocks",check_blocks);
		persist("check_files",check_files);
		persist("check_reset",check_reset);
		
		JPanel p_top = new JPanel(new GridLayout(3,2,5,5));
		p_top.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
		
		p_top.add(check_lines);
		p_top.add(check_reset);
		
		p_top.add(check_blocks);
		p_top.add(left(button_openDir));
		
		p_top.add(check_files);
		p_top.add(left(button_clearArea));
		
		
		area = new JTextArea();
		area.setEditable(false);
		
		panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		panel.add(left(p_top),BorderLayout.NORTH);
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		
		watchClipboard.addActionListener(this);
	}



	public Object i() throws Exception
	{return panel;}


	
	
	public void actionPerformed(ActionEvent e)
	{clipboardChanged();}

	
	
	private void clipboardChanged()
	{
		try
		{
			boolean reset = check_reset.isSelected();
			String text = (String) watchClipboard.g();
			if(reset && text.equals(BLANK)) return;
				
			if(check_lines.isSelected())
				append(text+"\n");
			else if(check_blocks.isSelected())
				append(text+"\n"+DELIM_LINE+"\n");
			else if(check_files.isSelected())
				recordInsideDir(text);
			
			if(reset) watchClipboard.p(BLANK);
		}
		catch(Exception e)
		{Outside.err(this,"clipboardChanged()",e);}
	}
	
	
	
	private void openDir()
	{
		try{openDir.p(storeDir);}
		catch(Exception e)
		{Outside.err(this,"openDir()",e);}
	}
	
	
	private void append(String text)
	{
		area.append(text);
		area.setCaretPosition(area.getDocument().getLength());
	}
	
	
	
	private void clearArea()
	{
		area.setText("");
	}
	
	
	
	private JPanel left(JComponent c)
	{
		JPanel p = new JPanel(new BorderLayout());
		p.add(c,BorderLayout.WEST);
		return p;
	}
	
	
	private JCheckBox check(String text) throws Exception
	{
		JCheckBox check = new JCheckBox(text);
		boldSelected.p(check);
		return check;
	}
	
	
	private void persist(String key, JComponent comp) throws Exception
	{persist.v(getClass().getName()+"_"+key,comp);}
	
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private String now(){return sdf.format(new Date());}

	
	private void recordInsideDir(String text) throws Exception
	{
		File f = new File(storeDir,"clipboard_"+now()+".txt");
		PrintStream p = new PrintStream(f);
		p.print(text);
		p.close();
	}
}