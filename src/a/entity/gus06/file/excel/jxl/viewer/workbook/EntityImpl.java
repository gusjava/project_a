package a.entity.gus06.file.excel.jxl.viewer.workbook;

import a.framework.*;
import javax.swing.JComponent;
import jxl.Sheet;
import jxl.Workbook;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JPanel;


public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20150511";}


	private Service sheetToJTable;
	private Service tableToPanel;

	private Workbook w;
	private JTabbedPane tabbed;


	public EntityImpl() throws Exception
	{
		sheetToJTable = Outside.service(this,"gus06.file.excel.jxl.sheetjtable.v2");
		tableToPanel = Outside.service(this,"gus06.swing.table.buildpanel1");
		
		tabbed = new JTabbedPane();
	}
	
	
	public Object i() throws Exception
	{return tabbed;}

	
	public Object g() throws Exception
	{return w;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		w = (Workbook) obj;
		tabbed.removeAll();
		if(w!=null) initTabbedPane();
	}
	
	
	private void initTabbedPane() throws Exception
	{	
		int nb = w.getNumberOfSheets();
		for(int i=0;i<nb;i++)
		{
			Sheet sheet = w.getSheet(i);
			String sheetName = sheet.getName();
			JTable table = (JTable) sheetToJTable.t(sheet);
			JPanel p = (JPanel) tableToPanel.t(table);
			tabbed.addTab(sheetName,p);
		}
	}
}
