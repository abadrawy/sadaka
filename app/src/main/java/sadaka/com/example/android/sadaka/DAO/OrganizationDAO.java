package sadaka.com.example.android.sadaka.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sadaka.com.example.android.sadaka.tasks.DatabaseHelper;

/**
 * Created by aisha on 8/17/2016.
 */
public class OrganizationDAO {
    Context context;
    String filePath;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;


    public OrganizationDAO(Context context,String filePath){
        this.context=context;
        this.filePath=filePath;
        databaseHelper = new DatabaseHelper(context,filePath);
        databaseHelper.prepareDB();

    }


    public List<Organization> getOrganizations(){
        Log.d("test","test4");
         db=databaseHelper.openDatabase();
        Log.d("test","test5");
        String query = "SELECT * FROM org";
        Cursor cursor = db.rawQuery(query, null);
        Log.d("test","test6");
        List<Organization> list = new ArrayList<Organization>();
        while(cursor.moveToNext()) {

            String name=cursor.getString(0);
            String nameEng=cursor.getString(1);
            String desc=cursor.getString(2);
            String descEng=cursor.getString(3);
            String smsNum=cursor.getString(4);
            String smsText=cursor.getString(5);
            String website=cursor.getString(6);
            String facebook=cursor.getString(7);
            String img=cursor.getString(8);
            String number=cursor.getString(9);
            Organization organization = new Organization(name,nameEng,desc,descEng,smsNum,smsText,website,facebook,img,number);

            list.add(organization);
        }
       databaseHelper.close();
        return list;
    }

}
