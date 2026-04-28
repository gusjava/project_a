package a.entity.gus.y.server1.engine.cmd.k;

import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service help;
	private Service show;
	private Service count;
	private Service tables;
	private Service tags;
	private Service sql;
	private Service search;
	
	private Service tags_k;
	private Service tags_t;
	
	private Service up_k;
	private Service up_t;
	
	private Service down_k;
	private Service down_t;
	
	private Service find_k;
	private Service find_t;
	
	private Service find2_k;
	
	private Service add_kk;
	private Service add_tt;
	private Service add_tk;
	
	private Service add_ka;
	private Service add_ta;
	
	private Service remove_ka;
	private Service remove_kk;
	private Service remove_ta;
	private Service remove_tt;
	private Service remove_tk;

	private Service create_k;
	private Service create_t;
	
	private Service update_k;
	private Service update_t;
	
	private Service delete_k;
	private Service delete_t;

	public EntityImpl() throws Exception
	{
		help   = Outside.service(this, "gus.y.server1.engine.cmd.k.help");
		show   = Outside.service(this, "gus.y.server1.engine.cmd.k.n1.sql_columns");
		count  = Outside.service(this, "gus.y.server1.engine.cmd.k.n0.count");
		tables = Outside.service(this, "gus.y.server1.engine.cmd.k.n0.sql_tables");
		tags   = Outside.service(this, "gus.y.server1.engine.cmd.k.n0.tags");
		sql    = Outside.service(this, "gus.y.server1.engine.cmd.k.nj.sql");
		search = Outside.service(this, "gus.y.server1.engine.cmd.k.search");
		
		find_k = Outside.service(this, "gus.y.server1.engine.cmd.k.n1.find_k");
		find_t = Outside.service(this, "gus.y.server1.engine.cmd.k.n1.find_t");
		
		find2_k = Outside.service(this, "gus.y.server1.engine.cmd.k.n1.find2_k");
		
		tags_k = Outside.service(this, "gus.y.server1.engine.cmd.k.n1.tags_k");
		tags_t = Outside.service(this, "gus.y.server1.engine.cmd.k.n1.tags_t");
		
		up_k = Outside.service(this, "gus.y.server1.engine.cmd.k.n1.up_k");
		up_t = Outside.service(this, "gus.y.server1.engine.cmd.k.n1.up_t");
		
		down_k = Outside.service(this, "gus.y.server1.engine.cmd.k.n1.down_k");
		down_t = Outside.service(this, "gus.y.server1.engine.cmd.k.n1.down_t");
		
		add_kk = Outside.service(this, "gus.y.server1.engine.cmd.k.n3.add_kk");
		add_tt = Outside.service(this, "gus.y.server1.engine.cmd.k.n3.add_tt");
		add_tk = Outside.service(this, "gus.y.server1.engine.cmd.k.n3.add_tk");
		
		add_ka = Outside.service(this, "gus.y.server1.engine.cmd.k.n2.add_ka");
		add_ta = Outside.service(this, "gus.y.server1.engine.cmd.k.n2.add_ta");

		remove_ka = Outside.service(this, "gus.y.server1.engine.cmd.k.n2.remove_ka");
		remove_kk = Outside.service(this, "gus.y.server1.engine.cmd.k.n2.remove_kk");
		remove_ta = Outside.service(this, "gus.y.server1.engine.cmd.k.n2.remove_ta");
		remove_tt = Outside.service(this, "gus.y.server1.engine.cmd.k.n2.remove_tt");
		remove_tk = Outside.service(this, "gus.y.server1.engine.cmd.k.n2.remove_tk");

		create_k = Outside.service(this, "gus.y.server1.engine.cmd.k.nj.create_k");
		create_t = Outside.service(this, "gus.y.server1.engine.cmd.k.nj.create_t");
		
		update_k = Outside.service(this, "gus.y.server1.engine.cmd.k.nj.update_k");
		update_t = Outside.service(this, "gus.y.server1.engine.cmd.k.nj.update_t");
		
		delete_k = Outside.service(this, "gus.y.server1.engine.cmd.k.n1.delete_k");
		delete_t = Outside.service(this, "gus.y.server1.engine.cmd.k.n1.delete_t");
	}

	private Service findCmd(String cmd) throws Exception
	{
		if(cmd.equals("help"))    return help;
		if(cmd.equals("show"))    return show;
		if(cmd.equals("count"))   return count;
		if(cmd.equals("tables"))  return tables;
		if(cmd.equals("tags"))    return tags;
		if(cmd.equals("sql"))     return sql;
		if(cmd.equals("search"))  return search;
		
		if(cmd.equals("find_k")) return find_k;
		if(cmd.equals("find_t")) return find_t;
		
		if(cmd.equals("find2_k")) return find2_k;
		
		if(cmd.equals("tags_k")) return tags_k;
		if(cmd.equals("tags_t")) return tags_t;

		if(cmd.equals("up_k")) return up_k;
		if(cmd.equals("up_t")) return up_t;

		if(cmd.equals("down_k")) return down_k;
		if(cmd.equals("down_t")) return down_t;

		if(cmd.equals("add_kk")) return add_kk;
		if(cmd.equals("add_tt")) return add_tt;
		if(cmd.equals("add_tk")) return add_tk;
		
		if(cmd.equals("add_ka")) return add_ka;
		if(cmd.equals("add_ta")) return add_ta;

		if(cmd.equals("remove_ka")) return remove_ka;
		if(cmd.equals("remove_kk")) return remove_kk;
		if(cmd.equals("remove_ta")) return remove_ta;
		if(cmd.equals("remove_tt")) return remove_tt;
		if(cmd.equals("remove_tk")) return remove_tk;

		if(cmd.equals("create_k"))   return create_k;
		if(cmd.equals("create_t"))   return create_t;
		
		if(cmd.equals("update_k"))   return update_k;
		if(cmd.equals("update_t"))   return update_t;
		
		if(cmd.equals("delete_k"))   return delete_k;
		if(cmd.equals("delete_t"))   return delete_t;

		throw new Exception("commande inconnue: " + cmd);
	}

	public Object t(Object obj) throws Exception
	{
		Map payload = (Map) obj;
		List cmds = (List) payload.get("cmds");
		Object args = payload.get("args");

		if(cmds.size() != 2) throw new Exception("Incorrect cmd number: "+cmds.size());
		String cmd = (String) cmds.get(1);

		return findCmd(cmd).t(args);
	}
}
