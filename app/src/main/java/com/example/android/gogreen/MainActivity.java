package com.example.android.gogreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.grid);
        //getSupportActionBar().hide();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = (NavigationView) findViewById(R.id.nav_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch(menuItem.getItemId())
                {
                    case R.id.menu_home :
                        Toast.makeText(getApplicationContext(),"Home panel is open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_setting:
                        Toast.makeText(getApplicationContext(),"setting panel is open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_contact:
                        Toast.makeText(getApplicationContext(),"contact panel is open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_privacy:
                        Toast.makeText(getApplicationContext(),"privacy policy panel is open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_about:
                        Toast.makeText(getApplicationContext(),"About panel is open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_share:
                        Toast.makeText(getApplicationContext(),"Share panel is open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }
                return true;
            }
        });


        listindata();

       }

    private void listindata() {

        ApiInterface apiInterface = Retrofit.getRetrofit().create(ApiInterface.class);
        Call<Pojo>listindata = apiInterface.getData();
        listindata.enqueue(new Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                gridAdapter adapter = new gridAdapter(response.body().getData());
                grid.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });


    }

    class gridAdapter extends BaseAdapter{
        List<Pojo.Datum> list;

        public gridAdapter(List<Pojo.Datum> list) {
            this.list = list;
        }

        @Override
           public int getCount() {
               return list.size();
           }

           @Override
           public Object getItem(int i) {
               return null;
           }

           @Override
           public long getItemId(int i) {
               return 0;
           }

           @Override
           public View getView(int i, View view, ViewGroup viewGroup) {
               View v = getLayoutInflater().inflate(R.layout.gridlayout,viewGroup,false);
               ImageView pic = v.findViewById(R.id.pic);
               TextView name = v.findViewById(R.id.name);

               name.setText(list.get(i).getFirstName());
               Picasso.get()
                       .load(list.get(i).getAvatar())
                       .placeholder(R.drawable.ic_launcher_foreground)
                       .fit()
                       .into(pic);


               return v;
           }
       }
}