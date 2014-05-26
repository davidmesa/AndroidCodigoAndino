package co.rolon.js.comunicacion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;

import co.rolon.js.estructuras.CustomHashMap;

public class Comunicacion {

	private final static String URL = "http://localhost:8080/TeleConsulta-war/cliente/movil/";
	
//	private static BufferedOutputStream out;
//	
//	private static BufferedInputStream in;

	public static String send(String service, CustomHashMap params, int tries)
			throws Exception {
		String response = "";
//		HttpURLConnection conn = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
		    HttpResponse httpresponse = httpclient.execute(new HttpPost(URL + service + "?" + params.toString()));
		    StatusLine statusLine = httpresponse.getStatusLine();
		    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
		        ByteArrayOutputStream out = new ByteArrayOutputStream();
		        httpresponse.getEntity().writeTo(out);
		        out.close();
		        response = out.toString();
		    } else{
		        //Closes the connection.
		        httpresponse.getEntity().getContent().close();
		        throw new IOException(statusLine.getReasonPhrase());
		    }
//			conn = (HttpURLConnection) new URL(URL).openConnection();
//			conn.setDoOutput(true);
//			conn.setChunkedStreamingMode(0);
//
//			out = new BufferedOutputStream(conn.getOutputStream());
//			writeStream(service, params);
//			
//			in = new BufferedInputStream(conn.getInputStream());
//			response = readStream();
		} catch (IOException e) {
			if (tries == 0) {
				throw new ConnectTimeoutException();
			} else {
				send(service, params, tries - 1);
			}
		} finally {
//			if(conn != null) {
//				conn.disconnect();				
//			}
		}
		return response;
	}
	
//	private static void writeStream(String service, CustomHashMap params) throws Exception {
//		
//	}
//	
//	private static String readStream() throws Exception {
//		return null;
//	}
}
