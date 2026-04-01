package a.entity.gus06.ai.prompt.gus06_v1;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20251130";}


	private Service docEn;

	public EntityImpl() throws Exception
	{
		docEn = Outside.service(this,"gus06.framework.doc.en");
	}
	
	public Object g() throws Exception
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n\n@INTRODUCTION\n");
		sb.append((String) docEn.r("INTRODUCTION"));
		
		sb.append("\n\n@FRAMEWORK_SRC\n");
		sb.append((String) docEn.r("SRC_FRAMEWORK_ALL"));
		
		sb.append("\n\n@ENTITY_CODING_RULES\n");
		sb.append((String) docEn.r("ENTITY_CODING_RULES"));
		
		sb.append("\n\n@ENTITY_CODING_HINTS\n");
		sb.append((String) docEn.r("ENTITY_CODING_HINTS"));
		
		sb.append("\n\n@FEATURE_E_USE\n");
		sb.append((String) docEn.r("FEATURE_E_USE"));
		
		sb.append("\n\n@FEATURE_F_USE\n");
		sb.append((String) docEn.r("FEATURE_F_USE"));
		
		sb.append("\n\n@FEATURE_G_USE\n");
		sb.append((String) docEn.r("FEATURE_G_USE"));
		
		sb.append("\n\n@FEATURE_H_USE\n");
		sb.append((String) docEn.r("FEATURE_H_USE"));
		
		sb.append("\n\n@FEATURE_I_USE\n");
		sb.append((String) docEn.r("FEATURE_I_USE"));
		
		sb.append("\n\n@FEATURE_P_USE\n");
		sb.append((String) docEn.r("FEATURE_P_USE"));
		
		sb.append("\n\n@FEATURE_R_USE\n");
		sb.append((String) docEn.r("FEATURE_R_USE"));
		
		sb.append("\n\n@FEATURE_S_USE\n");
		sb.append((String) docEn.r("FEATURE_S_USE"));
		
		sb.append("\n\n@FEATURE_T_USE\n");
		sb.append((String) docEn.r("FEATURE_T_USE"));
		
		sb.append("\n\n@FEATURE_V_USE\n");
		sb.append((String) docEn.r("FEATURE_V_USE"));
		
		return sb.toString();
	}
}
