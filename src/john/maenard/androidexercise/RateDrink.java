package john.maenard.androidexercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class RateDrink {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "alcohol_drink";
	public static final String KEY_RATE = "alcohol_rate";

	private static final String DATABASE_NAME = "AlcoholicDrinksDb";
	private static final String DATABASE_TABLE = "alcoholTable";
	private static final int DATABASE_VERSION = 1;

	private DbHelpers ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	private static class DbHelpers extends SQLiteOpenHelper {

		public DbHelpers(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
					+ " TEXT NOT NULL, " + KEY_RATE + " INTEGER NOT NULL);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}

	}

	public RateDrink(Context c) {
		ourContext = c;
	}
	
	public RateDrink open() {
		ourHelper = new DbHelpers(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		ourHelper.close();
	}

	public long createEntry(String bebida, String rango) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, bebida);
		cv.put(KEY_RATE, rango);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public String readData() {
		String[] columns = new String[] {KEY_ROWID, KEY_NAME, KEY_RATE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iRate = c.getColumnIndex(KEY_RATE);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + c.getString(iRow) + " " + c.getString(iName) + " " + c.getString(iRate) + "\n";
		}
		return result;
	}

}
