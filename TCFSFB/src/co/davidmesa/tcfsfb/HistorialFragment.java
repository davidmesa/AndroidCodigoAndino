package co.davidmesa.tcfsfb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import co.cristiansierra.entidades.ReporteIMC;
import co.cristiansierra.entidades.ReporteIMCAdapter;
import co.cristiansierra.entidades.ReporteTension;
import co.cristiansierra.entidades.ReporteTensionAdapter;
import co.cristiansierra.entidades.Usuario;
import co.rolon.js.comunicacion.Comunicacion;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class HistorialFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	public final static String URL = "http://157.253.224.36:8080/TeleConsulta-war/cliente/movil/";
	/**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 */
	public static HistorialFragment newInstance(int sectionNumber) {
		HistorialFragment fragment = new HistorialFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	private View rootView;
	public static ListView listIMC;
	public static ListView listTension;
	private Button btnConsultarIMC;
	private Button btnConsultarTension;
	private TextView lblSaludoReporteIMC;
	private TextView lblSaludoReportePresion;
	private Fragment instancia;

	public HistorialFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		instancia = this;
		rootView = inflater.inflate(R.layout.fragment_historial, container, false);

		btnConsultarIMC = (Button) rootView.findViewById(R.id.btnConsultarIMC);
		btnConsultarTension = (Button) rootView.findViewById(R.id.btnConsultarTension);
		listIMC = (ListView) rootView.findViewById(R.id.listIMC);
		listTension = (ListView) rootView.findViewById(R.id.listTension);
		lblSaludoReporteIMC = (TextView) rootView.findViewById(R.id.lblSaludoReporteIMC);
		lblSaludoReportePresion = (TextView) rootView.findViewById(R.id.lblSaludoReportePresion);



		//		OBTENER LOS REPORTES
		//		String datosIMC=Comunicacion.send("reportesimc", new CustomHashMap(), 1);
		//		
		//		String datosTension=Comunicacion.send("reportesimc", new CustomHashMap(), 1);
		//		FIN OBTENER LOS REPORTES

		//		listIMC.setAdapter(new ReporteIMCAdapter(rootView.getContext(), reportesIMC));
		//		listTension.setAdapter(new ReporteTensionAdapter(rootView.getContext(), reportesTension));

		btnConsultarIMC.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new ReportesIMCTask(instancia.getActivity()).execute(URL + "reportesimc" + "?" + "token="+ Usuario.darInstancia().getToken());

				listIMC.setVisibility(View.VISIBLE);
				lblSaludoReporteIMC.setVisibility(View.VISIBLE);
				listTension.setVisibility(View.GONE);
				lblSaludoReportePresion.setVisibility(View.GONE);
			}
		});

		btnConsultarTension.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new ReportesTensionTask(instancia.getActivity()).execute(URL + "reportestension" + "?" + "token="+ Usuario.darInstancia().getToken());

				listIMC.setVisibility(View.GONE);
				lblSaludoReporteIMC.setVisibility(View.GONE);
				listTension.setVisibility(View.VISIBLE);
				lblSaludoReportePresion.setVisibility(View.VISIBLE);
			}
		});

		return rootView;
	}
}

class ReportesIMCTask extends AsyncTask<String, String, String>{

	Activity asociada;

	public ReportesIMCTask(Activity param)
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
			String mensaje=(String) a.get("mensaje");
			System.out.println("Reporte IMC\n"+mensaje);

			List<ReporteIMC> reportesIMC = new ArrayList<ReporteIMC>();

			String[] reportes=mensaje.split("*");
			if(reportes.length>2)
			{
				for(int i=1; i<reportes.length-1; i++)
				{
					String[] datos=reportes[i].split("-");
					reportesIMC.add(new ReporteIMC(Double.parseDouble(datos[1]), Double.parseDouble(datos[2]), datos[0]));
				}
			}
			HistorialFragment.listIMC.setAdapter(new ReporteIMCAdapter(asociada, reportesIMC));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class ReportesTensionTask extends AsyncTask<String, String, String>{

	Activity asociada;

	public ReportesTensionTask(Activity param)
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
			String mensaje=(String) a.get("mensaje");
			System.out.println("REPORTE TENSION\n"+mensaje);

			List<ReporteTension> reportesTension = new ArrayList<ReporteTension>();

			String[] reportes=mensaje.split("*");
			if(reportes.length>2)
			{
				for(int i=1; i<reportes.length-1; i++)
				{
					String[] datos=reportes[i].split("-");
					reportesTension.add(new ReporteTension(Integer.parseInt(datos[2]), Integer.parseInt(datos[1]), Integer.parseInt(datos[3]), datos[0]));
				}
			}

			HistorialFragment.listTension.setAdapter(new ReporteTensionAdapter(asociada, reportesTension));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
