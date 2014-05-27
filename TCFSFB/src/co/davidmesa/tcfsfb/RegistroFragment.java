package co.davidmesa.tcfsfb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import co.cristiansierra.entidades.Usuario;
import co.rolon.js.comunicacion.Comunicacion;
import co.rolon.js.estructuras.CustomHashMap;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class RegistroFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	public final static String URL = "http://192.168.0.10:8080/TeleConsulta-war/cliente/movil/";
	
	/**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 */
	public static RegistroFragment newInstance(int sectionNumber) {
		RegistroFragment fragment = new RegistroFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	private View rootView;
	private TextView txtPeso;
	private TextView txtEstatura;
	private TextView txtSiastole;
	private TextView txtDiastole;
	private TextView txtPulso;
	private CheckBox checkEstatura;
	public Fragment instancia;
	
	public RegistroFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		instancia = this;
		rootView = inflater.inflate(R.layout.fragment_registro, container, false);

		txtPeso=(TextView) rootView.findViewById(R.id.txtPeso);
		txtEstatura=(TextView) rootView.findViewById(R.id.txtEstatura);
		txtSiastole=(TextView) rootView.findViewById(R.id.txtSiastole);
		txtDiastole=(TextView) rootView.findViewById(R.id.txtDiastole);
		txtPulso=(TextView) rootView.findViewById(R.id.txtPulso);
		checkEstatura=(CheckBox) rootView.findViewById(R.id.checkEstatura);
		
		checkEstatura.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						if (checkEstatura.isChecked()) {
							txtEstatura.setVisibility(View.VISIBLE);
						}
						else
						{
							txtEstatura.setVisibility(View.GONE);
						}
					}
				});
		
		
		  
        
		rootView.findViewById(R.id.btnEnviarIMC).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						try {
							if(!checkEstatura.isChecked())
							{
								new RegistrarTask(instancia.getActivity()).execute(URL + "imc" + "?" + "token="+ Usuario.darInstancia().getToken() + "&peso=" + txtPeso.getText() + "&altura=" + "0");
							}
							else
							{
								new RegistrarTask(instancia.getActivity()).execute(URL + "imc" + "?" + "token="+ Usuario.darInstancia().getToken() + "&peso=" + Double.parseDouble((String) txtPeso.getText()) + "&altura=" + Integer.parseInt((String) txtEstatura.getText()));
							}
							txtPeso.setText("");
							txtEstatura.setText("");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

		rootView.findViewById(R.id.btnEnviarPresion).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						try {
							System.out.println("REQUEST");
							System.out.println(URL + "tension" + "?" + "token="+ Usuario.darInstancia().getToken() + "&diastole=" + txtDiastole.getText() + "&siastole=" + txtSiastole.getText() + "&pulso="+txtPulso.getText());
							new RegistrarTask(instancia.getActivity()).execute(URL + "tension" + "?" + "token="+ Usuario.darInstancia().getToken() + "&diastole=" + txtDiastole.getText() + "&siastole=" + txtSiastole.getText() + "&pulso="+txtPulso.getText());
							txtSiastole.setText("");
							txtDiastole.setText("");
							txtPulso.setText("");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
		return rootView;
	}
}

class RegistrarTask extends AsyncTask<String, String, String>{

	Activity asociada;
	
	public RegistrarTask(Activity param)
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
			String respuesta=(String) a.get("mensaje");
			System.out.println(respuesta);
			
			final AlertDialog.Builder mensaje = new AlertDialog.Builder(asociada);
			mensaje.setTitle("Mensaje");
			mensaje.setPositiveButton("OK",null);
			mensaje.setMessage(respuesta);
			mensaje.create();
			mensaje.show();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
