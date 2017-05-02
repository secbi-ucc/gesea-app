package co.secbi.gesea;

import android.content.Intent;
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
import android.widget.Toast;

import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import co.secbi.gesea.io.ApiAdapter;
import co.secbi.gesea.io.model.LoginResponse;
import co.secbi.gesea.ui.fragment.AsistenciaFragment;
import co.secbi.gesea.ui.fragment.InicioFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar(); // Setear Toolbar como action bar

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.main_content, new AsistenciaFragment())
                    .commit();
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
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

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Marcar item presionado
                        menuItem.setChecked(true);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        switch (menuItem.getItemId()) {
                            case R.id.nav_asistencia:
                                //drawerLayout.openDrawer(GravityCompat.START);
                                fragmentManager
                                        .beginTransaction()
                                        .replace(R.id.main_content, new AsistenciaFragment())
                                        .commit();
                                drawerLayout.closeDrawers(); // Cerrar drawer
                                return true;
                            case R.id.nav_actividades:
                                drawerLayout.closeDrawers(); // Cerrar drawer
                                return true;
                            case R.id.nav_estudiantes:
                                drawerLayout.closeDrawers(); // Cerrar drawer
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
