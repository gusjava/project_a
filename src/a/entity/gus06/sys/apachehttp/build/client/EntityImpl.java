package a.entity.gus06.sys.apachehttp.build.client;

import a.framework.*;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20171021";}
	
	
	
	private HttpClient client;
	
	public Object g() throws Exception
	{
		if(client==null) initClient();
		return client;
	}
	
	
	private void initClient() throws Exception
	{
		// setup a Trust Strategy that allows all certificates.
		TrustStrategy trustStrategy = new TrustStrategy() {
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException
			{return true;}
		};
	
		SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
		sslContextBuilder.loadTrustMaterial(null,trustStrategy);
		SSLContext sslContext = sslContextBuilder.build();

		// don't check Hostnames, either.
		// -- use SSLConnectionSocketFactory.getDefaultHostnameVerifier(),
		// if you don't want to weaken
		HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();

		// here's the special part:
		// -- need to create an SSL Socket Factory, to use our weakened
		// "trust strategy";
		// -- and create a Registry, to register it.
		//
		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", sslSocketFactory)
				.build();

		// now, we create connection-manager using our Registry.
		// -- allows multi-threaded use
		PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		
		RequestConfig defaultRequestConfig = RequestConfig
			.custom()
			.setConnectTimeout(50000)
			.setExpectContinueEnabled(true)
			.build();
		
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setConnectionManager(connMgr);
		httpClientBuilder.setSSLContext(sslContext);
		httpClientBuilder.setDefaultRequestConfig(defaultRequestConfig);
		
		client = httpClientBuilder.build();
	}
}