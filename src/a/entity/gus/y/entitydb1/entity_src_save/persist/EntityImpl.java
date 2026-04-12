package a.entity.gus.y.entitydb1.entity_src_save.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240721";}
	
	private Service count;
	private Service insert;
	private Service update;

	public EntityImpl() throws Exception {
		count = Outside.service(this,"gus.y.entitydb1.entity_src_save.count");
		insert = Outside.service(this,"gus.y.entitydb1.entity_src_save.insert");
		update = Outside.service(this,"gus.y.entitydb1.entity_src_save.update");
	}

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 4)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];
		String fileName = (String) o[2];
		String src = (String) o[3];
		
		Integer c = (Integer) count.t(new Object[] {cx, entityName, fileName});
		if(c>0) update.p(new Object[] {cx, entityName, fileName, src});
		else insert.p(new Object[] {cx, entityName, fileName, src});
	}

}
