package co.edu.unal.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.unal.androidsqlite.db.DbCompanies;

public class NewRegisterActivity extends AppCompatActivity {

    EditText txtNombre,txtUrl,txtTelefono,txtCorreoElectronico,txtTipo,txtProductos;
    Button addRegister;

    Button botonVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Agregar Company");
        txtNombre = findViewById(R.id.txtNombre);
        txtUrl = findViewById(R.id.txtUrl);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        txtTipo = findViewById(R.id.txtTipo);
        txtProductos = findViewById(R.id.txtProductos);

        addRegister = findViewById(R.id.btnGuarda);
        botonVolver = findViewById(R.id.botonVolver);

        addRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbCompanies dbCompanies = new DbCompanies(NewRegisterActivity.this);
                long id = dbCompanies.insertCompany(
                        txtNombre.getText().toString(),
                        txtUrl.getText().toString(),
                        txtTelefono.getText().toString(),
                        txtCorreoElectronico.getText().toString(),
                        txtTipo.getText().toString(),
                        txtProductos.getText().toString());

                if ( id>0 ){
                    Toast.makeText(NewRegisterActivity.this,"Nuevo contacto Agregado",Toast.LENGTH_LONG).show();
                    limpiar();
                }else{
                    Toast.makeText(NewRegisterActivity.this,"Error en Agregar contacto",Toast.LENGTH_LONG).show();
                }
            }
        });

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void limpiar(){
        txtNombre.setText("");
        txtUrl.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
        txtTipo.setText("");
        txtProductos.setText("");
    }
}