package a.entity.gus06.sys.form1.typetoname;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221105";}

	public static final String TEXTFIELD = "gus.sys.form1.item.textfield";
	public static final String TEXTAREA = "gus.sys.form1.item.textarea";
	public static final String PASSWORD = "gus.sys.form1.item.password";
	public static final String CHECKBOX = "gus.sys.form1.item.checkbox";
	public static final String SLIDER = "gus.sys.form1.item.slider";
	public static final String COMBOBOX = "gus.sys.form1.item.combobox";
	public static final String EMAIL = "gus.sys.form1.item.email1";
	public static final String COUNTRY_FR = "gus.sys.form1.item.combo.country.fr";
	public static final String COUNTRY_EN = "gus.sys.form1.item.combo.country.en";
	public static final String COUNTRY_JP = "gus.sys.form1.item.combo.country.jp";
	public static final String LANGUAGE_FR = "gus.sys.form1.item.combo.language.fr";
	public static final String LANGUAGE_EN = "gus.sys.form1.item.combo.language.en";
	public static final String LANGUAGE_JP = "gus.sys.form1.item.combo.language.jp";
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String type = (String) obj;
		
		if(type.equals("textfield")) return TEXTFIELD;
		if(type.equals("textarea")) return TEXTAREA;
		if(type.equals("password")) return PASSWORD;
		if(type.equals("checkbox")) return CHECKBOX;
		if(type.equals("slider")) return SLIDER;
		if(type.equals("combobox")) return COMBOBOX;
		if(type.equals("email")) return EMAIL;
		if(type.equals("country_fr")) return COUNTRY_FR;
		if(type.equals("country_en")) return COUNTRY_EN;
		if(type.equals("country_jp")) return COUNTRY_JP;
		if(type.equals("language_fr")) return LANGUAGE_FR;
		if(type.equals("language_en")) return LANGUAGE_EN;
		if(type.equals("language_jp")) return LANGUAGE_JP;
		
		return null;
	}
}
