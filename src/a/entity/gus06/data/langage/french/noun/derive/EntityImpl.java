package a.entity.gus06.data.langage.french.noun.derive;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250220";}

	public static final String VOWELS = "aeiouy\u00e2\u00ea\u00ee\u00f4\u00fb\u00e4\u00eb\u00ef\u00f6\u00fc\u00ff\u00e9\u00e8\u00e0\u00f9";

	public EntityImpl() throws Exception
	{
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(":");

		String singular = getSingular(n);
		String plural = getPlural(n, singular);
		boolean isMale = isMale(n);
		boolean startsWithVowel = startsWithVowel(singular);
		
		Map map = new HashMap();
		
		//sd : singulier défini
		map.put("sd", buildSingularDefined(singular, isMale, startsWithVowel));
		//singulier indéfini
		map.put("su", buildSingularUndefined(singular, isMale));
		//singulier démonstratif
		map.put("sc", buildSingularDemonstrative(singular, isMale, startsWithVowel));
		
		//pluriel défini
		map.put("pd", buildPluralDefined(plural));
		//pluriel indéfini
		map.put("pu", buildPluralUndefined(plural));
		//pluriel démonstratif
		map.put("pc", buildPluralDemonstrative(plural));
		
		return map;
	}

	private boolean isMale(String[] n)
	{
		if(n.length==1) return true;
		return n[0].equals("m");
	}

	private boolean startsWithVowel(String singular)
	{
		return VOWELS.indexOf(singular.charAt(0))>=0;
	}
	
	private String getSingular(String[] n)
	{
		if(n.length==1) return n[0];
		return n[1];
	}
	
	private String getPlural(String[] n, String singular)
	{
		if(n.length==3) return n[2];
		return defaultPlural(singular);
	}
	
	private String defaultPlural(String singular)
	{
		if(!singular.contains(" ")) return singular+"s";
		String[] n = singular.split(" ",2);
		return n[0]+"s "+n[1]; 
	}
	
	/*
	 * BUILD SINGULAR
	 */
	
	private String buildSingularDefined(String singular, boolean isMale, boolean startsWithVowel)
	{
		if(startsWithVowel) return "l'"+singular;
		if(isMale) return "le "+singular;
		return "la "+singular;
	}
	
	private String buildSingularUndefined(String singular, boolean isMale)
	{
		if(isMale) return "un "+singular;
		return "une "+singular;
	}
	
	private String buildSingularDemonstrative(String singular, boolean isMale, boolean startsWithVowel)
	{
		if(!isMale) return "cette "+singular;
		if(startsWithVowel) return "cet "+singular;
		return "ce "+singular;
	}
	
	/*
	 * BUILD PLURAL
	 */
	
	private String buildPluralDefined(String plural)
	{
		return "les "+plural;
	}
	
	private String buildPluralUndefined(String plural)
	{
		return "des "+plural;
	}
	
	private String buildPluralDemonstrative(String plural)
	{
		return "ces "+plural;
	}
}
