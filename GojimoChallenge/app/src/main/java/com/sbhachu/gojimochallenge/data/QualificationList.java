package com.sbhachu.gojimochallenge.data;

import com.sbhachu.gojimochallenge.data.model.Qualification;

import java.util.ArrayList;
import java.util.List;

/**
 * QualificationList.class
 *
 * Android Annotations does not like stacked generics when generating code
 * for example, ResponseEntity<List<Qualification>> causes errors.
 *
 * Extending ArrayList<Qualification> removes the stacked generic syntax.
 */
public class QualificationList extends ArrayList<Qualification> {
}
