package com.gut.follower.activities.track;

import com.gut.follower.activities.BasePresenter;
import com.gut.follower.activities.BaseView;
import com.gut.follower.model.Track;

public interface TrackContract {

    interface View extends BaseView{

        void showTrackInfo(Track track);

    }

    interface Presenter extends BasePresenter<View>{

        void loadTrack(String trackId);
    }
}
