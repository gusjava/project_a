package a.entity.gus06.app.certificate.info;

import a.framework.*;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20141005";}


	private Service getCertificate;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMmdd_HHmmss");


	public EntityImpl() throws Exception
	{getCertificate = Outside.service(this,"gus06.app.certificate");}
	
	
	
	public Object g() throws Exception
	{
		X509Certificate x509 = (X509Certificate) getCertificate.g();
		Map infoMap = new HashMap();
		
		Date notAfter = x509.getNotAfter();
		Date notBefore = x509.getNotBefore();
		boolean isValid = checkValidity(x509);
		X500Principal x500 = x509.getIssuerX500Principal();
		
		String[] nn = x500.getName().split(",");
		for(String n:nn)
		{
			String[] k = n.split("=");
			infoMap.put(k[0],k[1]);
		}
		
		infoMap.put("validity",""+isValid);
		infoMap.put("starttime",sdf.format(notBefore));
		infoMap.put("endtime",sdf.format(notAfter));
	
		return infoMap;
	}
	
	
	
	private boolean checkValidity(X509Certificate x509)
	{
		try
		{
			x509.checkValidity();
			return true;
		}
		catch(Exception e)
		{return false;}
	}
}
