package a.entity.gus06.sys.webserver1.web2.site.pseudo.action.send;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.security.PublicKey;
import java.sql.Connection;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141016";}


	private Service generateCode;
	private Service findPublicKey;
	private Service verifySign;
	private Service jdbcConnection;
	private Service executeCmd;
	
	private PrintStream out;


	public EntityImpl() throws Exception
	{
		generateCode = Outside.service(this,"gus06.data.generate.string.random.alphanum8");
		findPublicKey = Outside.service(this,"gus06.sys.webserver1.web2.site.pseudo.retrieve.publickey");
		verifySign = Outside.service(this,"gus06.crypto.asymkeys.signature.dsa.verify.hexa");
		jdbcConnection = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.jdbc.connection");
		executeCmd = Outside.service(this,"gus06.sys.webserver1.web2.site.pseudo.execute");
		
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		R mr = (R) obj;
		
		Map auth = initAuth(mr);
		
		String _pseudo = getPost(mr,"pseudo");
		String _sign = getPost(mr,"sign");
		String _cmd = getPost(mr,"cmd");
		String _cmd1 = getPost(mr,"cmd1");
		
		if(_pseudo==null) {failed(auth,mr,1);return;}
		if(_cmd==null) {failed(auth,mr,2);return;}
		
		String pseudo = checkPseudo(auth,_pseudo);
		if(pseudo==null) {failed(auth,mr,3);return;}
		
		String code = get(auth,"code");
		if(code==null)
		{
			
			String newCode = newCode();
			auth.put("code",newCode);
			print(mr,"sign:"+newCode);
			return;
		}
		
		if(!auth.containsKey("key"))
		{
			if(_sign==null) {failed(auth,mr,4);return;}
			
			Connection cx = findConnection(mr);
			if(cx==null) {failed(auth,mr,5);return;}
			
			PublicKey key = findPublicKey(cx,pseudo);
			if(key==null) {failed(auth,mr,6);return;}
			
			boolean ok = verifySign(key,code,_sign);
			if(!ok) {failed(auth,mr,6);return;}
			
			auth.put("key",key);
			out.println("pseudo authentified: "+pseudo);
		}
		
		out.println("cmd from "+pseudo+": "+cmdDesc(_cmd,_cmd1));
		executeCmd.p(new Object[]{mr,pseudo,_cmd});
	}
	
	
	
	
	private String cmdDesc(String cmd, String cmd1)
	{
		if(cmd1!=null) return cmd+"|"+cmd1;
		return cmd;
	}
	
	
	
	
	private Map initAuth(R mr) throws Exception
	{
		if(!((F)mr).f("session auth"))
			((V)mr).v("session auth",new HashMap());
		return (Map) mr.r("session auth");
	}
	
	
	
	private String checkPseudo(Map auth, String _v)
	{
		String v = get(auth,"pseudo");
		if(v==null) {auth.put("pseudo",_v);return _v;}
		if(!v.equals(_v)) return null;
		return v;
	}
	
	
	
	private void failed(Map auth, R mr, int code) throws Exception
	{
		auth.clear();
		print(mr,"error:"+code);
	}
	
	
	private String getPost(R mr, String key) throws Exception
	{
		if(!((F) mr).f("params_post "+key)) return null;
		return (String) mr.r("params_post "+key);
	}
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}

	
	private String newCode() throws Exception
	{return (String) generateCode.g();}
	
	
	private Connection findConnection(R mr) throws Exception
	{return (Connection) jdbcConnection.t(mr);}
	
	
	private PublicKey findPublicKey(Connection cx, String pseudo) throws Exception
	{return (PublicKey) findPublicKey.t(new Object[]{cx,pseudo});}
	
	
	private boolean verifySign(PublicKey key, String code, String sign) throws Exception
	{return verifySign.f(new Object[]{key,code,sign});}
	
	
	private void print(R mr, String res) throws Exception
	{((P) mr.r("data h")).p(res);}
}
