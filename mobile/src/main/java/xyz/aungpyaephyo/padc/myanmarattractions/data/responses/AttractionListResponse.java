package xyz.aungpyaephyo.padc.myanmarattractions.data.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import xyz.aungpyaephyo.padc.myanmarattractions.data.vos.AttractionVO;

/**
 * Created by Nyein Nyein on 7/9/2016.
 */
public class AttractionListResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("attraction")
    private ArrayList<AttractionVO> attractionList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<AttractionVO> getAttractionList() {
        return attractionList;
    }
}

