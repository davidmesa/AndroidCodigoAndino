package co.davidmesa.tcfsfb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.crypto.Cipher;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import co.cristiansierra.entidades.Usuario;
import co.rolon.js.comunicacion.Comunicacion;
import co.rolon.js.estructuras.CustomHashMap;
import co.rolon.js.seguridad.Seguridad;
import co.rolon.js.seguridad.Transformacion;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class LoadActivity extends ActionBarActivity {

	private EditText usuario;
	private EditText contrasenia;
	private Button btnIniciarSesion;
	public final static String URL = "http://157.253.224.36:8080/TeleConsulta-war/cliente/movil/";
	public Activity instancia;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instancia=this;
		setContentView(R.layout.activity_load);
		System.out.println("Inicio");
		try {
			usuario = (EditText) findViewById(R.id.txtUsuario);
			contrasenia = (EditText) findViewById(R.id.txtContrasenia);
			btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);
			btnIniciarSesion.setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							try {
								System.out.println(URL + "auth" + "?" + "id="+ usuario.getText() + "&password=" + contrasenia.getText());
								new RequestTask(instancia).execute(URL + "auth" + "?" + "id="+ usuario.getText() + "&password=" + contrasenia.getText());

							} catch (Exception e) {
								// TODO Auto-generated catch block
								System.out.println(e);
								System.out.println(e.getMessage());
								e.printStackTrace();
							}
						}
					});
			
		} catch (Exception e) {
			System.out.println(e);
			e.getStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.load, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_load, container,
					false);
			return rootView;
		}
	}
}

class RequestTask extends AsyncTask<String, String, String>{

	Activity asociada;
	
	public RequestTask(Activity param)
	{
		asociada=param;
	}
	
	@Override
    protected String doInBackground(String... uri) {
    	System.out.println("LLEGA INFORMACION DEL POST "+uri[0]);
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
            response = httpclient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems..
        	System.err.println("Error en primer lado");
        	System.err.println(e.getMessage());
        } catch (IOException e) {
            //TODO Handle problems..
        	System.err.println("Error en primer lado");
        	System.err.println(e.getMessage());
        }
        return responseString;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        System.out.println(result);
        try {
			JSONObject a=new JSONObject(result);
			String token=(String) a.get("token");
			System.out.println(token);
			
			if(token!=null)
			{
				Usuario.darInstancia().setToken(token);
				asociada.finish();
				Intent homepage = new Intent(asociada, Main.class);
				asociada.startActivity(homepage);
			}
			else
			{
				asociada.recreate();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
