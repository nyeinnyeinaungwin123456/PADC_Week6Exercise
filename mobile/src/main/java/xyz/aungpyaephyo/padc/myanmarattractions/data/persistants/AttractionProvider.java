package xyz.aungpyaephyo.padc.myanmarattractions.data.persistants;

/**
 * Created by Nyein Nyein on 7/10/2016.
 */
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by aung on 7/10/16.
 */
public class AttractionProvider extends ContentProvider {

    //declare variable according to PATH or TABLE
    public static final int ATTRACTION = 100;
    public static final int ATTRACTION_IMAGE = 200;
    public static final int ATTRACTION_REGISTER = 300;

    private static final String sAttractionTitleSelection = AttractionsContract.AttractionEntry.COLUMN_TITLE + " = ?";
    private static final String sAttractionImageSelectionWithTitle = AttractionsContract.AttractionImageEntry.COLUMN_ATTRACTION_TITLE + " = ?";
    private static final String sAttractionRegisterSelectionWithName = AttractionsContract.AttractionRegisterEntry.COLUMN_ATTRACTION_NAME + " = ?";

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private AttractionDBHelper mAttractionDBHelper;

    @Override
    public boolean onCreate() {
        mAttractionDBHelper = new AttractionDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
//    String[] projection = return column names
//    selection = condition from where (title = ? AND region =?) not contain values
//    selectionArgs = contain values from where condition
//    sortOrder = to sort what column and what type
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor queryCursor;

        int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case ATTRACTION:
                String attractionTitle = AttractionsContract.AttractionEntry.getTitleFromParam(uri);
                if (!TextUtils.isEmpty(attractionTitle)) {
                    selection = sAttractionTitleSelection;
                    selectionArgs = new String[]{attractionTitle};
                }
                queryCursor = mAttractionDBHelper.getReadableDatabase().query(AttractionsContract.AttractionEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, //group_by
                        null, //having
                        sortOrder);
                break;
            case ATTRACTION_IMAGE:
                String title = AttractionsContract.AttractionImageEntry.getAttractionTitleFromParam(uri);
                if (title != null) {
                    selection = sAttractionImageSelectionWithTitle;
                    selectionArgs = new String[]{title};
                }
                queryCursor = mAttractionDBHelper.getReadableDatabase().query(AttractionsContract.AttractionImageEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            case ATTRACTION_REGISTER:
                String name = AttractionsContract.AttractionRegisterEntry.getAttractionNameFromParam(uri);
                if (name != null) {
                    selection = sAttractionRegisterSelectionWithName;
                    selectionArgs = new String[]{name};
                }
                queryCursor = mAttractionDBHelper.getReadableDatabase().query(AttractionsContract.AttractionRegisterEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        Context context = getContext();
        if (context != null) {
            queryCursor.setNotificationUri(context.getContentResolver(), uri);
        }

        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case ATTRACTION:
                return AttractionsContract.AttractionEntry.DIR_TYPE;
            case ATTRACTION_IMAGE:
                return AttractionsContract.AttractionImageEntry.DIR_TYPE;
            case ATTRACTION_REGISTER:
                return AttractionsContract.AttractionRegisterEntry.DIR_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
//        only useable writabledb to insert
        final SQLiteDatabase db = mAttractionDBHelper.getWritableDatabase();
        final int matchUri = sUriMatcher.match(uri);
        Uri insertedUri;

        switch (matchUri) {
            case ATTRACTION: {
                long _id = db.insert(AttractionsContract.AttractionEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    //create uri using appending _id, get path uri
                    insertedUri = AttractionsContract.AttractionEntry.buildAttractionUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case ATTRACTION_IMAGE: {
                long _id = db.insert(AttractionsContract.AttractionImageEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = AttractionsContract.AttractionImageEntry.buildAttractionImageUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }

            case ATTRACTION_REGISTER: {
                long _id = db.insert(AttractionsContract.AttractionRegisterEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = AttractionsContract.AttractionRegisterEntry.buildAttractionRegisterUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        Context context = getContext();
        if (context != null) {

//            atuo refresh when changes in dbtable and then notify
            //uri is from insert
            //to access content provide = getContentResolver()
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedUri;
    }

//
    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mAttractionDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
//            count size of array and add to ContentValues
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            //commit not to increase data every time
            db.endTransaction();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mAttractionDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mAttractionDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }

    //For each path, relate to int constant
    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AttractionsContract.CONTENT_AUTHORITY, AttractionsContract.PATH_ATTRACTIONS, ATTRACTION);
        uriMatcher.addURI(AttractionsContract.CONTENT_AUTHORITY, AttractionsContract.PATH_ATTRACTION_IMAGES, ATTRACTION_IMAGE);
        uriMatcher.addURI(AttractionsContract.CONTENT_AUTHORITY, AttractionsContract.PATH_REGISTER, ATTRACTION_REGISTER);

        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);

        switch (matchUri) {
            case ATTRACTION:
                return AttractionsContract.AttractionEntry.TABLE_NAME;
            case ATTRACTION_IMAGE:
                return AttractionsContract.AttractionImageEntry.TABLE_NAME;

            case ATTRACTION_REGISTER:
                return AttractionsContract.AttractionImageEntry.TABLE_NAME;

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }
}