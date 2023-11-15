package co.edu.unal.androidsqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
                company.setTelefono(cursorCompanies.getString(3));
                company.setCorreo_electronico(cursorCompanies.getString(4));
                /*company.setUrl(cursorCompanies.getString(2));

                company.setCorreo_electronico(cursorCompanies.getString(4));
                company.setTipo(cursorCompanies.getString(5));
                company.setProductos(cursorCompanies.getString(6));*/
                listCompanies.add(company);
            }while (cursorCompanies.moveToNext());
        }
        cursorCompanies.close();
        return listCompanies;
    }

    public Companies viewCompany(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Companies company = null;
        Cursor cursorCompanies = null;

        cursorCompanies =db.rawQuery("SELECT * FROM "+TABLE_NAME + " WHERE id = "+ id + " LIMIT 1",null);

        if (cursorCompanies.moveToFirst()){
                company = new Companies();
                company.setId(cursorCompanies.getInt(0));
                company.setName(cursorCompanies.getString(1));
                company.setTelefono(cursorCompanies.getString(3));
                company.setCorreo_electronico(cursorCompanies.getString(4));
                company.setUrl(cursorCompanies.getString(2));
                company.setTipo(cursorCompanies.getString(5));
                company.setProductos(cursorCompanies.getString(6));

        }
        cursorCompanies.close();
        return company;
    }

    public boolean editCompany(int id, String nombre, String url, String telefono,String correo_electronico,String tipo,String productos) {

        boolean flag = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL(
                    "UPDATE "+TABLE_NAME+
                            " SET nombre = '"+nombre+"'," +
                            "url = '"+url+"'," +
                            "telefono = '"+telefono+"'," +
                            "correo_electronico = '"+correo_electronico+"'," +
                            "tipo = '"+tipo+"'," +
                            "productos = '"+productos+
                            "' WHERE id = '"+id+"'");

            flag =true;

        } catch (Exception ex) {
            ex.toString();
            Log.d("EtiquetaDeLog ", ex.toString());
            flag =false;
        } finally {
            db.close();
        }

        return flag;
    }

    public boolean deleteCompany(int id) {

        boolean flag = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL(
                    "DELETE FROM "+TABLE_NAME+
                            " WHERE id = '"+id+"'");

            flag =true;

        } catch (Exception ex) {
            ex.toString();
            Log.d("EtiquetaDeLog ", ex.toString());
            flag =false;
        } finally {
            db.close();
        }

        return flag;
    }

}
