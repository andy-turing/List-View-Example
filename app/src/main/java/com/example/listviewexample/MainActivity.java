package com.example.listviewexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<String> names;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    private int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        names = getAllNames();
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager = new GridLayoutManager(this,2);
        mAdapter = new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {
                Toast.makeText(MainActivity.this, name + "- " + position, Toast.LENGTH_SHORT).show();
                deleteNames(position);
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    private List<String> getAllNames(){
        return  new ArrayList<String>(){{
            add("Alejandra");
            add("Jose");
            add("Bere");
            add("Mauricio");
            add("Jorge");
            add("Alejandra");
            add("Jose");
            add("Bere");
            add("Mauricio");
            add("Jorge");
        }};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                    this.addNames(0);
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void addNames(int position){
        names.add(position, "New name"+(++counter));
        mAdapter.notifyItemInserted(position);
        recyclerView.scrollToPosition(position);

    }

    private void deleteNames(int position){
        names.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
