package a.entity.gus06.jdbc.gui.tableview.perform.column.watcher;

import a.framework.*;
import javax.swing.JTable;
import java.sql.Connection;
import java.util.Map;
import java.awt.Color;
import javax.swing.JLabel;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20221005";}

	public static final String ICONKEY = "eye";

	private Service findCx;
	private Service buildSql;
	private Service sqlQuery;
	private Service toObjectMap;
	private Service mjoin;
	private Service findLabel;
	private Service setIcon;
	private Service showMini;
	private Service cellEdit;
	private Service onDoubleClick;

	public EntityImpl() throws Exception
	{
		findCx = Outside.service(this,"gus06.jdbc.connection.find");
		buildSql = Outside.service(this,"gus06.jdbc.gui.tableview.tool.cell.sql.select");
		sqlQuery = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
		toObjectMap = Outside.service(this,"gus06.jdbc.resultset.next.toobjectmap");
		mjoin = Outside.service(this,"gus06.data.perform.mjoin");
		findLabel = Outside.service(this,"gus06.find.jlabel");
		setIcon = Outside.service(this,"gus06.swing.comp.seticon");
		showMini = Outside.service(this,"gus06.swing.frame.showmini");
		cellEdit = Outside.service(this,"gus06.jdbc.gui.tableview.perform.cell.edit");
		onDoubleClick = Outside.service(this,"gus.x.swing.comp.cust3.on.mousedoubleclicked.execute");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		Object[] data = (Object[]) o[1];
		
		if(table==null || data==null) return false;
		
		Connection cx = (Connection) findCx.t(data[0]);
		String sql = (String) buildSql.t(o);
		
		Editor editor = new Editor(o);
		Provider provider = new Provider(cx,sql);
		
		JLabel label = (JLabel) findLabel.t(provider);
		
		label.setOpaque(true);
		label.setForeground(Color.BLUE);
		label.setBackground(Color.WHITE);
		
		setIcon.p(new Object[]{label,ICONKEY});
		onDoubleClick.p(new Object[]{label,editor});
		showMini.p(label);
		
		return false;
	}
	
	
	private class Provider implements G
	{
		private Connection cx;
		private String sql;
		
		public Provider(Connection cx, String sql)
		{
			this.cx = cx;
			this.sql = sql;
		}
		
		public Object g() throws Exception
		{
			Object rs = sqlQuery.t(new Object[]{cx,sql});
			Map map = (Map) toObjectMap.t(rs);
			return mjoin.t(new Object[]{map,"="," - "});
		}
	}
	
	
	private class Editor implements E
	{
		private Object data;
		
		public Editor(Object data)
		{this.data = data;}
		
		public void e() throws Exception
		{cellEdit.f(data);}
	}
}
