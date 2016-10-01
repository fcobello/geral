package br.com.android.rastreador;

import org.alfredlibrary.AlfredException;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import br.com.android.rastreador.registros.Registro;

public class Principal extends Activity
{
	private EditText txtCodigo;
	private EditText txtDescricao;
	private Button btnOk;
	private ListView list;
	private Registro objRegistro;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.rastreamento);
		txtCodigo = (EditText) findViewById(R.id.txtCodigo);
		txtDescricao = (EditText) findViewById(R.id.txtTag);
		list = (ListView) findViewById(R.id.lstRegistros);
		btnOk = ((Button) findViewById(R.id.btnRastrear));
		list.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				final View view = arg1;
				DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						String registro = ((TextView)view).getText().toString();
						objRegistro.removeRegistro(registro.substring(0, 13));
						update();
					}
				};
				Utils.showConfirmation(arg1.getContext(), "Deseja Remover o Item?", listener);
			}
		});
		btnOk.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				try
				{
					objRegistro.addRegistro(txtCodigo.getText().toString(), txtDescricao.getText().toString());
					update();
				}
				catch(AlfredException e)
				{
					Utils.showMessage(arg0.getContext(), e.getMessage());
				}
			}
		});
	}
	
	@Override
	public void onStart()
	{
		try
		{
			objRegistro = new Registro();
			super.onStart();
			update();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Utils.showMessage(this, e.getMessage());
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.itmSair:
				super.finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private void update()
	{
		ArrayAdapter<String> adapter;

		try
		{
			adapter = new ArrayAdapter<String>(list.getContext(), R.layout.list_line, objRegistro.loadRegistros());
			list.setAdapter(adapter);
		}
		catch (AlfredException e)
		{
			Utils.showMessage(this, e.getMessage(), "OK", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					finish();
				}
			});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}