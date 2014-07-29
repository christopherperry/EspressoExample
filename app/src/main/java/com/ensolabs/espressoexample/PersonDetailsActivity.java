package com.ensolabs.espressoexample;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.ensolabs.espressoexample.MyActivity.Person;

public class PersonDetailsActivity extends Activity {
  public static final String EXTRA_PERSON = "person";
  @InjectView(R.id.fullName) TextView fullNameView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.person_details_activity);
    ButterKnife.inject(this);

    Person person = (Person) getIntent().getSerializableExtra(EXTRA_PERSON);
    fullNameView.setText(String.format("%s, %s", person.getLastName(), person.getFirstName()));
  }
}
