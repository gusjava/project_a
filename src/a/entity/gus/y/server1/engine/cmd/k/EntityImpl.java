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

	// commandes tag

	private Service tagsof;
	private Service addtag;
	private Service removetag;

	// commandes link

	private Service linksof;
	private Service addlink;
	private Service removelink;

	// commandes todoknowledge

	private Service addtodoknowledge;
	private Service removetodoknowledge;

	// commandes avec knowledge

	private Service create;
	private Service update;
	private Service delete;
	private Service find;

	public EntityImpl() throws Exception
	{
		// commandes simples

		show   = Outside.service(this, "gus.y.server1.engine.cmd.k.show");
		count  = Outside.service(this, "gus.y.server1.engine.cmd.k.count");
		tables = Outside.service(this, "gus.y.server1.engine.cmd.k.tables");
		tags   = Outside.service(this, "gus.y.server1.engine.cmd.k.tags");
		help   = Outside.service(this, "gus.y.server1.engine.cmd.k.help");
		sql    = Outside.service(this, "gus.y.server1.engine.cmd.k.sql");
		get    = Outside.service(this, "gus.y.server1.engine.cmd.k.get");
		list   = Outside.service(this, "gus.y.server1.engine.cmd.k.list");
		search = Outside.service(this, "gus.y.server1.engine.cmd.k.search");

		// commandes tag

		tagsof    = Outside.service(this, "gus.y.server1.engine.cmd.k.tagsof");
		addtag    = Outside.service(this, "gus.y.server1.engine.cmd.k.addtag");
		removetag = Outside.service(this, "gus.y.server1.engine.cmd.k.removetag");

		// commandes link

		linksof    = Outside.service(this, "gus.y.server1.engine.cmd.k.linksof");
		addlink    = Outside.service(this, "gus.y.server1.engine.cmd.k.addlink");
		removelink = Outside.service(this, "gus.y.server1.engine.cmd.k.removelink");

		// commandes todoknowledge

		addtodoknowledge    = Outside.service(this, "gus.y.server1.engine.cmd.k.addtodoknowledge");
		removetodoknowledge = Outside.service(this, "gus.y.server1.engine.cmd.k.removetodoknowledge");

		// commandes knowledge

		create   = Outside.service(this, "gus.y.server1.engine.cmd.k.create");
		update   = Outside.service(this, "gus.y.server1.engine.cmd.k.update");
		delete   = Outside.service(this, "gus.y.server1.engine.cmd.k.delete");
		find     = Outside.service(this, "gus.y.server1.engine.cmd.k.find");
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

		// commandes tag

		if(cmd.equals("tagsof"))    return tagsof;
		if(cmd.equals("addtag"))    return addtag;
		if(cmd.equals("removetag")) return removetag;

		// commandes link

		if(cmd.equals("linksof"))    return linksof;
		if(cmd.equals("addlink"))    return addlink;
		if(cmd.equals("removelink")) return removelink;

		// commandes todoknowledge

		if(cmd.equals("addtodoknowledge"))    return addtodoknowledge;
		if(cmd.equals("removetodoknowledge")) return removetodoknowledge;

		// commandes knowledge

		if(cmd.equals("create"))   return create;
		if(cmd.equals("update"))   return update;
		if(cmd.equals("delete"))   return delete;
		if(cmd.equals("find"))     return find;

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
