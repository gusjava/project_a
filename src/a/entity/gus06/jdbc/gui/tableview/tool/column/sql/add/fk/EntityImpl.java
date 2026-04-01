package a.entity.gus06.jdbc.gui.tableview.tool.column.sql.add.fk;

import a.framework.*;
import javax.swing.JTable;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221007";}


	private Service format;
	private Service random;
	
	public EntityImpl() throws Exception
	{
		format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");
		random = Outside.service(this,"gus06.data.generate.string.random.number10");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		Object[] data = (Object[]) o[1];
		
		if(table==null || data==null) return false;
		
		String dbName = (String) data[1];
		String tableName = (String) data[2];
		
		String path = dbName+"."+tableName;
		
		String column1 = findColumn(table);
		if(column1==null) return false;
		if(!column1.endsWith("_id")) return false;
		
		String fkName = "fk_"+tableName+"_"+column1;
		String refTable = column1.substring(0,column1.length()-3);
		String column2 = "id";
		
		return "ALTER TABLE "+format(tableName)+" ADD CONSTRAINT "+fkName+" FOREIGN KEY ("+format(column1)+") REFERENCES "+format(refTable)+"("+format(column2)+");";
	}
	
	
	private String findColumn(JTable table)
	{
		int[] columns = table.getSelectedColumns();
		if(columns==null || columns.length!=1) return null;
		return table.getColumnName(columns[0]);
	}

	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
