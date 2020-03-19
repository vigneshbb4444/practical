package com.mcapractical;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    ListView listView;
    List<String> ExercisesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        listView = (ListView)findViewById(R.id.listView);

        ExercisesList.clear();
        ExercisesList.add("Form Design for Mobile Application");
        ExercisesList.add("Application using Controls");
        ExercisesList.add("Graphical and Multimedia Application");
        ExercisesList.add("Data Retrieval Application");
        ExercisesList.add("Networking Application");
        ExercisesList.add("Gaming Application");

        MyAdapter adapter = new MyAdapter(HomePage.this,ExercisesList);
        listView.setAdapter(adapter);
    }

    class MyAdapter extends BaseAdapter
    {
        Context context;
        List<String> list;

        public MyAdapter(Context context, List<String> list) {
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
            view = layoutInflater.inflate(R.layout.home_list_items
                    , viewGroup, false);

            TextView textView = (TextView) view.findViewById(R.id.exe_title);
            textView.setText(list.get(i));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(i==0)
                    {
                        Intent mainIntent = new Intent(context,FormDesign.class);
                        startActivity(mainIntent);
                    }
                    else if(i==1)
                    {
                        Intent mainIntent = new Intent(context,ApplicationControls.class);
                        startActivity(mainIntent);
                    }
                    else if(i==2)
                    {
                        Intent mainIntent = new Intent(context,Multimedia.class);
                        startActivity(mainIntent);
                    }
                    else if(i==3)
                    {
                        Intent mainIntent = new Intent(context,DataRetrieval.class);
                        startActivity(mainIntent);
                    }
                    else if(i==4)
                    {
                        Intent mainIntent = new Intent(context,Networking.class);
                        startActivity(mainIntent);
                    }

                }
            });

            return view;
        }
    }

}
