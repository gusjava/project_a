package a.entity.gus06.string.transform.calcul.sum;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141114";}


	private Service stringToTable;


	public EntityImpl() throws Exception
	{stringToTable = Outside.service(this,"gus06.data.string2.stringtotable");}


	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("")) return "";
		
		String[][] table = (String[][]) stringToTable.t(s);
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<table.length;i++)
		b.append(processColumn(table[i])+"\t");
		
		return s+"\n\n"+b;
	}
	
	
	private String processColumn(String[] column)
	{ 
		double sum = 0;
		boolean found = false;
		
		for(int i=0;i<column.length;i++)
		try
		{
		    double d = Double.parseDouble(column[i]);
		    sum += d;
		    found = true;
		}
		catch(Exception e){} 
		return found?""+sum:"NAN";
	}
}
