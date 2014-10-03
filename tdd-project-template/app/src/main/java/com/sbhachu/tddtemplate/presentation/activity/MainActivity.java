package com.sbhachu.tddtemplate.presentation.activity;

import android.app.Activity;
import android.widget.TextView;

import com.sbhachu.tddtemplate.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById(R.id.tv_hello_world)
    public TextView label;

}
