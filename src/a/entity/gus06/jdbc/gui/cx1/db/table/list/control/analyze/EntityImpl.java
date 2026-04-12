package a.entity.gus06.jdbc.gui.cx1.db.table.list.control.analyze;

import a.framework.*;
import java.sql.Connection;
import java.awt.Dimension;
import javax.swing.JComponent;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20230226";}
	
	public static final String TITLE = "TABLE_eye#Analyzing : ";
	public static final Dimension DIM = new Dimension(1000,700);


	private Service show;
	private Service newGui;
	
	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show2");
		newGui = Outside.service(this,"factory#gus06.jdbc.gui.analyze2");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		String tableName = (String) o[2];
		
		Object gui = newGui.g();
		((P) gui).p(obj);
		
		JComponent comp = (JComponent) ((I) gui).i();
		show.p(new Object[]{comp, DIM, TITLE+dbName+"@"+tableName});
		return false;
	}
	
}