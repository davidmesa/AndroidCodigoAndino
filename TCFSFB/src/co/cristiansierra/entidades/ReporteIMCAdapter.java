package co.cristiansierra.entidades;

import java.util.List;

import co.davidmesa.tcfsfb.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ReporteIMCAdapter extends BaseAdapter {

	private Context context;
	
	private List<ReporteIMC> listaReportesIMC;
	
	public ReporteIMCAdapter(Context context, List<ReporteIMC> items)
	{
		this.context = context;
		this.listaReportesIMC = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listaReportesIMC.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listaReportesIMC.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return listaReportesIMC.get(position).hashCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View roowView = convertView;
	    if (convertView == null) {
	        // Create a new view into the list.
	        LayoutInflater inflater = (LayoutInflater) context
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        roowView = inflater.inflate(R.layout.item_imc, parent, false);
	    }

	    // Set data into the view.
	    TextView fecha = (TextView) roowView.findViewById(R.id.lblFechaIMC);
	    TextView imc = (TextView) roowView.findViewById(R.id.lblIMC);

	    ReporteIMC item = listaReportesIMC.get(position);
	    fecha.setText(item.getFechaString());
	    imc.setText((int) item.getIMC()+"="+item.getPeso()+"/"+item.getAltura()+"^2");

	    return roowView;
	}
}
