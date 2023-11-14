package co.edu.unal.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.edu.unal.androidsqlite.db.DbCompanies;
import co.edu.unal.androidsqlite.entities.Companies;

public class ViewActivity extends AppCompatActivity {

    EditText txtNombre,txtUrl,txtTelefono,txtCorreoElectronico,txtTipo,txtProductos;
    Button addSave;

    Companies company;

    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtNombre = findViewById(R.id.txtNombre);
        txtUrl = findViewById(R.id.txtUrl);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        txtTipo = findViewById(R.id.txtTipo);
        txtProductos = findViewById(R.id.txtProductos);

        addSave = findViewById(R.id.btnGuarda);

        if (savedInstanceState == null){
            Bundle extra = getIntent().getExtras();
            if (extra == null){
                id = Integer.parseInt(null);
            }else{
                id = extra.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbCompanies dbCompanies = new DbCompanies(ViewActivity.this);
        company =dbCompanies.viewCompany(id);

        if (company != null){
            txtNombre.setText(company.getName().toString());
            txtUrl.setText(company.getUrl().toString());
            txtTelefono.setText(company.getTelefono().toString());
            txtCorreoElectronico.setText(company.getCorreo_electronico().toString());
            txtTipo.setText(company.getTipo().toString());
            txtProductos.setText(company.getProductos().toString());

            addSave.setVisibility(View.INVISIBLE);

            txtNombre.setInputType(InputType.TYPE_NULL);
            txtUrl.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtCorreoElectronico.setInputType(InputType.TYPE_NULL);
            txtTipo.setInputType(InputType.TYPE_NULL);
            txtProductos.setInputType(InputType.TYPE_NULL);
        }
    }
}