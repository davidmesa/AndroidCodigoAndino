package co.cristiansierra.entidades;

import java.util.List;

import co.davidmesa.tcfsfb.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ReporteTensionAdapter extends BaseAdapter{

	private Context context;

	private List<ReporteTension> listaReportesTension;

	public ReporteTensionAdapter(Context context, List<ReporteTension> items)
	{
		this.context = context;
		this.listaReportesTension = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listaReportesTension.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listaReportesTension.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return listaReportesTension.get(position).hashCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View roowView = convertView;
	    if (convertView == null) {
	        // Create a new view into the list.
	        LayoutInflater inflater = (LayoutInflater) context
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        roowView = inflater.inflate(R.layout.item_tension, parent, false);
	    }

	    // Set data into the view.
	    TextView fecha = (TextView) roowView.findViewById(R.id.lblFechaTension);
	    TextView imc = (TextView) roowView.findViewById(R.id.lblTension);

	    ReporteTension item = listaReportesTension.get(position);
	    fecha.setText(item.getFechaReporte());
	    imc.setText(item.getSiastole()+"  "+item.getDiastole()+"  "+item.getPulso());

	    return roowView;
	}

}
