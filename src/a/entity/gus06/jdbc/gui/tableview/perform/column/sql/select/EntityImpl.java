package a.entity.gus06.jdbc.gui.tableview.perform.column.sql.select;

import a.framework.*;
import javax.swing.JTable;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20221003";}


	private Service buildSql;
	private Service clipboard;

	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.gui.tableview.tool.column.sql.select");
		clipboard = Outside.service(this,"gus.x.clipboard.string");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		Object[] data = (Object[]) o[1];
		
		String sql = (String) buildSql.t(new Object[]{table,data});
		if(sql!=null) clipboard.p(sql);
		return false;
	}
}