package sp.com;
//check all the ways  for db.read docs
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.view.View;

        import androidx.annotation.Nullable;

public class Fchelpermodel extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "restaurantlist.db";
    private static final int SCHEMA_VERSION = 1;

    SQLiteDatabase db =null;



    private Context context;


    public Fchelpermodel(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE restaurants_table (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, email TEXT, adminnum TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public Cursor getAll() {
        return (getReadableDatabase().rawQuery(
                "SELECT _id, name, email, adminnum FROM restaurants_table ORDER BY name", null));
//get everything.No where.

    }
//    public Cursor getById(String id){
//       String[] args={id} ;
//        return (getReadableDatabase().rawQuery("SELECT _id, name, email, adminnum " +
//                "FROM restaurants_table WHERE _ID=?", args)); //where means you only find id that match the id .
//    }
//    public Cursor getLastID(){
////        String[] args = {id}; //make an array
//
//
//        return (getReadableDatabase().rawQuery("SELECT MAX(Id) FROM restaurants_table",null)); //where means you only find id that match the id .
//    }

    public void insert(String name,String adminnum, String email) { //getting variables from table
       db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("email",email);
        cv.put("adminnum",adminnum);
        db.insert("restaurants_table",null,cv);
    }

    public void delete(){

        db = this.getWritableDatabase();//cannot helper?
        db.delete("restaurants_table", null,null);

    }


//    another method in the database calls update
//    public void update(String id, String restaurantName,String restaurantAddress,String restaurantTel,String restaurantType){
//        ContentValues cv = new ContentValues();
//        String[] args ={id};
//        cv.put("restaurantName",restaurantName);
//        cv.put("restaurantAddress",restaurantAddress);
//        cv.put("restaurantTel",restaurantTel);
//        cv.put("restaurantType",restaurantType);
//
////        GETWIRTABLE DATABASE GOT ID TO  MATCH
//        getWritableDatabase().update("restaurants_table", cv,"_ID =?",args);
//
//    }





    public String getID(Cursor c){return(c.getString(0));} //coloumn index is the id

    public String getName(Cursor c) {
        return (c.getString(1));
    }

    public String getemail(Cursor c) {
        return (c.getString(2));
    }

    public String getadminnum(Cursor c) {
        return (c.getString(3));
    }

}
//if file format diff just change to .jpg file extension.Wont matteer


