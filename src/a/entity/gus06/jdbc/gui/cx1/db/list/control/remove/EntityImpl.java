package a.entity.gus06.jdbc.gui.cx1.db.list.control.remove;

import a.framework.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20231014";}



	private Service drop;
	private Service dialog;

	public EntityImpl() throws Exception
	{
		drop = Outside.service(this,"gus06.jdbc.mysql.perform.db.drop");
		dialog = Outside.service(this,"gus06.input.confirm.dialog");
	}

	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object cx = o[0];
		List selection = (List) o[1];
		
		if(selection==null || selection.isEmpty()) return false;
		
		int nb = selection.size();
		String title = nb>1 ? "Removing "+nb+" databases ?" : "Removing 1 database ?";
		boolean ok = dialog.f(title);
		if(!ok) return false;
		
		for(Object dbName : selection) drop.p(new Object[]{cx,dbName});
		return true;
	}
}