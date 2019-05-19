package com.waterphobiadr.ui.feature.main;

/*
 * Created by shayan.rais on 20/12/2017.
 */

import com.waterphobiadr.ui.base.BasePresenter;
import com.waterphobiadr.ui.base.BaseView;

public class MainContract {

    public interface View extends BaseView<Presenter> {
        void setupToolbar();
        void setupLayout();
        void setupClickListeners();
        void updateDoctor();
    }

    public interface Presenter extends BasePresenter {

    }
}
