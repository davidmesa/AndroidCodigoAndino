package co.davidmesa.tcfsfb;

import co.rolon.js.comunicacion.Comunicacion;
import co.rolon.js.estructuras.CustomHashMap;
import android.app.AlertDialog;
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

	public RegistroFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

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
		
		final AlertDialog.Builder mensaje = new AlertDialog.Builder(rootView.getContext());
		mensaje.setTitle("Mensaje");
		mensaje.setPositiveButton("OK",null);
		  
        
		rootView.findViewById(R.id.btnEnviarIMC).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						try {
							String respuesta=Comunicacion.send("imc", new CustomHashMap().put("peso", txtPeso.toString()).put("altura", txtEstatura.toString()), 1);
							System.out.println(respuesta);
//							Descifra la respuesta si esta bien, continua sino arroja excepcion
							mensaje.setMessage(respuesta);
							mensaje.create();
							mensaje.show();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							mensaje.setMessage(e.getMessage());
							mensaje.create();
							mensaje.show();
						}
					}
				});

		rootView.findViewById(R.id.btnEnviarPresion).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						try {
							String respuesta=Comunicacion.send("tension", new CustomHashMap().put("siastole", txtSiastole.toString()).put("disatole", txtDiastole.toString()).put("pulso", txtPulso.toString()), 1);
							System.out.println(respuesta);
//							Descifra la respuesta si esta bien, continua sino arroja excepcion
							mensaje.setMessage(respuesta);
							mensaje.create();
							mensaje.show();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							mensaje.setMessage(e.getMessage());
							mensaje.create();
							mensaje.show();
						}
					}
				});
		return rootView;
	}
}

