package a.entity.gus.y.server1.engine.cmd.k.help;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	public Object t(Object obj) throws Exception
	{
		return "k-show <table>                        \u2014 colonnes d'une table\n"
			 + "k-count <table>                       \u2014 nombre de lignes\n"
			 + "k-tables                              \u2014 liste des tables\n"
			 + "k-get <table> <id>                    \u2014 un enregistrement par id\n"
			 + "k-list <table> [limit]                \u2014 derniers enregistrements (d\u00e9faut 20)\n"
			 + "k-search <table> <field> <value>      \u2014 recherche LIKE sur un champ\n"
			 + "k-sql <sql>                           \u2014 SQL brut sur knowledgedb1\n"
			 + "k-create-knowledge :<json>            \u2014 ins\u00e8re dans knowledge\n"
			 + "k-create-todo :<json>                 \u2014 ins\u00e8re dans todo\n"
			 + "k-create-doc-x :<json>                \u2014 ins\u00e8re dans doc_x\n"
			 + "k-create-doc-y :<json>                \u2014 ins\u00e8re dans doc_y\n"
			 + "k-create-doc-z :<json>                \u2014 ins\u00e8re dans doc_z\n"
			 + "k-create-knowledge-feedback :<json>   \u2014 ins\u00e8re dans knowledge_feedback\n"
			 + "k-update-knowledge :<json>            \u2014 met \u00e0 jour dans knowledge (id requis)\n"
			 + "k-update-todo :<json>                 \u2014 met \u00e0 jour dans todo (id requis)\n"
			 + "k-update-doc-x :<json>                \u2014 met \u00e0 jour dans doc_x (id requis)\n"
			 + "k-update-doc-y :<json>                \u2014 met \u00e0 jour dans doc_y (id requis)\n"
			 + "k-update-doc-z :<json>                \u2014 met \u00e0 jour dans doc_z (id requis)\n"
			 + "k-update-knowledge-feedback :<json>   \u2014 met \u00e0 jour dans knowledge_feedback (id requis)\n"
			 + "k-delete-knowledge <id>               \u2014 supprime dans knowledge\n"
			 + "k-delete-todo <id>                    \u2014 supprime dans todo\n"
			 + "k-delete-doc-x <id>                   \u2014 supprime dans doc_x\n"
			 + "k-delete-doc-y <id>                   \u2014 supprime dans doc_y\n"
			 + "k-delete-doc-z <id>                   \u2014 supprime dans doc_z\n"
			 + "k-delete-knowledge-feedback <id>      \u2014 supprime dans knowledge_feedback\n"
			 + "k-tags                                \u2014 tous les tags distincts (*_tag)\n"
			 + "k-tags-of <table> <id>                \u2014 tags d'un enregistrement\n"
			 + "k-add-tag <table> <id> <tag>          \u2014 ajouter un tag\n"
			 + "k-remove-tag <table> <id> <tag>       \u2014 supprimer un tag\n"
			 + "k-detail-of-knowledge <id>            \u2014 d\u00e9tail complet (data+tags+linkers+linked)\n"
			 + "k-detail-of-todo <id>                 \u2014 d\u00e9tail complet (data+tags+linkers+linked)\n"
			 + "k-detail-of-doc-x <id>                \u2014 d\u00e9tail complet (data+tags)\n"
			 + "k-detail-of-doc-y <id>                \u2014 d\u00e9tail complet (data+tags)\n"
			 + "k-detail-of-doc-z <id>                \u2014 d\u00e9tail complet (data+tags)\n"
			 + "k-detail-of-knowledge-feedback <id>   \u2014 d\u00e9tail complet (data)\n"
			 + "k-links-of <table> <id>               \u2014 liens d'un enregistrement\n"
			 + "k-add-link <table> <id1> <id2> [type] \u2014 cr\u00e9er un lien\n"
			 + "k-remove-link <table> <id1> <id2>     \u2014 supprimer un lien\n"
			 + "k-add-todo-knowledge <id_todo> <id_knowledge> [type] \u2014 lier un todo \u00e0 un knowledge\n"
			 + "k-remove-todo-knowledge <id_todo> <id_knowledge>     \u2014 supprimer ce lien\n"
			 + "k-help                                \u2014 cette aide";
	}
}