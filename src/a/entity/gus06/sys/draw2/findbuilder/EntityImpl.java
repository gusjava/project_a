package a.entity.gus06.sys.draw2.findbuilder;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250826";}


	private Service builderRound;
	private Service builderOval;
	private Service builderSquare;
	private Service builderRect;

	public EntityImpl() throws Exception
	{
		builderRound = Outside.service(this,"gus06.sys.draw2.builder.round");
		builderOval = Outside.service(this,"gus06.sys.draw2.builder.oval");
		builderSquare = Outside.service(this,"gus06.sys.draw2.builder.square");
		builderRect = Outside.service(this,"gus06.sys.draw2.builder.rect");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String type = (String) obj;
		
		if(type.equals("round")) return builderRound;
		if(type.equals("oval")) return builderOval;
		if(type.equals("square")) return builderSquare;
		if(type.equals("rect")) return builderRect;
		
		throw new Exception("Unknown type: "+type);
	}
}