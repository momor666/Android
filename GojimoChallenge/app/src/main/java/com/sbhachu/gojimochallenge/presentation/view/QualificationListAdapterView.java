package com.sbhachu.gojimochallenge.presentation.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbhachu.gojimochallenge.R;
import com.sbhachu.gojimochallenge.data.model.Qualification;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * QualificationListAdapterView.class
 * Uses ViewHolder pattern and binds qualification object data to the view
 */
@EViewGroup(R.layout.view_qualification_list_item)
public class QualificationListAdapterView extends LinearLayout {

    @ViewById(R.id.qualification_name_tv)
    public TextView qualificationNameTextView;

    @ViewById(R.id.list_arrow)
    public ImageView listArrow;

    public QualificationListAdapterView(Context context) {
        super(context);
    }

    public void bind(Qualification qualification) {
        if (qualification != null) {
            qualificationNameTextView.setText(qualification.getName());
        }
    }
}
