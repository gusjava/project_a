package a.entity.gus.y.entitydb1.jar.findentitynames.bysha1;

import a.framework.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260416";}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		List sha1List = toList(o[1]);

		int nb = sha1List.size();
		if(nb==0) return new ArrayList();

		Set results = new HashSet();
		{
			StringBuilder sql = new StringBuilder("SELECT DISTINCT ei.entity_name");
			sql.append(" FROM jar j");
			sql.append(" JOIN jar_class jc ON jc.jar_sha1 = j.sha1");
			sql.append(" JOIN entity_import ei ON (");
			sql.append("ei.entity_import_wildcard = 0 AND ei.entity_import = jc.jar_class)");
			sql.append(" WHERE j.sha1 IN (");
			for(int i=0;i<nb;i++)
			{
				sql.append("?");
				if(i<nb-1) sql.append(",");
			}
			sql.append(") ORDER BY ei.entity_name");
			
			PreparedStatement st = cx.prepareStatement(sql.toString());
			for(int i=0;i<nb;i++) st.setObject(i+1, sha1List.get(i));
			ResultSet rs = st.executeQuery();
	
			while (rs.next())
			{
				String entityName = rs.getString(1);
				if(entityName!=null) results.add(entityName);
			}
			rs.close();
			st.close();
		}
		{
			StringBuilder sql = new StringBuilder("SELECT DISTINCT ei.entity_name");
			sql.append(" FROM jar j");
			sql.append(" JOIN jar_class jc ON jc.jar_sha1 = j.sha1");
			sql.append(" JOIN entity_import ei ON (");
			sql.append("ei.entity_import_wildcard = 1 AND ei.entity_import_package = jc.jar_class_package)");
			sql.append(" WHERE j.sha1 IN (");
			for(int i=0;i<nb;i++)
			{
				sql.append("?");
				if(i<nb-1) sql.append(",");
			}
			sql.append(") ORDER BY ei.entity_name");
			
			PreparedStatement st = cx.prepareStatement(sql.toString());
			for(int i=0;i<nb;i++) st.setObject(i+1, sha1List.get(i));
			ResultSet rs = st.executeQuery();
	
			while (rs.next())
			{
				String entityName = rs.getString(1);
				if(entityName!=null) results.add(entityName);
			}
			rs.close();
			st.close();
		}
		
		List entityNames = new ArrayList(results);
		Collections.sort(entityNames);
		return entityNames;
	}
	
	private List toList(Object obj) throws Exception
	{
		if(obj==null) return new ArrayList();
		if(obj instanceof List) return (List) obj;
		if(obj instanceof Set) return new ArrayList((Set) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
