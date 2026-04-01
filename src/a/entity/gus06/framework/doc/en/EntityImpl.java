package a.entity.gus06.framework.doc.en;

import a.framework.*;

public class EntityImpl implements Entity, R {
	public String creationDate() {return "20251130";}


	private Service introduction;
	private Service entityCodingRules;
	private Service entityCodingHints;
	private Service frameworkSrc;
	
	private Service featureEUse;
	private Service featureFUse;
	private Service featureGUse;
	private Service featureHUse;
	private Service featureIUse;
	private Service featurePUse;
	private Service featureRUse;
	private Service featureSUse;
	private Service featureTUse;
	private Service featureVUse;
	
	public EntityImpl() throws Exception
	{
		introduction = Outside.service(this,"gus06.framework.doc.en.introduction");
		entityCodingRules = Outside.service(this,"gus06.framework.doc.en.entity_coding_rules");
		entityCodingHints = Outside.service(this,"gus06.framework.doc.en.entity_coding_hints");
		frameworkSrc = Outside.service(this,"gus06.framework.sources");
		
		featureEUse = Outside.service(this,"gus06.framework.doc.en.feature_e_use");
		featureFUse = Outside.service(this,"gus06.framework.doc.en.feature_f_use");
		featureGUse = Outside.service(this,"gus06.framework.doc.en.feature_g_use");
		featureHUse = Outside.service(this,"gus06.framework.doc.en.feature_h_use");
		featureIUse = Outside.service(this,"gus06.framework.doc.en.feature_i_use");
		featurePUse = Outside.service(this,"gus06.framework.doc.en.feature_p_use");
		featureRUse = Outside.service(this,"gus06.framework.doc.en.feature_r_use");
		featureSUse = Outside.service(this,"gus06.framework.doc.en.feature_s_use");
		featureTUse = Outside.service(this,"gus06.framework.doc.en.feature_t_use");
		featureVUse = Outside.service(this,"gus06.framework.doc.en.feature_v_use");
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("INTRODUCTION")) return introduction.g();
		if(key.equals("ENTITY_CODING_RULES")) return entityCodingRules.g();
		if(key.equals("ENTITY_CODING_HINTS")) return entityCodingHints.g();
		
		if(key.equals("FEATURE_E_USE")) return featureEUse.g();
		if(key.equals("FEATURE_F_USE")) return featureFUse.g();
		if(key.equals("FEATURE_G_USE")) return featureGUse.g();
		if(key.equals("FEATURE_H_USE")) return featureHUse.g();
		if(key.equals("FEATURE_I_USE")) return featureIUse.g();
		if(key.equals("FEATURE_P_USE")) return featurePUse.g();
		if(key.equals("FEATURE_R_USE")) return featureRUse.g();
		if(key.equals("FEATURE_S_USE")) return featureSUse.g();
		if(key.equals("FEATURE_T_USE")) return featureTUse.g();
		if(key.equals("FEATURE_V_USE")) return featureVUse.g();
		
		if(key.equals("SRC_FRAMEWORK_ALL")) return frameworkSrc.r("*");
		if(key.equals("SRC_FRAMEWORK_E")) return frameworkSrc.r("E");
		if(key.equals("SRC_FRAMEWORK_G")) return frameworkSrc.r("G");
		if(key.equals("SRC_FRAMEWORK_P")) return frameworkSrc.r("P");
		if(key.equals("SRC_FRAMEWORK_T")) return frameworkSrc.r("T");
		if(key.equals("SRC_FRAMEWORK_F")) return frameworkSrc.r("F");
		if(key.equals("SRC_FRAMEWORK_R")) return frameworkSrc.r("R");
		if(key.equals("SRC_FRAMEWORK_V")) return frameworkSrc.r("V");
		if(key.equals("SRC_FRAMEWORK_S")) return frameworkSrc.r("S");
		if(key.equals("SRC_FRAMEWORK_H")) return frameworkSrc.r("H");
		if(key.equals("SRC_FRAMEWORK_I")) return frameworkSrc.r("I");
		if(key.equals("SRC_FRAMEWORK_SERVICE")) return frameworkSrc.r("SERVICE");
		if(key.equals("SRC_FRAMEWORK_ENTITY")) return frameworkSrc.r("ENTITY");
		if(key.equals("SRC_FRAMEWORK_MANAGER")) return frameworkSrc.r("MANAGER");
		if(key.equals("SRC_FRAMEWORK_OUTSIDE")) return frameworkSrc.r("OUTSIDE");
		if(key.equals("SRC_FRAMEWORK_S1")) return frameworkSrc.r("S1");
		
		if(key.equals("keys")) return new String[]{
			"INTRODUCTION", 
			"ENTITY_CODING_RULES", 
			"FEATURE_E_USE", 
			"FEATURE_F_USE", 
			"FEATURE_G_USE", 
			"FEATURE_H_USE", 
			"FEATURE_I_USE", 
			"FEATURE_P_USE", 
			"FEATURE_R_USE", 
			"FEATURE_S_USE", 
			"FEATURE_T_USE", 
			"FEATURE_V_USE", 
			"SRC_FRAMEWORK_ALL",
			"SRC_FRAMEWORK_E",
			"SRC_FRAMEWORK_G",
			"SRC_FRAMEWORK_P",
			"SRC_FRAMEWORK_T",
			"SRC_FRAMEWORK_F",
			"SRC_FRAMEWORK_R",
			"SRC_FRAMEWORK_V",
			"SRC_FRAMEWORK_S",
			"SRC_FRAMEWORK_H",
			"SRC_FRAMEWORK_I",
			"SRC_FRAMEWORK_SERVICE",
			"SRC_FRAMEWORK_ENTITY",
			"SRC_FRAMEWORK_MANAGER",
			"SRC_FRAMEWORK_OUTSIDE",
			"SRC_FRAMEWORK_S1"
		};
		throw new Exception("Unknown key: "+key);
	}
}
