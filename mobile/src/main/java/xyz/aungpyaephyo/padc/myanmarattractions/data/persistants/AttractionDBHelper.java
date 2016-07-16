package xyz.aungpyaephyo.padc.myanmarattractions.data.persistants;

/**
 * Created by Nyein Nyein on 7/10/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import xyz.aungpyaephyo.padc.myanmarattractions.data.persistence.AttractionsContract.AttractionEntry;
//import xyz.aungpyaephyo.padc.myanmarattractions.data.persistence.AttractionsContract.AttractionImageEntry;

/**
 * Created by aung on 7/9/16.
 */
public class AttractionDBHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "attractions.db";

    //create constant table(sql commands with cap letter)
    private static final String SQL_CREATE_ATTRACTION_TABLE = "CREATE TABLE " + AttractionsContract.AttractionEntry.TABLE_NAME + " (" +
            AttractionsContract.AttractionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            AttractionsContract.AttractionEntry.COLUMN_TITLE + " TEXT NOT NULL, "+
            AttractionsContract.AttractionEntry.COLUMN_DESC + " TEXT NOT NULL, "+

            //constrain ON CONFLICT IGNORE/REPLACE(not to add or replace data to table)
            //to reduce db operation
            //should identify for every table
            //if we don't identify, db can add every time.
            " UNIQUE (" + AttractionsContract.AttractionEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";

    //create constraints
    private static final String SQL_CREATE_ATTRACTION_IMAGE_TABLE = "CREATE TABLE " + AttractionsContract.AttractionImageEntry.TABLE_NAME + " (" +
            AttractionsContract.AttractionImageEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            AttractionsContract.AttractionImageEntry.COLUMN_ATTRACTION_TITLE + " TEXT NOT NULL, "+
            AttractionsContract.AttractionImageEntry.COLUMN_IMAGE + " TEXT NOT NULL, "+

            " UNIQUE (" + AttractionsContract.AttractionImageEntry.COLUMN_ATTRACTION_TITLE + ", "+
            AttractionsContract.AttractionImageEntry.COLUMN_IMAGE + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_ATTRACTION_REGISTER_TABLE = "CREATE TABLE " + AttractionsContract.AttractionRegisterEntry.TABLE_NAME + " (" +
            AttractionsContract.AttractionRegisterEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            AttractionsContract.AttractionRegisterEntry.COLUMN_ATTRACTION_NAME + " TEXT NOT NULL, "+
            AttractionsContract.AttractionRegisterEntry.COLUMN_ATTRACTION_EMAIL + " TEXT NOT NULL, "+
            AttractionsContract.AttractionRegisterEntry.COLUMN_ATTRACTION_PASSOWRD + " TEXT NOT NULL, "+
            AttractionsContract.AttractionRegisterEntry.COLUMN_ATTRACTION_DOB + " TEXT NOT NULL, "+
            AttractionsContract.AttractionRegisterEntry.COLUMN_ATTRACTION_COUNTRY + " TEXT NOT NULL, "+


            " UNIQUE (" + AttractionsContract.AttractionRegisterEntry.COLUMN_ATTRACTION_NAME + ") ON CONFLICT IGNORE" +
            " );";

    public AttractionDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //db is not created at first time and call this method
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ATTRACTION_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ATTRACTION_IMAGE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ATTRACTION_REGISTER_TABLE);
    }

    //how to increase db version
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AttractionsContract.AttractionImageEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AttractionsContract.AttractionEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AttractionsContract.AttractionRegisterEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}