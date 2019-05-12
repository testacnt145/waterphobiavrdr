package com.waterphobiadr.di.component;


import com.waterphobiadr.App;
import com.waterphobiadr.di.Singleton;
import com.waterphobiadr.di.module.DataSourceModule;
import com.waterphobiadr.ui.feature.about.AboutActivity;
import com.waterphobiadr.ui.feature.doctorlist.DoctorListActivity;
import com.waterphobiadr.ui.feature.main.MainActivity;
import com.waterphobiadr.ui.feature.newdoctor.NewDoctorActivity;
import com.waterphobiadr.ui.feature.patientdetail.PatientDetailActivity;
import com.waterphobiadr.ui.feature.patientlist.PatientListActivity;
import dagger.Component;
/*
 * Created by asad on 12/11/2017.
 */

@Singleton
@Component(modules = {DataSourceModule.class})
public interface ApplicationComponent {

    void inject(App app);

    //______________________________________________________________________________________________
    void injectMainActivity(MainActivity activity);
    void injectNewDoctorActivity(NewDoctorActivity activity);
    void injectAboutActivity(AboutActivity activity);
    void injectPatientListActivity(PatientListActivity activity);
    void injectDoctorListActivity(DoctorListActivity activity);
    void injectPatientDetailActivity(PatientDetailActivity activity);
}