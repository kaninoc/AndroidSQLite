package co.edu.unal.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import co.edu.unal.androidsqlite.db.DbCompanies;
import co.edu.unal.androidsqlite.entities.Companies;

public class ViewActivity extends AppCompatActivity {

    EditText txtNombre,txtUrl,txtTelefono,txtCorreoElectronico,txtTipo,txtProductos;
    Button addSave;

    Button botonVolver;

    FloatingActionButton fabEdit;

    FloatingActionButton fabDelete;
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
        fabEdit = findViewById(R.id.floatEdit);
        fabDelete = findViewById(R.id.deleteEdit);

        botonVolver = findViewById(R.id.botonVolver);

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

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ViewActivity.this,EditActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewActivity.this);
                builder.setMessage("Desea eleminar el registro ?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(dbCompanies.deleteCompany(id)){
                                    listDelete();
                                }

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void listDelete(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}