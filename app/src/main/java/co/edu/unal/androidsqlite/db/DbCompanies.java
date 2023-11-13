package co.edu.unal.androidsqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import co.edu.unal.androidsqlite.entities.Companies;

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

    public ArrayList<Companies> viewCompanies(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Companies> listCompanies = new ArrayList<>();
        Companies company = null;
        Cursor cursorCompanies = null;

        cursorCompanies =db.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        if (cursorCompanies.moveToFirst()){
            do {
                company = new Companies();
                company.setId(cursorCompanies.getInt(0));
                company.setName(cursorCompanies.getString(1));
                /*company.setUrl(cursorCompanies.getString(2));
                company.setTelefono(cursorCompanies.getString(3));
                company.setCorreo_electronico(cursorCompanies.getString(4));
                company.setTipo(cursorCompanies.getString(5));
                company.setProductos(cursorCompanies.getString(6));*/
                listCompanies.add(company);
            }while (cursorCompanies.moveToNext());
        }
        cursorCompanies.close();
        return listCompanies;
    }


}
