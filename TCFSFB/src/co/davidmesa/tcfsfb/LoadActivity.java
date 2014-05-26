package co.davidmesa.tcfsfb;

import javax.crypto.Cipher;

import co.rolon.js.comunicacion.Comunicacion;
import co.rolon.js.estructuras.CustomHashMap;
import co.rolon.js.seguridad.Seguridad;
import co.rolon.js.seguridad.Transformacion;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);

		System.out.println("Inicio");
		try {
//			System.out.println(Comunicacion.send("auth", new CustomHashMap().put("id", "cristiansierra").put("password", "algo"), 1));
			
			usuario = (EditText) findViewById(R.id.txtUsuario);
			contrasenia = (EditText) findViewById(R.id.txtContrasenia);
			btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);
			btnIniciarSesion.setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							//PROCEDIMIENTO
							try {
//								System.out.println(Comunicacion.send("auth", new CustomHashMap().put("id", "cristiansierra").put("password", "algo"), 1));
								
//								String simetrica=Comunicacion.send("getSymmetricKey", new CustomHashMap(), 1);
//								String oculto=(Comunicacion.send("auth", new CustomHashMap().put("id", usuario.toString()).put("password", contrasenia.toString()), 1));
//								REVISAR PROCEDIMIENTO DESCIFRADO
//								c.init(Cipher.ENCRYPT_MODE, KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(destransformar(PRIVATE_KEY))));
//						        transformar(c.doFinal(destransformar(SYMMETRIC_KEY)));								
//								String token=Seguridad.descifrarSimetrico(simetrica, oculto);
								
//								Comparar si lo que descifro esta bien, si esta mal, arroja Excepcion
								
								finish();
								Intent homepage = new Intent(LoadActivity.this, Main.class);
								startActivity(homepage);
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
