package a.entity.gus06.appli.laboxlsfile.gui.maingui;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import jxl.Workbook;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20150508";}


	private Service chooseFile;
	private Service workbookGui;


	private JPanel panel;
	private JButton button;

	public EntityImpl() throws Exception
	{
		chooseFile = Outside.service(this,"gus06.file.choose.open.file.ext.xls.en");
		workbookGui = Outside.service(this,"*gus06.file.excel.jxl.viewer.workbook");
		
		button = new JButton("Open Excel file");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(button,BorderLayout.NORTH);
		panel.add((JComponent) workbookGui.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{openFile();}
	
	
	private void openFile()
	{
		try
		{
			File file = (File) chooseFile.g();
			if(file==null || !file.isFile()) return;
			
			Workbook w = Workbook.getWorkbook(file);
			workbookGui.p(w);
		}
		catch(Exception e)
		{Outside.err(this,"openFile()",e);}
	}

}
