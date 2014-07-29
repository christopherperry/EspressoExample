package com.ensolabs.espressoexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;


public class MyActivity extends Activity {
  @Inject List<Person> people;
  @InjectView(R.id.list) ListView listView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my);
    ButterKnife.inject(this);
    ObjectGraph.create(new MyModule()).inject(this);

    listView.setAdapter(new MyAdapter(people, getLayoutInflater()));
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Person person = people.get(position);
        Intent intent = new Intent(MyActivity.this, PersonDetailsActivity.class);
        intent.putExtra(PersonDetailsActivity.EXTRA_PERSON, person);
        startActivity(intent);
      }
    });
  }

  private static class MyAdapter extends BaseAdapter {
    private final List<Person> people;
    private final LayoutInflater inflater;

    private MyAdapter(List<Person> people, LayoutInflater inflater) {
      this.people = people;
      this.inflater = inflater;
    }

    @Override public int getCount() {
      return people.size();
    }

    @Override public Person getItem(int position) {
      return people.get(position);
    }

    @Override public long getItemId(int position) {
      return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
      View view = inflater.inflate(R.layout.row, parent, false);
      ((TextView) view.findViewById(R.id.firstName)).setText(getItem(position).getFirstName());
      ((TextView) view.findViewById(R.id.lastName)).setText(getItem(position).getLastName());
      return view;
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.my, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Module(
    injects = MyActivity.class
  )
  public static class MyModule {
    @Provides @Singleton public List<Person> providePeople() {
      ArrayList<Person> people = new ArrayList<Person>();
      for (int i = 0; i < 50; i++) {
        people.add(new Person("Arthur", String.valueOf(i)));
      }
      return people;
    }
  }

  public static class Person implements Serializable {
    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

    public String getFirstName() {
      return firstName;
    }

    public String getLastName() {
      return lastName;
    }
  }
}
