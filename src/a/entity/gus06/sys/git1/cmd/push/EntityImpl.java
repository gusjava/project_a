package a.entity.gus06.sys.git1.cmd.push;

import a.framework.*;
import java.util.Map;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20240402";}

	public static final String KEY_USER = "user";
	public static final String KEY_PWD = "pwd";
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof Git) handleGit((Git) obj);
		else if(obj instanceof Object[]) handleArray((Object[]) obj);
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private void handleArray(Object[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Git git = (Git) o[0];
		Map data = (Map) o[1];
		
		PushCommand cmd = git.push();
		
		String user = get(data, KEY_USER);
		String pwd = get(data, KEY_PWD);
		
		if(user!=null && pwd!=null)
			cmd.setCredentialsProvider(new UsernamePasswordCredentialsProvider("user", "pwd"));
		
		cmd.call();
	}
	
	private void handleGit(Git git) throws Exception
	{
		PushCommand cmd = git.push();
		cmd.call();
	}
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}