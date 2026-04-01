package a.entity.gus06.sys.expression1.apply.op._help_fr_symbols;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200416";}

	public static final String T = "constant";
	
	public static final String HELP = 
		"OPERATEURS UNAIRES\n"
		+ "!\tn\u00e9gation\n"
		+ "-\topposition/soustraction\n"
		+ "&\tinterpr\u00e9tation\n"
		+ "\u00a3\tinterpr\u00e9teur\n"
		+ "@\tinstanciation unique\n"
		+ "\u00a7\tinstanciation multiple\n"
		+ "\n"
		+ "OPERATEURS BINAIRES\n"
		+ "::\tinclusion\n"
		+ "!:\texclusion\n"
		+ "/\tdivision r\u00e9elle\n"
		+ "//\tdivision euclidienne\n"
		+ "%\tmodulo\n"
		+ "==\t\u00e9galit\u00e9\n"
		+ "!=\tdifference\n"
		+ "<=\tinf\u00e9rieur ou \u00e9gal\n"
		+ "<\tinf\u00e9rieur strictement\n"
		+ ">=\tsup\u00e9rieur ou \u00e9gal\n"
		+ ">\tsup\u00e9rieur strictement\n"
		+ "^\tpuissance/ou exclusif/intersection\n"
		+ "!^\textrasection\n"
		+ "\n"
		+ "OPERATEURS TERNAIRES\n"
		+ "?:\t\u00e9valuation conditionnelle\n"
		+ "\n"
		+ "OPERATEURS N-AIRES\n"
		+ "+\taddition\n"
		+ "*\tmultiplication\n"
		+ "&&\tet\n"
		+ "||\tou inclusif\n"
		+ "|\tcomposition\n"
		+ "#\tapplication\n"
		+ ".\tconnexion";
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return HELP;
	}
}
