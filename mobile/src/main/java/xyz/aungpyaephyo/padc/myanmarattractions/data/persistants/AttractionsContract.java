package xyz.aungpyaephyo.padc.myanmarattractions.data.persistants;

/**
 * Created by Nyein Nyein on 7/10/2016.
 */
import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import xyz.aungpyaephyo.padc.myanmarattractions.MyanmarAttractionsApp;

/**
 * Created by aung on 7/9/16.
 */
public class AttractionsContract {

    //unique
    public static final String CONTENT_AUTHORITY = MyanmarAttractionsApp.class.getPackage().getName();
    //to put url. content:// protocol that use in application like http in web
    //content://xyz.aungpyaephyo.padc.myanmarattractions (like website address)
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //like database table
    public static final String PATH_ATTRACTIONS = "attractions";
    public static final String PATH_ATTRACTION_IMAGES = "attraction_images";
    public static final String PATH_REGISTER = "register";

    //create entry objs/class depending on PATH
    public static final class AttractionEntry implements BaseColumns {

        //create 3 variabls in every entry (CONTENT_URI, DIR_TYPE, ITEM_TYPE)
        //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ATTRACTIONS).build();

        //
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ATTRACTIONS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ATTRACTIONS;

        //table name is same with path name
        public static final String TABLE_NAME = "attractions";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESC = "desc";

        //create helper methods (buildAttractionUri(in every entry),
//        buildAttractionUriWithTitle(query to create with title, id or something), getTitleFromParam(retrieve))
        public static Uri buildAttractionUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildAttractionUriWithTitle(String attractionTitle) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions?title="Yangon"
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TITLE, attractionTitle)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {

            //uri = content://xyz.aungpyaephyo.padc.myanmarattractions/attractions?title="Yangon"
            return uri.getQueryParameter(COLUMN_TITLE);
        }
    }

    public static final class AttractionImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ATTRACTION_IMAGES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ATTRACTION_IMAGES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ATTRACTION_IMAGES;

        public static final String TABLE_NAME = "attraction_images";

        public static final String COLUMN_ATTRACTION_TITLE = "attraction_title";
        public static final String COLUMN_IMAGE = "image";

        public static Uri buildAttractionImageUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildAttractionImageUriWithTitle(String attractionTitle) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images?attraction_title=Yangon
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_ATTRACTION_TITLE, attractionTitle)
                    .build();
        }

        public static String getAttractionTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_ATTRACTION_TITLE);
        }
    }

    public static final class AttractionRegisterEntry implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_REGISTER).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REGISTER;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REGISTER;

        public static final String TABLE_NAME = "register";

        public static final String COLUMN_ATTRACTION_NAME = "name";
        public static final String COLUMN_ATTRACTION_EMAIL = "email";
        public static final String COLUMN_ATTRACTION_PASSOWRD = "password";
        public static final String COLUMN_ATTRACTION_DOB = "date_of_birth";
        public static final String COLUMN_ATTRACTION_COUNTRY = "country_of_origin";

        public static Uri buildAttractionRegisterUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildAttractionRegisterUriWithName(String attractionName) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images?attraction_title=Yangon
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_ATTRACTION_NAME, attractionName)
                    .build();
        }

        public static String getAttractionNameFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_ATTRACTION_NAME);
        }
    }
}