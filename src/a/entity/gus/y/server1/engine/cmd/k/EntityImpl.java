package a.entity.gus.y.server1.engine.cmd.k;

import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	// commandes simples

	private Service show;
	private Service count;
	private Service tables;
	private Service tags;
	private Service help;
	private Service sql;
	private Service get;
	private Service list;
	private Service search;
	private Service tagsof;
	private Service addtag;
	private Service removetag;
	private Service linksof;
	private Service find;

	// commandes link
	
	private Service add_kk;
	private Service add_tt;
	private Service add_tk;
	private Service add_ka;
	private Service add_ta;
	
	private Service remove_kk;
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
		// commandes simples

		show   = Outside.service(this, "gus.y.server1.engine.cmd.k.n1.sql_columns");
		count  = Outside.service(this, "gus.y.server1.engine.cmd.k.n0.count");
		tables = Outside.service(this, "gus.y.server1.engine.cmd.k.n0.sql_tables");
		tags   = Outside.service(this, "gus.y.server1.engine.cmd.k.n0.tags");
		help   = Outside.service(this, "gus.y.server1.engine.cmd.k.help");
		sql    = Outside.service(this, "gus.y.server1.engine.cmd.k.n1.sql");
		get    = Outside.service(this, "gus.y.server1.engine.cmd.k.get");
		list   = Outside.service(this, "gus.y.server1.engine.cmd.k.list");
		search = Outside.service(this, "gus.y.server1.engine.cmd.k.search");
		tagsof    = Outside.service(this, "gus.y.server1.engine.cmd.k.tagsof");
		addtag    = Outside.service(this, "gus.y.server1.engine.cmd.k.addtag");
		removetag = Outside.service(this, "gus.y.server1.engine.cmd.k.removetag");
		linksof    = Outside.service(this, "gus.y.server1.engine.cmd.k.linksof");
		find     = Outside.service(this, "gus.y.server1.engine.cmd.k.find");
		
		add_kk = Outside.service(this, "gus.y.server1.engine.cmd.k.n3.add_kk");
		add_tt = Outside.service(this, "gus.y.server1.engine.cmd.k.n3.add_tt");
		add_tk = Outside.service(this, "gus.y.server1.engine.cmd.k.n3.add_tk");
		add_ka = Outside.service(this, "gus.y.server1.engine.cmd.k.n2.add_ka");
		add_ta = Outside.service(this, "gus.y.server1.engine.cmd.k.n2.add_ta");

		remove_kk = Outside.service(this, "gus.y.server1.engine.cmd.k.n2.remove_kk");
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
		// commandes simples

		if(cmd.equals("show"))    return show;
		if(cmd.equals("count"))   return count;
		if(cmd.equals("tables"))  return tables;
		if(cmd.equals("tags"))    return tags;
		if(cmd.equals("help"))    return help;
		if(cmd.equals("sql"))     return sql;
		if(cmd.equals("get"))     return get;
		if(cmd.equals("list"))    return list;
		if(cmd.equals("search"))  return search;
		if(cmd.equals("tagsof"))    return tagsof;
		if(cmd.equals("addtag"))    return addtag;
		if(cmd.equals("removetag")) return removetag;
		if(cmd.equals("linksof"))    return linksof;
		if(cmd.equals("find"))     return find;

		if(cmd.equals("add_kk")) return add_kk;
		if(cmd.equals("add_tt")) return add_tt;
		if(cmd.equals("add_tk")) return add_tk;

		if(cmd.equals("remove_kk")) return remove_kk;
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
