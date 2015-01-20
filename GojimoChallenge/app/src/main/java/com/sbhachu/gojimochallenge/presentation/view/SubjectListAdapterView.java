package com.sbhachu.gojimochallenge.presentation.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbhachu.gojimochallenge.R;
import com.sbhachu.gojimochallenge.data.model.Subject;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;

/**
 * SubjectListAdapterView.class
 * Uses ViewHolder pattern and binds subject object data to the view
 */
@EViewGroup(R.layout.view_subject_list_item)
public class SubjectListAdapterView extends LinearLayout {

    @ViewById(R.id.color_block)
    public View colorBlock;

    @ViewById(R.id.subject_name_tv)
    public TextView subjectNameTextView;

    public SubjectListAdapterView(Context context) {
        super(context);
    }

    public void bind(Subject subject) {

        colorBlock.setBackgroundColor(0xFFFFFFFF);

        if (subject != null) {
            subjectNameTextView.setText(subject.getTitle());

            if (subject.getColour() == null || subject.getColour().isEmpty()) {
                return;
            }

            int color = Integer.parseInt(StringUtils.remove(subject.getColour(), '#'), 16) + 0xFF000000;
            colorBlock.setBackgroundColor(color);
        }
    }
}
