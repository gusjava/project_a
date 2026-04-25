package a.entity.gus.y.server1.engine.cmd.e.n0.help;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260418";}

	public Object t(Object obj) throws Exception
	{
		return
		"commandes generales:\n" +
		"e-help — cette aide\n" +
		"e-reload — recharge le moteur entity\n" +
		"e-errors [entity] — erreurs de compilation (toutes, ou filtrées par entité)\n" +
		"e-sql <sql> — SQL brut sur entitydb1\n" +
		"\n" +
		"manipulations d'entite:\n" +
		"e-create <entity> [features] — crée le code source d'une nouvelle entité (features : BEFGHIPRSTV, ex: GT)\n" +
		"e-delete <name> — supprime une entité\n" +
		"e-rename <name0> <name1> — renomme une entité (avec refactor des liens)\n" +
		"e-duplicate <name0> <name1> — duplique une entité\n" +
		"e-createtree :<json> — crée un arbre d'entités depuis un JSON [[\"name-features\",[children...]]] (DFS post-order)\n" +
		"e-importsrc :{src} — crée une entité à partir du code source <src>\n" +
		"e-editinsert :{name,pos,src} — insère src à la position pos dans l'entité name\n" +
		"e-editremove :{name,start,end} — supprime les lignes start→end dans l'entité name\n" +
		"e-editreplace :{name,start,end,src} — remplace les lignes start→end par src dans l'entité name\n" +
		"e-editmulti :{name,op:[{start,end,replace},...]} — remplacements multiples dans l'entité name\n" +
		"\n" +
		"informations sur l'entite:\n" +
		"e-src <entity> — affiche le code source de l'entité\n" +
		"e-srcpart <entity> <start> <end> — affiche une section du code source de l'entité comprise entre les positions <start> et <end>\n" +
		"e-ast1 <entity> — AST simplifié — package, imports, class (sans modifiers), méthodes (sans throws ni body)\n" +
		"e-ast2 <entity> — AST complet — comme ast1 + modifiers sur la classe + throws sur les méthodes\n" +
		"e-ast3 <entity> — comme ast2 + body des méthodes\n" +
		"e-path <entity> — retourne le filepath de EntityImpl.java\n" +
		"e-features <entity> — retourne les features implémentées par l'entité\n" +
		"e-creationdate <entity> — retourne la date de création de l'entité\n" +
		"e-hash <entity> — hash sémantique de l'entité (insensible au package, imports, formatage, fins de ligne)\n" +
		"\n" +
		"parcours descendant:\n" +
		"e-downlinks <entity> — liste les entités qui dépendent de <entity>\n" +
		"e-downlinkstree <entity> <maxDeep> — arbre récursif des downlinks jusqu'à la profondeur <maxDeep>\n" +
		"e-downlinkstree2 <entity> <maxDeep> — arbre récursif des downlinks avec descriptions (nom-features)\n" +
		"\n" +
		"parcours montant:\n" +
		"e-uplinks <entity> — liste les dépendances de <entity>\n" +
		"e-uplinkstree <entity> <maxDeep> — arbre récursif des uplinks jusqu'à la profondeur <maxDeep>\n" +
		"e-uplinkstree2 <entity> <maxDeep> — arbre récursif des uplinks avec descriptions (nom-features)\n" +
		"\n" +
		"comptages:\n" +
		"e-count — nombre total d'entités\n" +
		"e-count_st <prefix> — nombre d'entités dont le nom commence par <prefix>\n" +
		"e-count_en <suffix> — nombre d'entités dont le nom se termine par <suffix>\n" +
		"e-count_co <fragment> — nombre d'entités dont le nom contient <fragment>\n" +
		"\n" +
		"nommages:\n" +
		"e-names — liste tous les noms d'entités\n" +
		"e-names_st <prefix> — liste les noms d'entités commençant par <prefix>\n" +
		"e-names_en <suffix> — liste les noms d'entités se terminant par <suffix>\n" +
		"e-names_co <fragment> — liste les noms d'entités contenant <fragment>\n" +
		"\n" +
		"recherche des features:\n" +
		"e-findall_features — toutes les entités avec leurs features\n" +
		"e-findall_features_st <prefix> — entités dont les features commencent par <prefix>\n" +
		"e-findall_features_en <suffix> — entités dont les features se terminent par <suffix>\n" +
		"e-findall_features_co <fragment> — entités dont les features contiennent <fragment>\n" +
		"\n" +
		"recherche des dates de creation:\n" +
		"e-findall_creationdate — toutes les entités avec leur date de création\n" +
		"e-findall_creationdate_st <prefix> — entités dont la date de création commence par <prefix>\n" +
		"e-findall_creationdate_en <suffix> — entités dont la date de création se termine par <suffix>\n" +
		"e-findall_creationdate_co <fragment> — entités dont la date de création contient <fragment>\n" +
		"\n" +
		"recherche des descriptions (name-features):\n" +
		"e-findall_desc — toutes les entités (nom-features) triées par nom\n" +
		"e-findall_desc_st <prefix> — entités dont le nom commence par <prefix> (nom-features)\n" +
		"e-findall_desc_en <suffix> — entités dont le nom se termine par <suffix> (nom-features)\n" +
		"e-findall_desc_co <fragment> — entités dont le nom contient <fragment> (nom-features)\n" +
		"\n" +
		"recherche globale:\n" +
		"e-findall_st <prefix> — liste les entités dont le nom commence par <prefix>\n" +
		"e-findall_en <suffix> — liste les entités dont le nom se termine par <suffix>\n" +
		"e-findall_co <fragment> — liste les entités dont le nom contient <fragment>\n" +
		"\n" +
		"(les actions create, delete, rename, duplicate, createtree, importsrc, edit* sont asynchrones — patienter un instant avant de vérifier)";
	}
}