package a.entity.gus06.filter.string.build.rule1.a;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}


	
	private Service f_equals;
	private Service f_equals_i;
	private Service f_equals_n;
	
	private Service f_startswith;
	private Service f_startswith_i;
	private Service f_startswith_n;
	
	private Service f_endswith;
	private Service f_endswith_i;
	private Service f_endswith_n;

	private Service f_contains;
	private Service f_contains_i;
	private Service f_contains_n;

	private Service f_near;
	private Service f_near_i;
	private Service f_near_n;
	
	private Service f_allofthem;
	private Service f_allofthem_i;
	private Service f_allofthem_n;
	
	private Service f_oneofthem;
	private Service f_oneofthem_i;
	private Service f_oneofthem_n;

	private Service f_el_allofthem;
	private Service f_el_allofthem_i;
	private Service f_el_allofthem_n;
	
	private Service f_el_oneofthem;
	private Service f_el_oneofthem_i;
	private Service f_el_oneofthem_n;

	private Service f_equals_oneofthem;
	private Service f_equals_oneofthem_i;
	private Service f_equals_oneofthem_n;
	
	private Service f_mask;
	private Service f_mask_i;
	private Service f_mask_n;
	
	private Service f_mask2;
	private Service f_mask2_i;
	private Service f_mask2_n;

	private Service f_matches;
	private Service f_matches_i;
	private Service f_matches_n;

	private Service f_mstars;
	private Service f_mstars_i;
	private Service f_mstars_n;

	private Service f_mstars2;
	private Service f_mstars2_i;
	private Service f_mstars2_n;

	private Service f_containsregexp;
	private Service f_containsregexp_i;
	private Service f_containsregexp_n;

	private Service f_number_equals;
	private Service f_number_range;
	private Service f_date_range;
	
	private Service f_uni_equals;
	private Service f_uni_startswith;
	private Service f_uni_endswith;
	private Service f_uni_contains;

	private Service f_int;
	private Service f_length;
	private Service f_haschar;
	private Service f_custom;
	
	
	
	
	public EntityImpl() throws Exception
	{
		f_equals = Outside.service(this,"gus06.filter.string.build.equals");
		f_equals_i = Outside.service(this,"gus06.filter.string.build.equals_i");
		f_equals_n = Outside.service(this,"gus06.filter.string.build.equals_n");
		
		f_startswith = Outside.service(this,"gus06.filter.string.build.startswith");
		f_startswith_i = Outside.service(this,"gus06.filter.string.build.startswith_i");
		f_startswith_n = Outside.service(this,"gus06.filter.string.build.startswith_n");
		
		f_endswith = Outside.service(this,"gus06.filter.string.build.endswith");
		f_endswith_i = Outside.service(this,"gus06.filter.string.build.endswith_i");
		f_endswith_n = Outside.service(this,"gus06.filter.string.build.endswith_n");

		f_contains = Outside.service(this,"gus06.filter.string.build.contains");
		f_contains_i = Outside.service(this,"gus06.filter.string.build.contains_i");
		f_contains_n = Outside.service(this,"gus06.filter.string.build.contains_n");

		f_near = Outside.service(this,"gus06.filter.string.build.near");
		f_near_i = Outside.service(this,"gus06.filter.string.build.near_i");
		f_near_n = Outside.service(this,"gus06.filter.string.build.near_n");
		
		f_allofthem = Outside.service(this,"gus06.filter.string.build.allofthem");
		f_allofthem_i = Outside.service(this,"gus06.filter.string.build.allofthem_i");
		f_allofthem_n = Outside.service(this,"gus06.filter.string.build.allofthem_n");

		f_oneofthem = Outside.service(this,"gus06.filter.string.build.oneofthem");
		f_oneofthem_i = Outside.service(this,"gus06.filter.string.build.oneofthem_i");
		f_oneofthem_n = Outside.service(this,"gus06.filter.string.build.oneofthem_n");

		f_el_allofthem = Outside.service(this,"gus06.filter.string.build.element.allofthem");
		f_el_allofthem_i = Outside.service(this,"gus06.filter.string.build.element.allofthem_i");
		f_el_allofthem_n = Outside.service(this,"gus06.filter.string.build.element.allofthem_n");

		f_el_oneofthem = Outside.service(this,"gus06.filter.string.build.element.oneofthem");
		f_el_oneofthem_i = Outside.service(this,"gus06.filter.string.build.element.oneofthem_i");
		f_el_oneofthem_n = Outside.service(this,"gus06.filter.string.build.element.oneofthem_n");

		f_equals_oneofthem = Outside.service(this,"gus06.filter.string.build.equals.oneofthem");
		f_equals_oneofthem_i = Outside.service(this,"gus06.filter.string.build.equals.oneofthem_i");
		f_equals_oneofthem_n = Outside.service(this,"gus06.filter.string.build.equals.oneofthem_n");

		f_mask = Outside.service(this,"gus06.filter.string.build.mask");
		f_mask_i = Outside.service(this,"gus06.filter.string.build.mask_i");
		f_mask_n = Outside.service(this,"gus06.filter.string.build.mask_n");

		f_mask2 = Outside.service(this,"gus06.filter.string.build.mask2");
		f_mask2_i = Outside.service(this,"gus06.filter.string.build.mask2_i");
		f_mask2_n = Outside.service(this,"gus06.filter.string.build.mask2_n");

		f_matches = Outside.service(this,"gus06.filter.string.build.matches");
		f_matches_i = Outside.service(this,"gus06.filter.string.build.matches_i");
		f_matches_n = Outside.service(this,"gus06.filter.string.build.matches_n");

		f_mstars = Outside.service(this,"gus06.filter.string.build.mstars");
		f_mstars_i = Outside.service(this,"gus06.filter.string.build.mstars_i");
		f_mstars_n = Outside.service(this,"gus06.filter.string.build.mstars_n");

		f_mstars2 = Outside.service(this,"gus06.filter.string.build.mstars2");
		f_mstars2_i = Outside.service(this,"gus06.filter.string.build.mstars2_i");
		f_mstars2_n = Outside.service(this,"gus06.filter.string.build.mstars2_n");

		f_containsregexp = Outside.service(this,"gus06.filter.string.build.containsregexp");
		f_containsregexp_i = Outside.service(this,"gus06.filter.string.build.containsregexp_i");
		f_containsregexp_n = Outside.service(this,"gus06.filter.string.build.containsregexp_n");

		f_number_equals = Outside.service(this,"gus06.filter.string.build.number.equals");
		f_number_range = Outside.service(this,"gus06.filter.string.build.number.range");
		f_date_range = Outside.service(this,"gus06.filter.string.build.date.range");
		
		f_uni_equals = Outside.service(this,"gus06.filter.string.build.unicode.equals");
		f_uni_startswith = Outside.service(this,"gus06.filter.string.build.unicode.startswith");
		f_uni_endswith = Outside.service(this,"gus06.filter.string.build.unicode.endswith");
		f_uni_contains = Outside.service(this,"gus06.filter.string.build.unicode.contains");

		f_int = Outside.service(this,"gus06.filter.string.build.number.integer.filter1");
		f_length = Outside.service(this,"gus06.filter.string.build.length.intfilter");
		f_haschar = Outside.service(this,"gus06.filter.string.build.character.haschar");
		f_custom = Outside.service(this,"gus06.filter.string.build.custom");
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		String[] n = (String[]) obj;
		if(n.length!=2) throw new Exception("Wrong data number: "+n.length);
		return build(n[0],n[1]);
	}
	
	
	
	
	private F build(String type, String info) throws Exception
	{
		if(type.equals(FILTER.EQ)) 		return build(f_equals,info);
		if(type.equals(FILTER.EQ_I)) 		return build(f_equals_i,info);
		if(type.equals(FILTER.EQ_N)) 		return build(f_equals_n,info);
		
		if(type.equals(FILTER.ST)) 		return build(f_startswith,info);
		if(type.equals(FILTER.ST_I)) 		return build(f_startswith_i,info);
		if(type.equals(FILTER.ST_N)) 		return build(f_startswith_n,info);
		
		if(type.equals(FILTER.EN)) 		return build(f_endswith,info);
		if(type.equals(FILTER.EN_I)) 		return build(f_endswith_i,info);
		if(type.equals(FILTER.EN_N)) 		return build(f_endswith_n,info);
		
		if(type.equals(FILTER.CO)) 		return build(f_contains,info);
		if(type.equals(FILTER.CO_I)) 		return build(f_contains_i,info);
		if(type.equals(FILTER.CO_N)) 		return build(f_contains_n,info);

		if(type.equals(FILTER.NEAR)) 		return build(f_near,info);
		if(type.equals(FILTER.NEAR_I)) 		return build(f_near_i,info);
		if(type.equals(FILTER.NEAR_N)) 		return build(f_near_n,info);
		
		if(type.equals(FILTER.AOTHEM)) 		return build(f_allofthem,info);
		if(type.equals(FILTER.AOTHEM_I)) 	return build(f_allofthem_i,info);
		if(type.equals(FILTER.AOTHEM_N)) 	return build(f_allofthem_n,info);
		
		if(type.equals(FILTER.OOTHEM)) 		return build(f_oneofthem,info);
		if(type.equals(FILTER.OOTHEM_I)) 	return build(f_oneofthem_i,info);
		if(type.equals(FILTER.OOTHEM_N)) 	return build(f_oneofthem_n,info);
		
		if(type.equals(FILTER.EAOTHEM)) 	return build(f_el_allofthem,info);
		if(type.equals(FILTER.EAOTHEM_I)) 	return build(f_el_allofthem_i,info);
		if(type.equals(FILTER.EAOTHEM_N)) 	return build(f_el_allofthem_n,info);
		
		if(type.equals(FILTER.EOOTHEM)) 	return build(f_el_oneofthem,info);
		if(type.equals(FILTER.EOOTHEM_I)) 	return build(f_el_oneofthem_i,info);
		if(type.equals(FILTER.EOOTHEM_N)) 	return build(f_el_oneofthem_n,info);
		
		if(type.equals(FILTER.EQO)) 		return build(f_equals_oneofthem,info);
		if(type.equals(FILTER.EQO_I)) 		return build(f_equals_oneofthem_i,info);
		if(type.equals(FILTER.EQO_N)) 		return build(f_equals_oneofthem_n,info);
		
		if(type.equals(FILTER.MASK)) 		return build(f_mask,info);
		if(type.equals(FILTER.MASK_I)) 		return build(f_mask_i,info);
		if(type.equals(FILTER.MASK_N)) 		return build(f_mask_n,info);
		
		if(type.equals(FILTER.MASK2)) 		return build(f_mask2,info);
		if(type.equals(FILTER.MASK2_I)) 	return build(f_mask2_i,info);
		if(type.equals(FILTER.MASK2_N)) 	return build(f_mask2_n,info);

		if(type.equals(FILTER.EQEXP)) 		return build(f_matches,info);
		if(type.equals(FILTER.EQEXP_I)) 	return build(f_matches_i,info);
		if(type.equals(FILTER.EQEXP_N)) 	return build(f_matches_n,info);

		if(type.equals(FILTER.MSTARS)) 		return build(f_mstars,info);
		if(type.equals(FILTER.MSTARS_I)) 	return build(f_mstars_i,info);
		if(type.equals(FILTER.MSTARS_N)) 	return build(f_mstars_n,info);

		if(type.equals(FILTER.MSTARS2)) 	return build(f_mstars2,info);
		if(type.equals(FILTER.MSTARS2_I)) 	return build(f_mstars2_i,info);
		if(type.equals(FILTER.MSTARS2_N)) 	return build(f_mstars2_n,info);

		if(type.equals(FILTER.COEXP)) 		return build(f_containsregexp,info);
		if(type.equals(FILTER.COEXP_I)) 	return build(f_containsregexp_i,info);
		if(type.equals(FILTER.COEXP_N)) 	return build(f_containsregexp_n,info);
		
		if(type.equals(FILTER.NUMEQ)) 		return build(f_number_equals,info);
		if(type.equals(FILTER.NUMRA)) 		return build(f_number_range,info);
		if(type.equals(FILTER.DATERA)) 		return build(f_date_range,info);
		
		if(type.equals(FILTER.UNIEQ)) 		return build(f_uni_equals,info);
		if(type.equals(FILTER.UNIST)) 		return build(f_uni_startswith,info);
		if(type.equals(FILTER.UNIEN)) 		return build(f_uni_endswith,info);
		if(type.equals(FILTER.UNICO)) 		return build(f_uni_contains,info);
		
		if(type.equals(FILTER.INT)) 		return build(f_int,info);
		if(type.equals(FILTER.LENGTH)) 		return build(f_length,info);
		if(type.equals(FILTER.HASCHAR)) 	return build(f_haschar,info);
		if(type.equals(FILTER.CUSTOM)) 		return build(f_custom,info);
		
		throw new Exception("Invalid rule type: "+type);
	}
	
	
	
	private F build(Service s, String info) throws Exception
	{return (F) s.t(info);}
}
