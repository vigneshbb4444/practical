package com.mcapractical;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DataRetrieval extends AppCompatActivity {

    DatabaseHandler db;
    List<Contact> contacts = new ArrayList<>();
    EditText name, number;
    Button button;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_retrieval);

        db = new DatabaseHandler(this);
        contacts = db.getAllContacts();

        name = (EditText) findViewById(R.id.name);
        number = (EditText) findViewById(R.id.number);
        button = (Button) findViewById(R.id.submit);
        listView = (ListView) findViewById(R.id.listView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString() != null && number.getText().toString() != null)
                {
                    db.addContact(new Contact(name.getText().toString(),number.getText().toString()));
                    contacts = db.getAllContacts();
                    MyAdapter adapter = new MyAdapter(DataRetrieval.this,contacts);
                    listView.setAdapter(adapter);
                }

            }
        });

        MyAdapter adapter = new MyAdapter(DataRetrieval.this,contacts);
        listView.setAdapter(adapter);
    }

    class MyAdapter extends BaseAdapter
    {
        Context context;
        List<Contact> list;

        public MyAdapter(Context context, List<Contact> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.data_list_items
                    , viewGroup, false);

            TextView name = (TextView) view.findViewById(R.id.name);
            TextView number = (TextView) view.findViewById(R.id.number);

            name.setText(list.get(i)._name);
            number.setText(list.get(i)._phone_number);

            return view;
        }
    }

}
