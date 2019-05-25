package com.waterphobiadr.ui.feature.code;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.waterphobiadr.App;
import com.waterphobiadr.R;
import com.waterphobiadr.data.Repository;
import com.waterphobiadr.data.local.pref.Pref;
import com.waterphobiadr.databinding.ActivityCodeBinding;
import com.waterphobiadr.ui.base.BaseActivity;
import com.waterphobiadr.util.ActivityUtil;
import javax.inject.Inject;
/*
 * Created by shayan.rais on 20/12/2017.
 */

public class CodeActivity extends BaseActivity implements CodeContract.View {

    //5 lines
    ActivityCodeBinding binding;
    @Inject
    Repository repository;
    CodePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_code);
        App.getInstance().getComponent().injectCodeActivity(this);

        if(Pref.getIsCodeVerified()) {
            ActivityUtil.openHome(this);
            finish();
        }


        presenter = new CodePresenter(this, repository);
        presenter.setupIntent(getIntent());
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(binding.editext, InputMethodManager.SHOW_IMPLICIT);
        binding.editext.requestFocus();
        binding.editext.setFocusableInTouchMode(true);
        binding.editext.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable mEdit) {
                if(binding.editext.getText().length() >= 5) {
                    if(binding.editext.getText().toString().equals("99887")) {
                        Toast.makeText(CodeActivity.this, "Verified", Toast.LENGTH_SHORT).show();
                        Pref.putIsCodeVerified(true);
                        ActivityUtil.openHome(CodeActivity.this);
                        finish();
                    } else
                        Toast.makeText(CodeActivity.this, "Wrong code, Please try again", Toast.LENGTH_SHORT).show();

                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
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
