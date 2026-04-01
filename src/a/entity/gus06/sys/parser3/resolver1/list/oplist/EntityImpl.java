package a.entity.gus06.sys.parser3.resolver1.list.oplist;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160109";}

	
	
	private List list;
	
	
	public EntityImpl() throws Exception
	{
		list = new ArrayList();
		
		
		// A ? B : C
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.ternary"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.ternary"),
			"?:"
		);
		
		// A || B || C
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.seq.or"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.or"),
			"||"
		);
		
		// A && B && C
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.seq.and"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.and"),
			"&&"
		);
		
		// ! A
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.unary.not"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.unary.not"),
			"!"
		);
		
		// sqrt A
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.unary.rootsquare"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.unary.rootsquare"),
			"\u221a"
		);
		
		// cbrt A
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.unary.rootcube"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.unary.rootcube"),
			"\u221b"
		);
		
		
		
		// A :: B
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.binary.has"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.binary.has"),
			"::"
		);
		
		// A !: B
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.binary.hasnot"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.binary.hasnot"),
			"!:"
		);
		
		// A : B : C
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.seq.enu"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.enu"),
			":"
		);
		
		
		// A | B | C
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.seq.pipe"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.pipe"),
			"|"
		);
		
		// A == B
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.binary.equals"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.binary.equals"),
			"=="
		);
		
		// A != B
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.binary.diff"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.binary.diff"),
			"!="
		);
		
		// A >= B
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.binary.sup1"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.binary.sup1"),
			">="
		);
		
		// A <= B
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.binary.inf1"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.binary.inf1"),
			"<="
		);
		
		// A > B
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.binary.sup2"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.binary.sup2"),
			">"
		);
		
		// A < B
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.binary.inf2"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.binary.inf2"),
			"<"
		);
		
		// A + B - C
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.seq.sum"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum"),
			"+"
		);
		
		// A * B * C
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.seq.product"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.product"),
			"*"
		);
		
		// A // B
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.binary.div2"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.binary.div2"),
			"//"
		);
		
		// A / B
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.binary.div"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.binary.div"),
			"/"
		);
		
		// A % B
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.binary.modulo"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.binary.modulo"),
			"%"
		);
		
		// A !^ B
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.binary.powernot"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.binary.powernot"),
			"!^"
		);
		
		// A ^ B
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.binary.power"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.binary.power"),
			"^"
		);
		
		// - A
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.unary.opp"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.unary.opp"),
			"-"
		);
		
		// @ A
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.unary.unique"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.unary.unique"),
			"@"
		);
		
		// § A
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.unary.multiple"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.unary.multiple"),
			"§"
		);
		
		// & A
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.unary.apply"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.unary.apply"),
			"&"
		);
		
		// £ A
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.unary.applier"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.unary.applier"),
			"£"
		);
		
		// A %
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.unary.percent"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.unary.percent"),
			"%"
		);
		
		// A !
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.unary.factorial"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.unary.factorial"),
			"!"
		);
		
		// A 2
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.unary.square"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.unary.square"),
			"\u00b2"
		);
		
		// A 3
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.unary.cube"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.unary.cube"),
			"\u00b3"
		);
		
		// A # B # C
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.seq.apply"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.apply"),
			"#"
		);
		
		// A . B . C
		add(
			Outside.service(this,"gus06.sys.parser3.cut.op.seq.point"),
			Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.point"),
			"."
		);
	}
	
	
	public Object g() throws Exception
	{return list;}
	
	
	private void add(T t1, T t2, String type)
	{list.add(new Object[]{t1,t2,type});}
}
