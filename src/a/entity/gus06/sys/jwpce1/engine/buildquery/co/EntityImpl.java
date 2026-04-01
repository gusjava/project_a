package a.entity.gus06.sys.jwpce1.engine.buildquery.co;

import a.framework.*;
import java.io.File;
import java.sql.Connection;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250726";}


	private Service formatValue;
	private Service formatLike;
	private Service isJapanese;
	private Service buildHiragana;
	private Service buildKatakana;
	private Service prepare;

	public EntityImpl() throws Exception
	{
		formatValue = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");
		formatLike = Outside.service(this,"gus06.jdbc.mysql.format.sql.stringforlike");
		isJapanese = Outside.service(this,"gus06.filter.string.is.japanese");
		buildHiragana = Outside.service(this,"gus06.string.transform.japanese.hiragana.builder");
		buildKatakana = Outside.service(this,"gus06.string.transform.japanese.katakana.builder");
		prepare = Outside.service(this,"gus06.sys.jwpce1.engine.buildquery.prepare");
	}
	
	public Object t(Object obj) throws Exception
	{
		String value = (String) obj;
		if(value.startsWith("!"))
		{
			return sqlSenses(value.substring(1));
		}
		if(value.startsWith("*"))
		{
			value = (String) prepare.t(value.substring(1));
			if(isJapanese.f(value)) return sqlKana(value)+" OR "+sqlKanji(value);
			
			String hiragana = (String) buildHiragana.t(value);
			String katakana = (String) buildKatakana.t(value);
			
			return sqlKana(hiragana)+" OR "+sqlKana(katakana);
		}
		
		value = (String) prepare.t(value);
		if(isJapanese.f(value)) return sqlKana(value)+" OR "+sqlKanji(value);
		
		String hiragana = (String) buildHiragana.t(value);
		String katakana = (String) buildKatakana.t(value);
		
		return sqlKana(hiragana)+" OR "+sqlKana(katakana)+" OR "+sqlSenses(value);
	}
	
	private String sqlKana(String value) throws Exception
	{return "kana like "+formatLike(value);}
	
	private String sqlKanji(String value) throws Exception
	{return "kanji like "+formatLike(value);}
		
	private String sqlSenses(String value) throws Exception
	{return "senses like "+formatLike(value);}
		
	private String formatValue(String value) throws Exception
	{return (String) formatValue.t(value);}

	private String formatLike(Object value) throws Exception
	{return formatValue("%"+formatLike.t(value)+"%");}
}