package a.entity.gus06.sys.jwpce1.importer1.formatrow;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250722";}


	public static final String COL_ROMAJI = "romaji";
	public static final String COL_KANA = "kana";
	public static final String COL_KANJI = "kanji";
	public static final String COL_GRAMMATICAL_TAGS = "grammatical_tags";
	public static final String COL_SENSES = "senses";
	public static final String COL_COMMON = "common";


	private Service buildRomaji;

	public EntityImpl() throws Exception
	{
		buildRomaji = Outside.service(this,"gus06.string.transform.japanese.romaji.builder");
	}
	
	public Object t(Object obj) throws Exception
	{
		String row = (String) obj;
		
		String[] n = row.split("/",2);
		String n0 = get(n,0);
		String n1 = get(n,1);
		
		String[] m = n0.split("\\[",2);
		
		String kanji = get(m,0);
		String kana = get(m,1).replace("]","").trim();
		String romaji = (String) buildRomaji.t(kana);
		
		String[] k = n1.split("\\)",2);
		String tags = get(k,0).replace("(","").trim();
		String senses = get(k,1);
		boolean common = senses.contains("/(P)/");
		senses = senses.replace("/(P)/","").replace(";","");
		
		Map data = new HashMap();
		
		data.put(COL_ROMAJI, romaji);
		data.put(COL_KANA, kana);
		data.put(COL_KANJI, kanji);
		data.put(COL_GRAMMATICAL_TAGS, tags);
		data.put(COL_SENSES, senses);
		data.put(COL_COMMON, common);
		
		return data;
	}
	
	private String get(String[] n, int index)
	{return index<n.length ? n[index].trim() : "";}
}