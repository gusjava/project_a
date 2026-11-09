package a.entity.gus06.string.transform.httpcode.desc.fr;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190715";}


	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();

		put("100","Attente de la suite de la requête.");
		put("101","Acceptation du changement de protocole.");
		put("102","WebDAV RFC 2518 [ 3 ] : Traitement en cours (évite que le client dépasse le temps d’attente limite).");
		put("103","RFC 8297 [ 4 ] : (Expérimental) Dans l'attente de la réponse définitive, le serveur retourne des liens que le client peut commencer à télécharger.");
		put("200","Requête traitée avec succès. La réponse dépendra de la méthode de requête utilisée.");
		put("201","Requête traitée avec succès et création d’un document.");
		put("202","Requête traitée, mais sans garantie de résultat.");
		put("203","Information retournée, mais générée par une source non certifiée.");
		put("204","Requête traitée avec succès mais pas d’information à renvoyer.");
		put("205","Requête traitée avec succès, la page courante peut être effacée.");
		put("206","Une partie seulement de la ressource a été transmise.");
		put("207","WebDAV : Réponse multiple.");
		put("208","WebDAV : Le document a été envoyé précédemment dans cette collection.");
		put("210","WebDAV : La copie de la ressource côté client diffère de celle du serveur (contenu ou propriétés).");
		put("226","RFC 3229 [ 5 ] : Le serveur a accompli la requête pour la ressource, et la réponse est une représentation du résultat d'une ou plusieurs manipulations d'instances appliquées à l'instance actuelle.");
		put("300","L’ URI demandée se rapporte à plusieurs ressources.");
		put("301","Document déplacé de façon permanente.");
		put("302","Document déplacé de façon temporaire.");
		put("303","La réponse à cette requête est ailleurs.");
		put("304","Document non modifié depuis la dernière requête.");
		put("305","La requête doit être ré-adressée au proxy .");
		put("306","Code utilisé par une ancienne version de la RFC 2616 [ 6 ] , à présent réservé. Elle signifiait \"Les requêtes suivantes doivent utiliser le proxy spécifié\" [ 7 ] .");
		put("307","La requête doit être redirigée temporairement vers l’ URI spécifiée.");
		put("308","La requête doit être redirigée définitivement vers l’ URI spécifiée.");
		put("310","La requête doit être redirigée de trop nombreuses fois, ou est victime d’une boucle de redirection.");
		put("400","La syntaxe de la requête est erronée.");
		put("401","Une authentification est nécessaire pour accéder à la ressource.");
		put("402","Paiement requis pour accéder à la ressource.");
		put("403","Le serveur a compris la requête, mais refuse de l'exécuter. Contrairement à l' erreur 401 , s'authentifier ne fera aucune différence. Sur les serveurs où l'authentification est requise, cela signifie généralement que l'authentification a été acceptée mais que les droits d'accès ne permettent pas au client d'accéder à la ressource.");
		put("404","Ressource non trouvée.");
		put("405","Méthode de requête non autorisée.");
		put("406","La ressource demandée n'est pas disponible dans un format qui respecterait les en-têtes \"Accept\" de la requête.");
		put("407","Accès à la ressource autorisé par identification avec le proxy.");
		put("408","Temps d’attente d’une requête du client, écoulé côté serveur. D'après les spécifications HTTP: \"Le client n'a pas produit de requête dans le délai que le serveur était prêt à attendre. Le client PEUT répéter la demande sans modifications à tout moment ultérieur.\" [ 8 ]");
		put("409","La requête ne peut être traitée en l’état actuel.");
		put("410","La ressource n'est plus disponible et aucune adresse de redirection n’est connue.");
		put("411","La longueur de la requête n’a pas été précisée.");
		put("412","Préconditions envoyées par la requête non vérifiées.");
		put("413","Traitement abandonné dû à une requête trop importante.");
		put("414","URI trop longue.");
		put("415","Format de requête non supporté pour une méthode et une ressource données.");
		put("416","Champs d’en-tête de requête « range » incorrect.");
		put("417","Comportement attendu et défini dans l’en-tête de la requête insatisfaisante.");
		put("418","« Je suis une théière ». Ce code est défini dans la RFC 2324 [ 9 ] datée du premier avril 1998, Hyper Text Coffee Pot Control Protocol .");
		put("421","La requête a été envoyée à un serveur qui n'est pas capable de produire une réponse (par exemple, car une connexion a été réutilisée).");
		put("422","WebDAV : L’entité fournie avec la requête est incompréhensible ou incomplète.");
		put("423","WebDAV : L’opération ne peut avoir lieu car la ressource est verrouillée.");
		put("424","WebDAV : Une méthode de la transaction a échoué.");
		put("425","WebDAV RFC 3648 [ 10 ] . Ce code est défini dans le brouillon WebDAV Advanced Collections Protocol , mais est absent de Web Distributed Authoring and Versioning (WebDAV) Ordered Collections Protocol .");
		put("426","RFC 2817 [ 11 ] Le client devrait changer de protocole, par exemple au profit de TLS/1.0 .");
		put("428","RFC 6585 [ 12 ] La requête doit être conditionnelle.");
		put("429","RFC 6585 [ 13 ] Le client a émis trop de requêtes dans un délai donné.");
		put("431","RFC 6585 [ 13 ] Les entêtes HTTP émises dépassent la taille maximale admise par le serveur.");
		put("449","Code défini par Microsoft . La requête devrait être renvoyée après avoir effectué une action.");
		put("450","Code défini par Microsoft. Cette erreur est produite lorsque les outils de contrôle parental de Windows sont activés et bloquent l’accès à la page.");
		put("451","Ce code d'erreur indique que la ressource demandée est inaccessible pour des raisons d'ordre légal [ 14 ] , [ 15 ] .");
		put("456","WebDAV : Erreur irrécupérable.");
		put("444","Indique que le serveur n'a retourné aucune information vers le client et a fermé la connexion.");
		put("495","Une extension de l'erreur 400 Bad Request, utilisée lorsque le client a fourni un certificat invalide.");
		put("496","Une extension de l'erreur 400 Bad Request, utilisée lorsqu'un certificat client requis n'est pas fourni.");
		put("497","Une extension de l'erreur 400 Bad Request, utilisée lorsque le client envoie une requête HTTP vers le port 443 normalement destiné aux requêtes HTTPS.");
		put("498","Le jeton a expiré ou est invalide.");
		put("499","Le client a fermé la connexion avant de recevoir la réponse. Cette erreur se produit quand le traitement est trop long côté serveur [ 16 ] .");
		put("500","Erreur interne du serveur.");
		put("501","Fonctionnalité réclamée non supportée par le serveur.");
		put("502","En agissant en tant que serveur proxy ou passerelle, le serveur a reçu une réponse invalide depuis le serveur distant.");
		put("503","Service temporairement indisponible ou en maintenance.");
		put("504","Temps d’attente d’une réponse d’un serveur à un serveur intermédiaire écoulé.");
		put("505","Version HTTP non gérée par le serveur.");
		put("506","RFC 2295 [ 17 ] : Erreur de négociation. Transparent content negociation .");
		put("507","WebDAV : Espace insuffisant pour modifier les propriétés ou construire la collection.");
		put("508","WebDAV : Boucle dans une mise en relation de ressources ( RFC 5842 [ 18 ] ).");
		put("509","Utilisé par de nombreux serveurs pour indiquer un dépassement de quota.");
		put("510","RFC 2774 [ 19 ] : la requête ne respecte pas la politique d'accès aux ressources HTTP étendues.");
		put("511","RFC 6585 [ 13 ] : Le client doit s'authentifier pour accéder au réseau. Utilisé par les portails captifs pour rediriger les clients vers la page d'authentification.");
		put("520","L'erreur 520 est utilisé en tant que réponse générique lorsque le serveur d'origine retourne un résultat imprévu.");
		put("521","Le serveur a refusé la connexion depuis Cloudflare.");
		put("522","Cloudflare n'a pas pu négocier un TCP handshake avec le serveur d'origine.");
		put("523","Cloudflare n'a pas réussi à joindre le serveur d'origine. Cela peut se produire en cas d'échec de résolution de nom de serveur DNS.");
		put("524","Cloudflare a établi une connexion TCP avec le serveur d'origine mais n'a pas reçu de réponse HTTP avant l'expiration du délai de connexion.");
		put("525","Cloudflare n'a pas pu négocier un SSL/TLS handshake avec le serveur d'origine.");
		put("526","Cloudflare n'a pas pu valider le certificat SSL présenté par le serveur d'origine.");
		put("527","L'erreur 527 indique que la requête a dépassé le délai de connexion ou a échoué après que la connexion WAN ait été établie.");
	}
	
	
	private void put(String code, String name)
	{map.put(code,name);}
	
	
	public Object t(Object obj) throws Exception
	{
		String code = (String) obj;
		if(!map.containsKey(code)) throw new Exception("Unknown code: "+code);
		return map.get(code);
	}
}
