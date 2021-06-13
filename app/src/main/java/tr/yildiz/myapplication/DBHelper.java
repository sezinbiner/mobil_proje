package tr.yildiz.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){
        super(context, "dolabım.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table drawers(_id Integer primary key, drawerName Text)");
        myDB.execSQL("create Table clothes(_id Integer primary key, drawerName Text, clothesType Text, color Text, pattern Text, date Text, price Text, image blob)");
        myDB.execSQL("create Table parties(_id Integer primary key, partyName Text, partyType Text, partyDate Text, combines Text)");
        myDB.execSQL("create Table combines(_id Integer primary key, name Text, head Text, face Text, body Text, legs Text, feet Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion){
        myDB.execSQL("drop Table if exists drawers");
        myDB.execSQL("drop Table if exists clothes");
        myDB.execSQL("drop Table if exists parties");
        myDB.execSQL("drop Table if exists combines");
    }

    public Boolean insertCombine(String name ,String head,String face, String body, String legs, String feet) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("head", head);
        contentValues.put("face", face);
        contentValues.put("body", body);
        contentValues.put("legs", legs);
        contentValues.put("feet", feet);
        long result = myDB.insert("combines", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean insertDrawer(String name) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("drawerName", name);
        long result = myDB.insert("drawers", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean insertParty(String partyName, String partyType, String partyDate, String combines) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("partyName", partyName);
        contentValues.put("partyType", partyType);
        contentValues.put("partyDate", partyDate);
        contentValues.put("combines", combines);
        long result = myDB.insert("parties", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    Cursor readAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("select drawerName from drawers", null);
        }
        return cursor;
    }


    Cursor readPartyData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("select partyName from parties", null);
        }
        return cursor;
    }


    Cursor readCombines(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("select name from combines", null);
        }
        return cursor;
    }

    Cursor getCombine(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("select * from combines where name = ?", new String[]{name});
        }
        return cursor;
    }

    public void insertImage(String drawerName, String clothesType, String color, String pattern, String date, String price, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO clothes VALUES (NULL, ?, ?, ?, ? , ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, drawerName);
        statement.bindString(2, clothesType);
        statement.bindString(3,color);
        statement.bindString(4,pattern);
        statement.bindString(5,date);
        statement.bindString(6,price);
        statement.bindBlob(7,image);

        statement.executeInsert();
    }

    long deleteDrawer(String drawerName){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("drawers", "drawerName = ?", new String[]{drawerName});
        return result;
    }


    long deleteParty(String drawerName){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("parties", "partyName = ?", new String[]{drawerName});
        return result;
    }


    long deleteImage(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("clothes", "_id = ?", new String[]{id});
        return result;
    }

    public void deleteCombine(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("combines", "name = ?", new String[]{name});
    }





    public Cursor getImages(String drawerName){
        String sql = "SELECT image FROM clothes WHERE drawerName = ?";
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("select image from clothes where drawerName = ?", new String[] {drawerName});
    }

    public Cursor getImagesId(String drawerName){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("select _id from clothes where drawerName = ?", new String[] {drawerName});
    }

    public void deleteImage(int id){
        SQLiteDatabase db = getWritableDatabase();
        String new_id = String.valueOf(id);
        db.delete("clothes", "_id = ?", new String[]{new_id});

    }

    public Cursor getImage(String id){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("select image from clothes where _id = ?", new String[] {id});
    }


}
