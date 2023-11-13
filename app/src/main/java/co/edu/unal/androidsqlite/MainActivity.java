package co.edu.unal.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import co.edu.unal.androidsqlite.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    Button btncrear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btncrear =findViewById(R.id.btnCraer);

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
        });
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