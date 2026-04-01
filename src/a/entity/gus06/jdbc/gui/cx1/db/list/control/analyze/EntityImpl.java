package a.entity.gus06.jdbc.gui.cx1.db.list.control.analyze;

import a.framework.*;
import java.sql.Connection;
import java.awt.Dimension;
import javax.swing.JComponent;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20230223";}
	
	public static final String TITLE = "BASE_eye#Analyzing : ";
	public static final Dimension DIM = new Dimension(1000,700);


	private Service show;
	private Service newGui;
	
	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show2");
		newGui = Outside.service(this,"factory#gus.jdbc.gui.analyze1");
	}
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		List selection = (List) o[1];
		
		if(selection==null || selection.isEmpty()) return false;
		String dbName = (String) selection.get(0);
		
		Object gui = newGui.g();
		((P) gui).p(new Object[]{cx, dbName});
		
		JComponent comp = (JComponent) ((I) gui).i();
		show.p(new Object[]{comp, DIM, TITLE+dbName});
		return false;
	}
	
}
