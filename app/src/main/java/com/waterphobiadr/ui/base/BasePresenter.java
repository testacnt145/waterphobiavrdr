package com.waterphobiadr.ui.base;

import android.content.Intent;
import android.os.Bundle;

/*
 * Created by shayan.rais on 27/01/2018.
 */

public interface BasePresenter {
    void setupIntent(Intent intent);
    void setupFragmentIntent(Bundle bundle);
}
