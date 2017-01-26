package sadaka.com.example.android.sadaka.tasks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by aisha on 8/16/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private final static String DATABASE_NAME="sadakaDB.sqlite";
    private final static int DATABASE_VERSION=1;
    private String pathToSaveDBFile;
    private final static String TAG="DBHELPER";
    private SQLiteDatabase myDataBase;

    public DatabaseHelper(Context context,String filePath){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
        this.pathToSaveDBFile= "/data/data/sadaka.com.example.android.sadaka/databases/"+DATABASE_NAME;
                //new StringBuffer(filePath).append("/").append(DATABASE_NAME).toString();
    }

    public void prepareDB(){
        boolean exists=checkDB();
        if(exists)
        {
            Log.d(TAG, "Database exists.");
           /* int currVersion=getVersionId();
            if(DATABASE_VERSION>currVersion){
                Log.d(TAG, "Database version is higher than old.");
                deleteDB();
                try {
                    copyDB();
                }
                catch(Exception e){
                    Log.e(TAG, e.getMessage());

                }
            }*/
        }
        else {
            this.getReadableDatabase();
            try {
                copyDB();
            }
            catch(Exception e){
                Log.e(TAG, e.getMessage());

            }
        }
    }
    private boolean checkDB(){
        boolean checkDB=false;
        try{
          File file=new File(pathToSaveDBFile);
            checkDB=file.exists();
        }
        catch(Exception e){
            Log.d(TAG,"checking DB");
        }
        return checkDB;

    }

    private void copyDB()throws Exception{
        OutputStream os=new FileOutputStream(pathToSaveDBFile);
        InputStream is=context.getAssets().open(DATABASE_NAME);
        byte [] buffer=new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        os.flush();
        os.close();
        is.close();

    }
    private void deleteDB(){
        File file = new File(pathToSaveDBFile);
        if(file.exists()) {
            file.delete();
            Log.d(TAG, "Database deleted.");
        }
    }

   /* private int getVersionId() {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READONLY);
        String query = "SELECT version_id FROM organizations";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int v =  cursor.getInt(0);

         db.close();
        return v;
    }
*/
    public SQLiteDatabase openDatabase(){
       return myDataBase = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READONLY);
    }
    public void close(){
        if(myDataBase != null)
            myDataBase.close();

        super.close();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}


