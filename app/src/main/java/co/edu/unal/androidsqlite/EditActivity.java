package co.edu.unal.androidsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import co.edu.unal.androidsqlite.db.DbCompanies;
import co.edu.unal.androidsqlite.entities.Companies;

public class EditActivity extends AppCompatActivity {

    EditText txtNombre,txtUrl,txtTelefono,txtCorreoElectronico,txtTipo,txtProductos;
    Button btnSave;

    Button btnVolver;

    Companies company;

    int id = 0;
    FloatingActionButton fabEdit;
    FloatingActionButton fabDelete;

    boolean flag = false;
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

        fabEdit = findViewById(R.id.floatEdit);
        fabEdit.setVisibility(View.INVISIBLE);
        fabDelete = findViewById(R.id.deleteEdit);
        fabDelete.setVisibility(View.INVISIBLE);

        btnSave = findViewById(R.id.btnGuarda);

        btnVolver = findViewById(R.id.botonVolver);
        btnVolver.setVisibility(View.INVISIBLE);

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

        final DbCompanies dbCompanies = new DbCompanies(EditActivity.this);
        company =dbCompanies.viewCompany(id);

        if (company != null){
            txtNombre.setText(company.getName().toString());
            txtUrl.setText(company.getUrl().toString());
            txtTelefono.setText(company.getTelefono().toString());
            txtCorreoElectronico.setText(company.getCorreo_electronico().toString());
            txtTipo.setText(company.getTipo().toString());
            txtProductos.setText(company.getProductos().toString());

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!txtNombre.getText().toString().equals("")){
                        flag = dbCompanies.editCompany(id, txtNombre.getText().toString(),
                        txtUrl.getText().toString(),
                        txtTelefono.getText().toString(),
                        txtCorreoElectronico.getText().toString(),
                        txtTipo.getText().toString(),
                        txtProductos.getText().toString());
                        if (flag){
                            Toast.makeText(EditActivity.this,"Registro actualizado correctamente",Toast.LENGTH_LONG).show();
                            viewCompinies();
                        }else{
                            Toast.makeText(EditActivity.this,"Error al actualizar",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(EditActivity.this,"Modifica nombre",Toast.LENGTH_LONG).show();
                    }
                }
            });

        }


    }
    private void viewCompinies(){
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }
}
