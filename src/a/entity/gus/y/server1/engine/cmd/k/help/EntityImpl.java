package a.entity.gus.y.server1.engine.cmd.k.help;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	public Object t(Object obj) throws Exception
	{
		return "k-help                                     \u2014 cette aide\n"
			 + "k-show <table>                             \u2014 colonnes d'une table\n"
			 + "k-count <table>                            \u2014 nombre de lignes\n"
			 + "k-tables                                   \u2014 liste des tables\n"
			 + "k-tags                                     \u2014 tous les tags distincts\n"
			 + "k-sql :<json(sql=<sql>)>                   \u2014 SQL brut sur knowledgedb1\n"
			 + "k-search <table> <field> <value>           \u2014 recherche LIKE sur un champ\n"
			 + "\n"
			 + "k-find_k <id>                              \u2014 trouve un knowledge par id\n"
			 + "k-find_t <id>                              \u2014 trouve un todo par id\n"
			 + "k-find2_k <id>                             \u2014 d\u00e9tail complet knowledge (infos+tags+up+down)\n"
			 + "k-tags_k <id>                              \u2014 tags d'un knowledge\n"
			 + "k-tags_t <id>                              \u2014 tags d'un todo\n"
			 + "k-up_k <id>                                \u2014 liens montants d'un knowledge (linkers)\n"
			 + "k-up_t <id>                                \u2014 liens montants d'un todo\n"
			 + "k-down_k <id>                              \u2014 liens descendants d'un knowledge (linked)\n"
			 + "k-down_t <id>                              \u2014 liens descendants d'un todo\n"
			 + "\n"
			 + "k-create_k :<json>                         \u2014 cr\u00e9e un knowledge (code, action, object, state[, ctxfilename])\n"
			 + "k-create_t :<json>                         \u2014 cr\u00e9e un todo (title, description)\n"
			 + "k-update_k :<json>                         \u2014 met \u00e0 jour un knowledge (id requis)\n"
			 + "k-update_t :<json>                         \u2014 met \u00e0 jour un todo (id requis)\n"
			 + "k-delete_k <id>                            \u2014 supprime un knowledge\n"
			 + "k-delete_t <id>                            \u2014 supprime un todo\n"
			 + "\n"
			 + "k-add_ka <id> <tag>                        \u2014 ajoute un tag \u00e0 un knowledge\n"
			 + "k-add_ta <id> <tag>                        \u2014 ajoute un tag \u00e0 un todo\n"
			 + "k-remove_ka <id> <tag>                     \u2014 supprime un tag d'un knowledge\n"
			 + "k-remove_ta <id> <tag>                     \u2014 supprime un tag d'un todo\n"
			 + "\n"
			 + "k-add_kk <id1> <id2> <type>                \u2014 lien knowledge\u2192knowledge (REQUIRED|OPTIONAL|EXTENDS|...)\n"
			 + "k-add_tt <id1> <id2> <type>                \u2014 lien todo\u2192todo\n"
			 + "k-add_tk <id_todo> <id_knowledge> <type>   \u2014 lien todo\u2192knowledge\n"
			 + "k-remove_kk <id1> <id2>                    \u2014 supprime lien knowledge\u2192knowledge\n"
			 + "k-remove_tt <id1> <id2>                    \u2014 supprime lien todo\u2192todo\n"
			 + "k-remove_tk <id_todo> <id_knowledge>       \u2014 supprime lien todo\u2192knowledge";
	}
}
