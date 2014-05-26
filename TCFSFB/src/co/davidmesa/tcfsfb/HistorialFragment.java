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
public class HistorialFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	
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
//	private TextView txtPeso;
//	private TextView txtEstatura;
//	private TextView txtSiastole;
//	private TextView txtDiastole;
//	private TextView txtPulso;
//	private CheckBox checkEstatura;

	public HistorialFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_historial, container, false);

		
		return rootView;
	}
}

