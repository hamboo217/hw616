package com.example.tttttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    rooDao rooDao;
    rooDatabase rooDatabase;
    TextView textView;
    Button binsert,bupdate,bclear,bdelete;
    EditText editTextname,editTextnum;
    LiveData<List<roo>>allwordLive;
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        myAdapter = new MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        rooDatabase = Room.databaseBuilder(this,rooDatabase.class,"roo_database")
                .allowMainThreadQueries()
                .build();
        rooDao = rooDatabase.getrooDao();
        allwordLive = rooDao.getallwordLive();
//        textView=findViewById(R.id.textView3);
        allwordLive.observe(this, new Observer<List<roo>>() {
            @Override
            public void onChanged(List<roo> roos) {
                myAdapter.setAllWords(roos);
                myAdapter.notifyDataSetChanged();

            }
        });
        binsert=findViewById(R.id.binsert);
        editTextname = (EditText)findViewById(R.id.edname);
        editTextnum =(EditText) findViewById(R.id.ednum);




        ///////////////////////////////////////////////


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            // COMPLETED (4) Override onMove and simply return false inside
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // return false;//管理上下拖曳
//                int position_dragged = viewHolder.getAdapterPosition();
//                int position_target = target.getAdapterPosition();
//                myAdapter.notifyItemMoved(position_dragged, position_target);


                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //管理滑動情形
                int position = viewHolder.getAdapterPosition();
                switch (direction) {
                    case ItemTouchHelper.LEFT:
                    case ItemTouchHelper.RIGHT:
//                        roo word = new roo("sd","123");


                        String ai = findViewById(R.id.textViewnum).toString();
                        if(ai == "1"){
                            rooDao.deleteallword();
                        }

                        myAdapter.notifyItemRemoved(position);
                        break;
                }
            }

            //COMPLETED (11) attach the ItemTouchHelper to the waitlistRecyclerView
        }).attachToRecyclerView(recyclerView);

        //////////////////////////////////////////
    }
    public void bionclick(View view) {
        String name = editTextname.getText().toString();
        String num = editTextnum.getText().toString();
        roo word1 = new roo(name,num);

        rooDao.insertword(word1);



    }

    public void bconclick(View view) {
        rooDao.deleteallword();
        roo a =new roo("d","0");
        a.zero();

    }


    //錯了就把下面刪掉==
//    private void recyclerViewAction(RecyclerView recyclerView, final List<roo> choose, final MyAdapter myAdapter) {
//        ItemTouchHelper helper1 = new ItemTouchHelper(new ItemTouchHelper.Callback() {
//            @Override
//            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//                //return 0;//這裡是告訴RecyclerView你想開啟哪些操作
//                return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
//            }
//
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//               // return false;//管理上下拖曳
//                int position_dragged = viewHolder.getAdapterPosition();
//                int position_target = target.getAdapterPosition();
//                Collections.swap(choose, position_dragged, position_target);
//                myAdapter.notifyItemMoved(position_dragged, position_target);
//
//
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                //管理滑動情形
//                int position = viewHolder.getAdapterPosition();
//                switch (direction) {
//                    case ItemTouchHelper.LEFT:
//                    case ItemTouchHelper.RIGHT:
//                        choose.remove(position);
//                        myAdapter.notifyItemRemoved(position);
//                        break;
//                }
//            }
//
//        });
//    }

}

