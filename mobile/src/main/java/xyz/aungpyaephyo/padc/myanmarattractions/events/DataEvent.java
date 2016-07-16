package xyz.aungpyaephyo.padc.myanmarattractions.events;

import java.util.List;

import xyz.aungpyaephyo.padc.myanmarattractions.data.vos.AttractionVO;

/**
 * Created by Nyein Nyein on 7/9/2016.
 */
public class DataEvent {

    public static class AttractionDataLoadedEvent {
        private String extraMessage;
        private List<AttractionVO> attractionList;

        public AttractionDataLoadedEvent(String extraMessage, List<AttractionVO> attractionList) {
            this.extraMessage = extraMessage;
            this.attractionList = attractionList;

        }

        public String getExtraMessage() {
            return extraMessage;
        }
    }
}

