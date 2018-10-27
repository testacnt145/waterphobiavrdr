package com.waterphobiadr.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;
/*
 * Created by asad on 12/9/2017.
 */

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface Singleton {
}