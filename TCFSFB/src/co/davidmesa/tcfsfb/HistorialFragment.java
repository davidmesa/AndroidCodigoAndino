package co.davidmesa.tcfsfb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import co.cristiansierra.entidades.ReporteIMC;
import co.cristiansierra.entidades.ReporteIMCAdapter;
import co.cristiansierra.entidades.ReporteTension;
import co.cristiansierra.entidades.ReporteTensionAdapter;
import co.rolon.js.comunicacion.Comunicacion;
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
	private ListView listIMC;
	private ListView listTension;
	private Button btnConsultarIMC;
	private Button btnConsultarTension;
	private TextView lblSaludoReporteIMC;
	private TextView lblSaludoReportePresion;

	public HistorialFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_historial, container, false);

		btnConsultarIMC = (Button) rootView.findViewById(R.id.btnConsultarIMC);
		btnConsultarTension = (Button) rootView.findViewById(R.id.btnConsultarTension);
		listIMC = (ListView) rootView.findViewById(R.id.listIMC);
		listTension = (ListView) rootView.findViewById(R.id.listTension);
		lblSaludoReporteIMC = (TextView) rootView.findViewById(R.id.lblSaludoReporteIMC);
		lblSaludoReportePresion = (TextView) rootView.findViewById(R.id.lblSaludoReportePresion);
		
		List<ReporteIMC> reportesIMC = new ArrayList<ReporteIMC>();
		List<ReporteTension> reportesTension = new ArrayList<ReporteTension>();
		
//		OBTENER LOS REPORTES
//		String datosIMC=Comunicacion.send("reportesimc", new CustomHashMap(), 1);
//		
//		String datosTension=Comunicacion.send("reportesimc", new CustomHashMap(), 1);
//		FIN OBTENER LOS REPORTES
		
		reportesIMC.add(new ReporteIMC(56, 140, new Date((long) (1000000*Math.random()))));
		reportesIMC.add(new ReporteIMC(57, 142, new Date((long) (1000000*Math.random()))));
		reportesIMC.add(new ReporteIMC(60, 143, new Date((long) (1000000*Math.random()))));
		
		reportesTension.add(new ReporteTension((int) (100*Math.random()), (int) (100*Math.random()), (int) (100*Math.random()), new Date((long) (1000000*Math.random()))));
		reportesTension.add(new ReporteTension((int) (100*Math.random()), (int) (100*Math.random()), (int) (100*Math.random()), new Date((long) (1000000*Math.random()))));
		reportesTension.add(new ReporteTension((int) (100*Math.random()), (int) (100*Math.random()), (int) (100*Math.random()), new Date((long) (1000000*Math.random()))));
		reportesTension.add(new ReporteTension((int) (100*Math.random()), (int) (100*Math.random()), (int) (100*Math.random()), new Date((long) (1000000*Math.random()))));
		
		listIMC.setAdapter(new ReporteIMCAdapter(rootView.getContext(), reportesIMC));
		listTension.setAdapter(new ReporteTensionAdapter(rootView.getContext(), reportesTension));

		btnConsultarIMC.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				listIMC.setVisibility(View.VISIBLE);
				lblSaludoReporteIMC.setVisibility(View.VISIBLE);
                listTension.setVisibility(View.GONE);
                lblSaludoReportePresion.setVisibility(View.GONE);
			}
		});
        
        btnConsultarTension.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	listIMC.setVisibility(View.GONE);
            	lblSaludoReporteIMC.setVisibility(View.GONE);
                listTension.setVisibility(View.VISIBLE);
                lblSaludoReportePresion.setVisibility(View.VISIBLE);
            }
        });
        
		return rootView;
	}
}

