package co.secbi.gesea;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import java.util.Locale;
import co.secbi.gesea.io.ApiAdapter;
import co.secbi.gesea.io.model.LoginResponse;
import co.secbi.gesea.io.model.getUserResponse;
import co.secbi.gesea.ui.fragment.ProgramacionCalendarFragment;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private HorizontalCalendar horizontalCalendar;
    private TextView usernameTextView, emailTextView, fullnameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar(); // Setear Toolbar como action bar

        Resources res = getApplicationContext().getResources();

        Locale locale = new Locale("es");
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;

        res.updateConfiguration(config, res.getDisplayMetrics());

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {

            setupDrawerContent(navigationView);
            View headerLayout = navigationView.getHeaderView(0);
            usernameTextView = (TextView) headerLayout.findViewById(R.id.username);
            emailTextView = (TextView) headerLayout.findViewById(R.id.mail);

            setLoggedUser();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.main_content, new ProgramacionCalendarFragment())
                    .commit();
        }

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    public void setActionBarTitle(String t){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setTitle(t);
    }



    public void setLoggedUser(){
        Call<getUserResponse> call = ApiAdapter.getApiService(getApplicationContext()).getUser();
        call.enqueue(new Callback<getUserResponse>(){

            @Override
            public void onResponse(Call<getUserResponse> call, Response<getUserResponse> response) {


                try {

                    usernameTextView.setText(response.body().username);
                    emailTextView.setText(response.body().email) ;


                    Log.i("setLoggedUser", response.body().toString());

                } catch(NullPointerException e){

                    e.printStackTrace();

                }


            }
            @Override
            public void onFailure(Call<getUserResponse> call, Throwable t) {


                t.printStackTrace();

            }

        });

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Marcar item presionado
                        menuItem.setChecked(true);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        switch (menuItem.getItemId()) {
                            case R.id.nav_actividades:
                                fragmentManager
                                        .beginTransaction()
                                        .replace(R.id.main_content, new ProgramacionCalendarFragment())
                                        .commit();
                                drawerLayout.closeDrawers(); // Cerrar drawer
                                return true;

                            case R.id.nav_asistencia:
                                return true;
                            case R.id.nav_estudiantes:
                                return true;

                            case R.id.nav_log_out:

                                Call<LoginResponse> call = ApiAdapter.getApiService(getApplicationContext()).logout();
                                call.enqueue(new Callback<LoginResponse>(){

                                    @Override
                                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                                        try {

                                            Toast.makeText(getApplicationContext(), "Sesion cerrada con exito" , Toast.LENGTH_SHORT).show();

                                            SharedPrefsCookiePersistor e = new SharedPrefsCookiePersistor(getApplicationContext());

                                            e.clear();


                                            Intent main = new Intent(MainActivity.this, LoginActivity.class);
                                            startActivity(main);
                                            finish();

                                        } catch(NullPointerException e){

                                            e.printStackTrace();

                                        }


                                    }
                                    @Override
                                    public void onFailure(Call<LoginResponse> call, Throwable t) {


                                        t.printStackTrace();

                                    }

                                });


                                return true;
                        }

                        return onNavigationItemSelected(menuItem);

                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
