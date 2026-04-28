package a.entity.gus.y.server1.engine.cmd.r;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	// commandes generales

	private Service help;
	private Service sql;
	private Service tables;
	private Service tags;

	// lecture

	private Service show;
	private Service count;
	private Service get;
	private Service list;
	private Service search;

	// create

	private Service createobjective;
	private Service createtask;
	private Service createnote;
	private Service createsprint;
	private Service createsprintentry;

	// update

	private Service updateobjective;
	private Service updatetask;
	private Service updatenote;
	private Service updatesprint;
	private Service updatesprintentry;

	// delete

	private Service deleteobjective;
	private Service deletetask;
	private Service deletenote;
	private Service deletesprint;
	private Service deletesprintentry;

	// detail-of

	private Service detailofnote;
	private Service detailofobjective;
	private Service detailoftask;
	private Service detailofsprint;
	private Service detailofsprintentry;

	// tags

	private Service tagsof;
	private Service addtag;
	private Service removetag;

	public EntityImpl() throws Exception
	{
		// commandes generales
		help    = Outside.service(this, "gus.y.server1.engine.cmd.r.help");
		sql     = Outside.service(this, "gus.y.server1.engine.cmd.r.nj.sql");
		tables  = Outside.service(this, "gus.y.server1.engine.cmd.r.tables");
		tags    = Outside.service(this, "gus.y.server1.engine.cmd.r.tags");

		// lecture
		show    = Outside.service(this, "gus.y.server1.engine.cmd.r.show");
		count   = Outside.service(this, "gus.y.server1.engine.cmd.r.count");
		get     = Outside.service(this, "gus.y.server1.engine.cmd.r.get");
		list    = Outside.service(this, "gus.y.server1.engine.cmd.r.list");
		search  = Outside.service(this, "gus.y.server1.engine.cmd.r.search");

		// create
		createobjective   = Outside.service(this, "gus.y.server1.engine.cmd.r.createobjective");
		createtask        = Outside.service(this, "gus.y.server1.engine.cmd.r.createtask");
		createnote        = Outside.service(this, "gus.y.server1.engine.cmd.r.createnote");
		createsprint      = Outside.service(this, "gus.y.server1.engine.cmd.r.createsprint");
		createsprintentry = Outside.service(this, "gus.y.server1.engine.cmd.r.createsprintentry");

		// update
		updateobjective   = Outside.service(this, "gus.y.server1.engine.cmd.r.updateobjective");
		updatetask        = Outside.service(this, "gus.y.server1.engine.cmd.r.updatetask");
		updatenote        = Outside.service(this, "gus.y.server1.engine.cmd.r.updatenote");
		updatesprint      = Outside.service(this, "gus.y.server1.engine.cmd.r.updatesprint");
		updatesprintentry = Outside.service(this, "gus.y.server1.engine.cmd.r.updatesprintentry");

		// delete
		deleteobjective   = Outside.service(this, "gus.y.server1.engine.cmd.r.deleteobjective");
		deletetask        = Outside.service(this, "gus.y.server1.engine.cmd.r.deletetask");
		deletenote        = Outside.service(this, "gus.y.server1.engine.cmd.r.deletenote");
		deletesprint      = Outside.service(this, "gus.y.server1.engine.cmd.r.deletesprint");
		deletesprintentry = Outside.service(this, "gus.y.server1.engine.cmd.r.deletesprintentry");

		// detail-of
		detailofnote        = Outside.service(this, "gus.y.server1.engine.cmd.r.detailofnote");
		detailofobjective   = Outside.service(this, "gus.y.server1.engine.cmd.r.detailofobjective");
		detailoftask        = Outside.service(this, "gus.y.server1.engine.cmd.r.detailoftask");
		detailofsprint      = Outside.service(this, "gus.y.server1.engine.cmd.r.detailofsprint");
		detailofsprintentry = Outside.service(this, "gus.y.server1.engine.cmd.r.detailofsprintentry");

		// tags
		tagsof    = Outside.service(this, "gus.y.server1.engine.cmd.r.tagsof");
		addtag    = Outside.service(this, "gus.y.server1.engine.cmd.r.addtag");
		removetag = Outside.service(this, "gus.y.server1.engine.cmd.r.removetag");
	}

	public Object t(Object obj) throws Exception
	{
		Map payload = (Map) obj;
		List cmds = (List) payload.get("cmds");
		Object args = payload.get("args");

		if(cmds.size() != 2) throw new Exception("r: incorrect cmd number: " + cmds.size());
		String cmd = (String) cmds.get(1);

		// commandes generales
		if(cmd.equals("help"))   return help.t(args);
		if(cmd.equals("sql"))    return sql.t(args);
		if(cmd.equals("tables")) return tables.t(args);
		if(cmd.equals("tags"))   return tags.t(args);

		// lecture
		if(cmd.equals("show"))   return show.t(args);
		if(cmd.equals("count"))  return count.t(args);
		if(cmd.equals("get"))    return get.t(args);
		if(cmd.equals("list"))   return list.t(args);
		if(cmd.equals("search")) return search.t(args);

		// create
		if(cmd.equals("createobjective"))   return createobjective.t(args);
		if(cmd.equals("createtask"))        return createtask.t(args);
		if(cmd.equals("createnote"))        return createnote.t(args);
		if(cmd.equals("createsprint"))      return createsprint.t(args);
		if(cmd.equals("createsprintentry")) return createsprintentry.t(args);

		// update
		if(cmd.equals("updateobjective"))   return updateobjective.t(args);
		if(cmd.equals("updatetask"))        return updatetask.t(args);
		if(cmd.equals("updatenote"))        return updatenote.t(args);
		if(cmd.equals("updatesprint"))      return updatesprint.t(args);
		if(cmd.equals("updatesprintentry")) return updatesprintentry.t(args);

		// delete
		if(cmd.equals("deleteobjective"))   return deleteobjective.t(args);
		if(cmd.equals("deletetask"))        return deletetask.t(args);
		if(cmd.equals("deletenote"))        return deletenote.t(args);
		if(cmd.equals("deletesprint"))      return deletesprint.t(args);
		if(cmd.equals("deletesprintentry")) return deletesprintentry.t(args);

		// detail-of
		if(cmd.equals("detailofnote"))        return detailofnote.t(args);
		if(cmd.equals("detailofobjective"))   return detailofobjective.t(args);
		if(cmd.equals("detailoftask"))        return detailoftask.t(args);
		if(cmd.equals("detailofsprint"))      return detailofsprint.t(args);
		if(cmd.equals("detailofsprintentry")) return detailofsprintentry.t(args);

		// tags
		if(cmd.equals("tagsof"))    return tagsof.t(args);
		if(cmd.equals("addtag"))    return addtag.t(args);
		if(cmd.equals("removetag")) return removetag.t(args);

		throw new Exception("r: commande inconnue: " + cmd);
	}
}