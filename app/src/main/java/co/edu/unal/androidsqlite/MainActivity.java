package co.edu.unal.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.edu.unal.androidsqlite.adapters.ListCompaniesAdapter;
import co.edu.unal.androidsqlite.db.DbCompanies;
import co.edu.unal.androidsqlite.entities.Companies;

public class MainActivity extends AppCompatActivity {

    //Button btncrear;

    RecyclerView viewListCompanies;
    ArrayList<Companies> listCompanies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewListCompanies = findViewById(R.id.listCompanies);
        viewListCompanies.setLayoutManager(new LinearLayoutManager(this));
        listCompanies = new ArrayList<>();


        DbCompanies dbCompanies = new DbCompanies(MainActivity.this);

        ListCompaniesAdapter adapter = new ListCompaniesAdapter(dbCompanies.viewCompanies());
        viewListCompanies.setAdapter(adapter);
        /*btncrear =findViewById(R.id.btnCraer);

        btncrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (db != null){
                    Toast.makeText(MainActivity.this,"Agenda Creada",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Error al crear Agenda",Toast.LENGTH_LONG).show();
                }
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Actualizar la lista de empresas y el adaptador
        DbCompanies dbCompanies = new DbCompanies(MainActivity.this);
        ListCompaniesAdapter adapter = new ListCompaniesAdapter(dbCompanies.viewCompanies());
        viewListCompanies.setAdapter(adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int option = item.getItemId();
        if( R.id.menuNuevo == option) {
            NewRegister();
            return true;

        }
        return super.onOptionsItemSelected(item);

    }

    private void NewRegister(){
        Intent intent = new Intent(this, NewRegisterActivity.class);
        startActivity(intent);
    }
}