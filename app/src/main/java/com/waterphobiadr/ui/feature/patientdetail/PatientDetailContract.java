package com.waterphobiadr.ui.feature.patientdetail;

import com.waterphobiadr.ui.base.BasePresenter;
import com.waterphobiadr.ui.base.BaseView;
/*
 * Created by shayan.rais on 20/12/2017.
 */

public class PatientDetailContract {

    public interface View extends BaseView<Presenter> {
        void setupToolbar();
        void setupLayout();
        void setupClickListeners();
    }

    public interface Presenter extends BasePresenter {

    }
}
