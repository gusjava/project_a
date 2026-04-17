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

	// commandes todo-knowledge

	private Service addtodoknowledge;
	private Service removetodoknowledge;

	// commandes avec table (create/update/delete/detail-of)

	private Service create;
	private Service update;
	private Service delete;
	private Service detailof;

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

		// commandes todo-knowledge

		addtodoknowledge    = Outside.service(this, "gus.y.server1.engine.cmd.k.addtodoknowledge");
		removetodoknowledge = Outside.service(this, "gus.y.server1.engine.cmd.k.removetodoknowledge");

		// commandes avec table

		create   = Outside.service(this, "gus.y.server1.engine.cmd.k.create");
		update   = Outside.service(this, "gus.y.server1.engine.cmd.k.update");
		delete   = Outside.service(this, "gus.y.server1.engine.cmd.k.delete");
		detailof = Outside.service(this, "gus.y.server1.engine.cmd.k.detailof");
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

		if(cmd.equals("tags-of"))    return tagsof;
		if(cmd.equals("add-tag"))    return addtag;
		if(cmd.equals("remove-tag")) return removetag;

		// commandes link

		if(cmd.equals("links-of"))    return linksof;
		if(cmd.equals("add-link"))    return addlink;
		if(cmd.equals("remove-link")) return removelink;

		// commandes todo-knowledge

		if(cmd.equals("add-todo-knowledge"))    return addtodoknowledge;
		if(cmd.equals("remove-todo-knowledge")) return removetodoknowledge;

		throw new Exception("k: commande inconnue: " + cmd);
	}

	public Object t(Object obj) throws Exception
	{
		Map payload = (Map) obj;
		List cmds = (List) payload.get("cmds");
		Object args = payload.get("args");

		if(cmds.size() < 2) throw new Exception("k: commande manquante");
		String cmd = joinCmds(cmds, 1).toLowerCase();

		// commandes avec table (create/update/delete/detail-of)

		if(cmd.startsWith("create-"))    return create.t(enrichedArgs("create-", cmd, args));
		if(cmd.startsWith("update-"))    return update.t(enrichedArgs("update-", cmd, args));
		if(cmd.startsWith("delete-"))    return delete.t(idArgs("delete-", cmd, args));
		if(cmd.startsWith("detail-of-")) return detailof.t(idArgs("detail-of-", cmd, args));

		return findCmd(cmd).t(args);
	}

	private Map enrichedArgs(String prefix, String cmd, Object args)
	{
		String table = cmd.substring(prefix.length()).replace("-", "_");
		Map p = (args instanceof Map) ? new HashMap((Map) args) : new HashMap();
		p.put("table", table);
		return p;
	}

	private Map idArgs(String prefix, String cmd, Object args)
	{
		String table = cmd.substring(prefix.length()).replace("-", "_");
		List l = (List) args;
		String id = (l != null && !l.isEmpty()) ? (String) l.get(0) : null;
		Map p = new HashMap();
		p.put("table", table);
		p.put("id", id);
		return p;
	}

	private static String joinCmds(List cmds, int from)
	{
		StringBuffer sb = new StringBuffer();
		for(int i = from; i < cmds.size(); i++) {
			if(i > from) sb.append("-");
			sb.append(cmds.get(i));
		}
		return sb.toString();
	}
}
