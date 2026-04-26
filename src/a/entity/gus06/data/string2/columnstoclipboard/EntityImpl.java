package a.entity.gus06.data.string2.columnstoclipboard;

import a.framework.*;
import javax.swing.JFrame;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150702";}


	private Service toClipboard;
	private JFrame mainFrame;
	private JDialogColumnSelector dialog;
	

	public EntityImpl() throws Exception
	{
		toClipboard = Outside.service(this,"gus.x.clipboard.string");
		mainFrame = (JFrame) Outside.resource(this,"mainframe");
		dialog = new JDialogColumnSelector(mainFrame);
	}



	public void p(Object obj) throws Exception
	{
		if(obj instanceof String[][])
		{
			String[][] table = (String[][]) obj;
			perform(table);
			return;
		}
		if(obj instanceof Object[])
		{
			Object[] info = (Object[]) obj;
			if(info.length!=2) throw new Exception("wrong info size: "+info.length);
			String[][] table = (String[][]) info[0];
			String[] columns = (String[]) info[1];
			perform(table,columns);
			return;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	
	private void perform(String[][] table, String[] columns) throws Exception
	{
		if(table.length==0) return;
		int n = table[0].length;
		
		if(columns.length!=n)
			throw new Exception("Invalid column number: "+columns.length);
		
		dialog.updateGui(n,columns);
		dialog.setVisible(true);
		
		boolean[] selected = dialog.getSelected();
		if(selected==null) return;
		
		String rep = buildRepresentation(table,selected);
		toClipboard(rep);
	}
	
	
	
	
	private void perform(String[][] table)
	{
		if(table.length==0) return;
		int n = table[0].length;
		
		dialog.updateGui(n);
		dialog.setVisible(true);
		
		boolean[] selected = dialog.getSelected();
		if(selected==null) return;
		
		String rep = buildRepresentation(table,selected);
		toClipboard(rep);
	}


	
	
	
	private String buildRepresentation(String[][] table, boolean[] selected)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<table.length;i++)
		{
			for(int j=0;j<selected.length;j++)
			{
				if(selected[j])
				{
					b.append(table[i][j]);
					if(j<selected.length-1) b.append("\t");
				}
			}
			if(i<table.length-1) b.append("\n");
		}
		return b.toString();
	}
	
	
	
	
	
	
	private void toClipboard(String text)
	{
		try{toClipboard.p(text);}
		catch(Exception e)
		{Outside.err(this,"toClipboard(String)",e);}
	}
}
