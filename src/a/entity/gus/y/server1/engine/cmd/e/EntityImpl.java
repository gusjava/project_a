package a.entity.gus.y.server1.engine.cmd.e;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260422";}

	// commandes generales
	
	private Service help;
	private Service reload;
	private Service errors;
	private Service sql;
	
	// manipulations d'entite
	
	private Service create;
	private Service delete;
	private Service rename;
	private Service duplicate;
	private Service createtree;
	private Service importsrc;
	private Service editinsert;
	private Service editremove;
	private Service editreplace;
	private Service editmulti;
	
	// informations sur l'entite
	
	private Service ast1;
	private Service ast2;
	private Service ast3;
	private Service src;
	private Service srcpart;
	private Service path;
	private Service features;
	private Service creationdate;
	private Service hash;
	
	// calculs sur l'entite
	
	private Service computeHash;
	
	// parcours descendant
	
	private Service downlinks;
	private Service downlinkstree;
	private Service downlinkstree2;
	
	// parcours montant
	
	private Service uplinks;
	private Service uplinkstree;
	private Service uplinkstree2;
	
	// comptages
	
	private Service count;
	private Service count_st;
	private Service count_en;
	private Service count_co;
	private Service count_import;
	private Service count_import_co;
	
	// nommages

	private Service names;
	private Service names_st;
	private Service names_en;
	private Service names_co;
	private Service names_import;
	private Service names_import_co;


	// imports

	private Service imports;
	
	// recherche des features
	
	private Service featuresbyname;
	private Service featuresbyname_st;
	private Service featuresbyname_en;
	private Service featuresbyname_co;
	
	// recherche des dates de creation
	
	private Service creationdatebyname;
	private Service creationdatebyname_st;
	private Service creationdatebyname_en;
	private Service creationdatebyname_co;
	
	// recherche des dates de descriptions (name-features)
	
	private Service descs;
	private Service descs_st;
	private Service descs_en;
	private Service descs_co;
	
	// recherche globale
	
	private Service infosbyname_st;
	private Service infosbyname_en;
	private Service infosbyname_co;
	
	private Service countbyhash_multi;
	
	
	public EntityImpl() throws Exception
	{
		// commandes generales
		
		help       = Outside.service(this, "gus.y.server1.engine.cmd.e.n0.help");
		reload     = Outside.service(this, "gus.y.server1.engine.cmd.e.n0.reload");
		errors     = Outside.service(this, "gus.y.server1.engine.cmd.e.n01.errors");
		sql        = Outside.service(this, "gus.y.server1.engine.cmd.e.nj.sql");
		
		// manipulations d'entite
		
		create     = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.create");
		delete     = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.delete");
		rename     = Outside.service(this, "gus.y.server1.engine.cmd.e.n2.rename");
		duplicate  = Outside.service(this, "gus.y.server1.engine.cmd.e.n2.duplicate");
		createtree  = Outside.service(this, "gus.y.server1.engine.cmd.e.nr.createtree");
		importsrc   = Outside.service(this, "gus.y.server1.engine.cmd.e.nj.importsrc");
		editinsert  = Outside.service(this, "gus.y.server1.engine.cmd.e.nj.editinsert");
		editremove  = Outside.service(this, "gus.y.server1.engine.cmd.e.nj.editremove");
		editreplace = Outside.service(this, "gus.y.server1.engine.cmd.e.nj.editreplace");
		editmulti = Outside.service(this, "gus.y.server1.engine.cmd.e.nj.editmulti");
		
		// informations sur l'entite
		
		ast1        = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.ast1");
		ast2        = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.ast2");
		ast3        = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.ast3");
		src         = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.src");
		srcpart     = Outside.service(this, "gus.y.server1.engine.cmd.e.n3.srcpart");
		path        = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.path");
		features    = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.features_w_name");
		creationdate = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.creationdate_w_name");
		hash        = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.hash_w_name");
		
		// calculs sur l'entite
		
		computeHash = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.computehash");
		
		// parcours descendant
		
		downlinks      = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.downlinks");
		downlinkstree  = Outside.service(this, "gus.y.server1.engine.cmd.e.n2.downlinkstree");
		downlinkstree2 = Outside.service(this, "gus.y.server1.engine.cmd.e.n2.downlinkstree2");
		
		// parcours montant
		
		uplinks        = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.uplinks");
		uplinkstree    = Outside.service(this, "gus.y.server1.engine.cmd.e.n2.uplinkstree");
		uplinkstree2   = Outside.service(this, "gus.y.server1.engine.cmd.e.n2.uplinkstree2");
		
		// n0
		
		count       = Outside.service(this, "gus.y.server1.engine.cmd.e.n0.count");
		names       = Outside.service(this, "gus.y.server1.engine.cmd.e.n0.names");
		
		// n1
		
		count_st    = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.count_w_st");
		count_en    = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.count_w_en");
		count_co    = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.count_w_co");
		count_import = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.count_w_import");
		count_import_co = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.count_w_import_co");
		
		names_st    = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.names_w_st");
		names_en    = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.names_w_en");
		names_co    = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.names_w_co");
		names_import    = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.names_w_import");
		names_import_co = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.names_w_import_co");

		imports       = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.imports_w_name");
		
		featuresbyname        = Outside.service(this, "gus.y.server1.engine.cmd.e.n0.featuresbyname");
		featuresbyname_st     = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.featuresbyname_w_st");
		featuresbyname_en     = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.featuresbyname_w_en");
		featuresbyname_co     = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.featuresbyname_w_co");
		
		creationdatebyname    = Outside.service(this, "gus.y.server1.engine.cmd.e.n0.creationdatebyname");
		creationdatebyname_st = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.creationdatebyname_w_st");
		creationdatebyname_en = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.creationdatebyname_w_en");
		creationdatebyname_co = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.creationdatebyname_w_co");
		
		descs            = Outside.service(this, "gus.y.server1.engine.cmd.e.n0.descs");
		descs_st         = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.descs_w_st");
		descs_en         = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.descs_w_en");
		descs_co         = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.descs_w_co");
		
		//infosbyname (trop dangereux)
		infosbyname_st  = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.infosbyname_w_st");
		infosbyname_en  = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.infosbyname_w_en");
		infosbyname_co  = Outside.service(this, "gus.y.server1.engine.cmd.e.n1.infosbyname_w_co");
		
		countbyhash_multi = Outside.service(this, "gus.y.server1.engine.cmd.e.n0.countbyhash_multi");
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

	private Service findCmd(String cmd) throws Exception
	{
		// commandes generales

		if(cmd.equals("help"))             return help;
		if(cmd.equals("reload"))           return reload;
		if(cmd.equals("sql"))              return sql;
		if(cmd.equals("errors"))           return errors;

		// manipulations d'entite

		if(cmd.equals("create"))           return create;
		if(cmd.equals("delete"))           return delete;
		if(cmd.equals("rename"))           return rename;
		if(cmd.equals("duplicate"))        return duplicate;
		if(cmd.equals("createtree"))       return createtree;
		if(cmd.equals("importsrc"))        return importsrc;
		if(cmd.equals("editinsert"))       return editinsert;
		if(cmd.equals("editremove"))       return editremove;
		if(cmd.equals("editreplace"))      return editreplace;
		if(cmd.equals("editmulti"))        return editmulti;

		// informations sur l'entite

		if(cmd.equals("ast1"))             return ast1;
		if(cmd.equals("ast2"))             return ast2;
		if(cmd.equals("ast3"))             return ast3;
		if(cmd.equals("src"))              return src;
		if(cmd.equals("srcpart"))          return srcpart;
		if(cmd.equals("path"))             return path;
		if(cmd.equals("features"))         return features;
		if(cmd.equals("creationdate"))     return creationdate;
		if(cmd.equals("hash"))             return hash;

		// calculs sur l'entite
		
		if(cmd.equals("computehash"))      return computeHash;

		// parcours descendant

		if(cmd.equals("downlinks"))        return downlinks;
		if(cmd.equals("downlinkstree"))    return downlinkstree;
		if(cmd.equals("downlinkstree2"))   return downlinkstree2;

		// parcours montant

		if(cmd.equals("uplinks"))          return uplinks;
		if(cmd.equals("uplinkstree"))      return uplinkstree;
		if(cmd.equals("uplinkstree2"))     return uplinkstree2;

		// comptages

		if(cmd.equals("count"))            return count;
		if(cmd.equals("count_st"))         return count_st;
		if(cmd.equals("count_en"))         return count_en;
		if(cmd.equals("count_co"))         return count_co;
		if(cmd.equals("count_import")) return count_import;
		if(cmd.equals("count_import_co")) return count_import_co;

		// nommages

		if(cmd.equals("names"))            return names;
		if(cmd.equals("names_st"))         return names_st;
		if(cmd.equals("names_en"))         return names_en;
		if(cmd.equals("names_co"))         return names_co;

		// imports

		if(cmd.equals("imports"))          return imports;
		if(cmd.equals("names_import"))       return names_import;
		if(cmd.equals("names_import_co"))    return names_import_co;

		// recherche des features

		if(cmd.equals("featuresbyname"))        return featuresbyname;
		if(cmd.equals("featuresbyname_st"))     return featuresbyname_st;
		if(cmd.equals("featuresbyname_en"))     return featuresbyname_en;
		if(cmd.equals("featuresbyname_co"))     return featuresbyname_co;

		// recherche des dates de creation

		if(cmd.equals("creationdatebyname"))    return creationdatebyname;
		if(cmd.equals("creationdatebyname_st")) return creationdatebyname_st;
		if(cmd.equals("creationdatebyname_en")) return creationdatebyname_en;
		if(cmd.equals("creationdatebyname_co")) return creationdatebyname_co;

		// recherche des dates de descriptions (name-features)

		if(cmd.equals("descs"))            return descs;
		if(cmd.equals("descs_st"))         return descs_st;
		if(cmd.equals("descs_en"))         return descs_en;
		if(cmd.equals("descs_co"))         return descs_co;

		// recherche globale

		if(cmd.equals("infosbyname_st"))       return infosbyname_st;
		if(cmd.equals("infosbyname_en"))       return infosbyname_en;
		if(cmd.equals("infosbyname_co"))       return infosbyname_co;
		
		if(cmd.equals("countbyhash_multi"))       return countbyhash_multi;

		throw new Exception("e: commande inconnue: " + cmd);
	}
}