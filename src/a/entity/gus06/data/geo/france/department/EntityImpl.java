package a.entity.gus06.data.geo.france.department;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20250427";}


	private Map map;
	
	public Object g() throws Exception
	{
		if(map==null) init();
		return map;
	}
	
	private void init()
	{
		map = new HashMap();
		
		map.put("01", "Ain");
		map.put("02", "Aisne");
		map.put("03", "Allier");
		map.put("04", "Alpes-de-Haute-Provence");
		map.put("05", "Hautes-Alpes");
		map.put("06", "Alpes-Maritimes");
		map.put("07", "Ard�che");
		map.put("08", "Ardennes");
		map.put("09", "Ari�ge");
		map.put("10", "Aube");
		map.put("11", "Aude");
		map.put("12", "Aveyron");
		map.put("13", "Bouches-du-Rh�ne");
		map.put("14", "Calvados");
		map.put("15", "Cantal");
		map.put("16", "Charente");
		map.put("17", "Charente-Maritime");
		map.put("18", "Cher");
		map.put("19", "Corr�ze");
		map.put("2A", "Corse-du-Sud");
		map.put("2B", "Haute-Corse");
		map.put("21", "C�te-d'Or");
		map.put("22", "C�tes-d'Armor");
		map.put("23", "Creuse");
		map.put("24", "Dordogne");
		map.put("25", "Doubs");
		map.put("26", "Dr�me");
		map.put("27", "Eure");
		map.put("28", "Eure-et-Loir");
		map.put("29", "Finist�re");
		map.put("30", "Gard");
		map.put("31", "Haute-Garonne");
		map.put("32", "Gers");
		map.put("33", "Gironde");
		map.put("34", "H�rault");
		map.put("35", "Ille-et-Vilaine");
		map.put("36", "Indre");
		map.put("37", "Indre-et-Loire");
		map.put("38", "Is�re");
		map.put("39", "Jura");
		map.put("40", "Landes");
		map.put("41", "Loir-et-Cher");
		map.put("42", "Loire");
		map.put("43", "Haute-Loire");
		map.put("44", "Loire-Atlantique");
		map.put("45", "Loiret");
		map.put("46", "Lot");
		map.put("47", "Lot-et-Garonne");
		map.put("48", "Loz�re");
		map.put("49", "Maine-et-Loire");
		map.put("50", "Manche");
		map.put("51", "Marne");
		map.put("52", "Haute-Marne");
		map.put("53", "Mayenne");
		map.put("54", "Meurthe-et-Moselle");
		map.put("55", "Meuse");
		map.put("56", "Morbihan");
		map.put("57", "Moselle");
		map.put("58", "Ni�vre");
		map.put("59", "Nord");
		map.put("60", "Oise");
		map.put("61", "Orne");
		map.put("62", "Pas-de-Calais");
		map.put("63", "Puy-de-D�me");
		map.put("64", "Pyr�n�es-Atlantiques");
		map.put("65", "Hautes-Pyr�n�es");
		map.put("66", "Pyr�n�es-Orientales");
		map.put("67", "Bas-Rhin");
		map.put("68", "Haut-Rhin");
		map.put("69", "Rh�ne");
		map.put("70", "Haute-Sa�ne");
		map.put("71", "Sa�ne-et-Loire");
		map.put("72", "Sarthe");
		map.put("73", "Savoie");
		map.put("74", "Haute-Savoie");
		map.put("75", "Paris");
		map.put("76", "Seine-Maritime");
		map.put("77", "Seine-et-Marne");
		map.put("78", "Yvelines");
		map.put("79", "Deux-S�vres");
		map.put("80", "Somme");
		map.put("81", "Tarn");
		map.put("82", "Tarn-et-Garonne");
		map.put("83", "Var");
		map.put("84", "Vaucluse");
		map.put("85", "Vend�e");
		map.put("86", "Vienne");
		map.put("87", "Haute-Vienne");
		map.put("88", "Vosges");
		map.put("89", "Yonne");
		map.put("90", "Territoire de Belfort");
		map.put("91", "Essonne");
		map.put("92", "Hauts-de-Seine");
		map.put("93", "Seine-Saint-Denis");
		map.put("94", "Val-de-Marne");
		map.put("95", "Val-d'Oise");
	}
}
