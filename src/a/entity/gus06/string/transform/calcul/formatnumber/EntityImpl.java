package a.entity.gus06.string.transform.calcul.formatnumber;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}


	private Service stringToTable;
	private Service tableToString;


	public EntityImpl() throws Exception
	{
		stringToTable = Outside.service(this,"gus06.data.string2.stringtotable");
		tableToString = Outside.service(this,"gus06.data.string2.tabletostring");
	}


	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("")) return "";
		
		String[][] table = (String[][]) stringToTable.t(s);
		for(int i=0;i<table.length;i++)
		table[i] = processColumn(table[i]);
		
		return tableToString.t(table);
	}
	
	
	
	
	private String[] processColumn(String[] column)
	{ 
		try
		{
			Double.parseDouble(column[0]);
			String[] n = column[0].split("\\.");
			
			int length1 = n[0].length();
			int length2 = n.length>1?n[1].length():0;
			
			String[] newColumn = new String[column.length];
			for(int i=0;i<column.length;i++)
			newColumn[i] = format(column[i],length1,length2);
			
			return newColumn;
		}
		catch(Exception e)
		{return column;}
	}
	
	
	
	
	
	private String format(String number, int length1, int length2)
	{
		String[] n = number.split("\\.");
		if(n.length==0) return number;
		
		String integer = n[0];
		while(integer.length()<length1) integer = '0'+integer;
		
		if(length2==0) return integer;
		
		String decimal = n.length>1?n[1]:"";
		while(decimal.length()<length2) decimal = decimal+'0';
		
		return integer+"."+decimal.substring(0,length2);
	}
}
