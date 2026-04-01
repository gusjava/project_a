package a.entity.gus06.sys.draw1.findbuilder;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250826";}


	private Service builderImage;
	private Service builderLine;
	private Service builderOval;
	private Service builderRect;
	private Service builderRound;
	private Service builderSquare;
	private Service builderText;

	public EntityImpl() throws Exception
	{
		builderImage = Outside.service(this,"gus06.sys.draw1.builder.image");
		builderLine = Outside.service(this,"gus06.sys.draw1.builder.line");
		builderOval = Outside.service(this,"gus06.sys.draw1.builder.oval");
		builderRect = Outside.service(this,"gus06.sys.draw1.builder.rect");
		builderRound = Outside.service(this,"gus06.sys.draw1.builder.round");
		builderSquare = Outside.service(this,"gus06.sys.draw1.builder.square");
		builderText = Outside.service(this,"gus06.sys.draw1.builder.text");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String type = (String) obj;
		
		if(type.equals("image")) return builderImage;
		if(type.equals("line")) return builderLine;
		if(type.equals("oval")) return builderOval;
		if(type.equals("rect")) return builderRect;
		if(type.equals("round")) return builderRound;
		if(type.equals("square")) return builderSquare;
		if(type.equals("text")) return builderText;
		
		throw new Exception("Unknown type: "+type);
	}
}