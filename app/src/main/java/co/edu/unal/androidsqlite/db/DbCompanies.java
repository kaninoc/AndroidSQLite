package co.edu.unal.androidsqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbCompanies extends DbHelper {

    Context context;

    public DbCompanies(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertCompany(String nombre, String url, String telefono,String correo_electronico,String tipo,String productos) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("url", url);
            values.put("correo_electronico", correo_electronico);
            values.put("tipo", tipo);
            values.put("telefono", telefono);
            values.put("productos", productos);

            id = db.insert(TABLE_NAME, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }


}
