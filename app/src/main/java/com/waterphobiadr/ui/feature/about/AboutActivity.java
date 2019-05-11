package com.waterphobiadr.ui.feature.about;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.waterphobiadr.App;
import com.waterphobiadr.R;
import com.waterphobiadr.data.Repository;
import com.waterphobiadr.databinding.ActivityAboutBinding;
import com.waterphobiadr.ui.base.BaseActivity;
import javax.inject.Inject;
/*
 * Created by shayan.rais on 20/12/2017.
 */

public class AboutActivity extends BaseActivity implements AboutContract.View {

    //5 lines
    ActivityAboutBinding binding;
    @Inject
    Repository repository;
    AboutPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        App.getInstance().getComponent().injectAboutActivity(this);
        presenter = new AboutPresenter(this, repository);
        presenter.setupIntent(getIntent());
    }


    //______________________________________________________________________________________________ CREATE
    @Override
    public void setupToolbar() {
    }

    @Override
    public void setupLayout() {
    }

    @Override
    public void setupClickListeners() {
    }
}
