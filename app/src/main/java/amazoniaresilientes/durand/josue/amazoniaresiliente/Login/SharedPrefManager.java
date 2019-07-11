package amazoniaresilientes.durand.josue.amazoniaresiliente.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "volleyregisterlogin";
    private static final String KEY_PRIMER_NOMBRE = "keyprimerNombre";
    private static final String KEY_SEGUNDO_NOMBRE = "keysegundoNombre";
    private static final String KEY_APELLIDO_PATERNO = "keyapellidoPaterno";
    private static final String KEY_APELLIDO_MATERNO = "keyapellidoMaterno";
    private static final String KEY_ESTADO_CIVIL = "keyestadoCivil";
    private static final String KEY_DNI = "keydni";
    private static final String KEY_CORREO = "keycorreo";

    private static final String KEY_CELULAR = "keycelular";
    private static final String KEY_ID = "keyid";
    private static SharedPrefManager mInstance;
    private static Context ctx;

    private SharedPrefManager(Context context) {
        ctx = context;
    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will store the user data in shared preferences
    public void userLogin(nthc_Admin user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_PRIMER_NOMBRE, user.getPrimer_nombre());
        editor.putString(KEY_SEGUNDO_NOMBRE, user.getSegundo_nombre());
        editor.putString(KEY_APELLIDO_PATERNO, user.getApellido_paterno());
        editor.putString(KEY_APELLIDO_MATERNO, user.getApellido_materno());
        editor.putString(KEY_ESTADO_CIVIL, user.getEstado_civil());
        editor.putString(KEY_DNI, user.getDni());
        editor.putString(KEY_CORREO, user.getCorreo());

        editor.putString(KEY_CELULAR, user.getCelular());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CORREO, null) != null;
    }

    //this method will give the logged in user
    public nthc_Admin getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new nthc_Admin(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_PRIMER_NOMBRE, null),
                sharedPreferences.getString(KEY_SEGUNDO_NOMBRE, null),
                sharedPreferences.getString(KEY_APELLIDO_PATERNO, null),
                sharedPreferences.getString(KEY_APELLIDO_MATERNO, null),
                sharedPreferences.getString(KEY_ESTADO_CIVIL, null),
                sharedPreferences.getString(KEY_DNI, null),
                sharedPreferences.getString(KEY_CORREO, null),
                sharedPreferences.getString(KEY_CELULAR, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, LoginActivity.class));
    }
}