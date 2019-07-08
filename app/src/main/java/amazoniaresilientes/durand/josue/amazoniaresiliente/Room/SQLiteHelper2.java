package amazoniaresilientes.durand.josue.amazoniaresiliente.Room;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper2 extends SQLiteOpenHelper {
    public SQLiteHelper2(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String cultivo, String primer_nombre, String segundo_nombre, String apellido_paterno, String apellido_materno, String estado_civil, String dni, String referencia_predio, String departamento_cliente, String poligono, String area, String precision, byte[] imagen1, String lat1, String lng1, byte[] imagen2, String lat2, String lng2, byte[] imagen3, String lat3, String lng3, byte[] imagen4, String lat4, String lng4){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO AMAZONIA VALUES ( NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, cultivo);
        statement.bindString(2, primer_nombre);
        statement.bindString(3, segundo_nombre);
        statement.bindString(4, apellido_paterno);
        statement.bindString(5, apellido_materno);
        statement.bindString(6, estado_civil);
        statement.bindString(7, dni);
        statement.bindString(8, referencia_predio);
        statement.bindString(9, departamento_cliente);
        statement.bindString(10, poligono);
        statement.bindString(11, area);
        statement.bindString(12, precision);
        statement.bindBlob(13, imagen1);
        statement.bindString(14, lat1);
        statement.bindString(15, lng1);
        statement.bindBlob(16, imagen2);
        statement.bindString(17, lat2);
        statement.bindString(18, lng2);
        statement.bindBlob(19, imagen3);
        statement.bindString(20, lat3);
        statement.bindString(21, lng3);
        statement.bindBlob(22, imagen4);
        statement.bindString(23, lat4);
        statement.bindString(24, lng4);

        statement.executeInsert();
    }


    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
