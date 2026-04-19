package a.entity.gus.y.entityhash1.src.buildast;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260419";}
	
	private static Comparator compBySign = (Comparator) (a, b) ->{
		Map mA = (Map) a;
		Map mB = (Map) b;
		
		String signA = (String) mA.get("sign");
		String signB = (String) mB.get("sign");
		
		return signA.compareTo(signB);
	};

	private static Comparator compByName = (Comparator) (a, b) ->{
		Map mA = (Map) a;
		Map mB = (Map) b;
		
		String nameA = (String) mA.get("name");
		String nameB = (String) mB.get("name");
		
		return nameA.compareTo(nameB);
	};


	private Service buildAst;

	public EntityImpl() throws Exception {
		
		buildAst = Outside.service(this,"gus.x.java.srccode.ast3");
	}
	
	public Object t(Object obj) throws Exception {
		String src = (String) obj;
		
		Map root = (Map) buildAst.t(src);
		Map output = new HashMap();
		
		List types = (List) root.get("types");
		
		if(types.size()!=1)
			throw new Exception("Invalid entity src");
			
		Map type = (Map) types.get(0);
		
		if(!type.get("name").equals("EntityImpl"))
			throw new Exception("Invalid entity src");
			
		if(!type.get("kind").equals("class"))
			throw new Exception("Invalid entity src");
		
		if(!type.containsKey("implements"))
			throw new Exception("Invalid entity src");
			
		if(!type.containsKey("methods"))
			throw new Exception("Invalid entity src");
			
		if(!type.containsKey("modifiers"))
			throw new Exception("Invalid entity src");
		
		List modifiersList = (List) type.get("modifiers");
		List implementsList = (List) type.get("implements");
		
		if(modifiersList.size()!=1 || !modifiersList.contains("public"))
			throw new Exception("Invalid entity src");
		
		if(!implementsList.contains("Entity"))
			throw new Exception("Invalid entity src");
		
		// implements
		
		implementsList.remove("Entity");
		Collections.sort(implementsList);
		String implementsStr = String.join(",",implementsList);
		output.put("implements", implementsStr);
		
		// extends
		
		if(type.containsKey("extends"))
		{
			output.put("extends", type.get("extends"));
		}
		output.put("implements", implementsStr);
		
		// nested
		
		if(type.containsKey("nested"))
		{
			output.put("nested", type.get("nested"));
		}
		
		// constructors
		
		if(type.containsKey("constructors"))
		{
			List constructors = (List) type.get("constructors");
			List constructors1 = new ArrayList(constructors);
			
			constructors1.sort(compBySign);
			output.put("constructors", constructors1);
		}
		
		// fields
		
		if(type.containsKey("fields"))
		{
			List fields = (List) type.get("fields");
			List fields1 = new ArrayList(fields);
			
			fields1.sort(compByName);
			output.put("fields", fields1);
		}
		
		// methods
		
		List methods = (List) type.get("methods");
		List methods1 = new ArrayList();
		boolean creationDateMethodFound = false;
		
		for(int i=0;i<methods.size();i++)
		{
			Map method = (Map) methods.get(i);
			if(method.get("name").equals("creationDate"))
				creationDateMethodFound = true;
			else methods1.add(method);
		}
		
		if(!creationDateMethodFound) throw new Exception("CreationDate method not found");
		
		methods1.sort(compBySign);
		output.put("methods", methods1);
		
		return output;
	}
}
