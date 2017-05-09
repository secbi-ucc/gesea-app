package co.secbi.gesea.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import co.secbi.gesea.R;
import co.secbi.gesea.domain.Programacion;
import co.secbi.gesea.domain.ProgramacionActividad;
import co.secbi.gesea.io.ApiAdapter;
import co.secbi.gesea.io.ApiConstants;
import co.secbi.gesea.io.model.ProgramacionResponse;
import co.secbi.gesea.ui.ItemDividerDecoration;
import co.secbi.gesea.ui.adapters.ProgramacionListAdapter;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JulioC on 5/2/17.
 */

public class ProgramacionCalendarFragment extends Fragment implements Callback<ProgramacionResponse> {

    private HorizontalCalendar horizontalCalendar;
    private ArrayList<ProgramacionActividad> pe = new ArrayList<>();
    private RecyclerView mProgramacionList;
    private ProgramacionListAdapter adapter;
    private String currentDayName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ProgramacionListAdapter(getActivity());

        Call<ProgramacionResponse> call = ApiAdapter.getApiService(getContext()).getProgramacion();
        call.enqueue(this);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");


        Date d = new Date();
        currentDayName = stripAccents(sdf.format(d).toUpperCase());

        Log.d("DAY", currentDayName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fagment_programacion_calendar, container, false);

        /** end after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        /** start before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .datesNumberOnScreen(5)
                .dayNameFormat("EEE")
                .dayNumberFormat("dd")
                .monthFormat("MMM")
                //.textSize(14f, 24f, 14f)
                .showDayName(true)
                .showMonthName(true)
                .textColor(Color.LTGRAY, Color.WHITE)
                .selectedDateBackground(Color.TRANSPARENT)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {


                Toast.makeText(getContext(), android.text.format.DateFormat.format("EEEE", date).toString().toUpperCase(), Toast.LENGTH_SHORT).show();

                String day_name =  stripAccents(android.text.format.DateFormat.format("EEEE", date).toString().toUpperCase());

                setAdapterActividadByDay(day_name);


            }

        });


        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                horizontalCalendar.goToday(false);
            }
        });


        mProgramacionList = (RecyclerView) rootView.findViewById(R.id.programacion_list);

        setupAsistenciaList();



        return rootView;
    }

    private void setupAsistenciaList(){
        mProgramacionList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProgramacionList.setAdapter(adapter);
        mProgramacionList.addItemDecoration(new ItemDividerDecoration(getActivity()));
    }

    @Override
    public void onResponse(Call<ProgramacionResponse> call, Response<ProgramacionResponse> response) {


        ArrayList<Programacion> p = new ArrayList<>();

        // response.isSuccessful() is true if the response code is 2xx
        if (response.isSuccessful()) {

            p = response.body().getProgramacion();

            Log.d("SIZE", String.valueOf(p.size()));
            for (int i = 0; i <  p.size(); i++) {
                for (int j = 0; j <  p.get(i).getDia_semana().size(); j++) {

                    String id = p.get(i).getId().toString();
                    String da = p.get(i).getDia_semana().get(j).getDia_Actividad();
                    String hi = p.get(i).getDia_semana().get(j).getHorario().getHora_Inicio();
                    String hf = p.get(i).getDia_semana().get(j).getHorario().getHora_Final();
                    String ta = p.get(i).getActividad().getTipo_actividad();


                    ProgramacionActividad pa = new ProgramacionActividad(id, hi, hf, da, ta);

                    pe.add(pa);

                }
            }

            setAdapterActividadByDay();

        } else {
            int statusCode = response.code();

            // handle request errors yourself
            ResponseBody errorBody = response.errorBody();


            Log.e("ERROR", String.valueOf(statusCode));
        }
    }

    @Override
    public void onFailure(Call<ProgramacionResponse> call, Throwable t) {

        TextView status = (TextView)getActivity().findViewById(R.id.status_text);
        status.setBackgroundColor(Color.RED);
        status.setText("No se pudo conectar al servidor " + ApiConstants.BASE_URL);

        t.printStackTrace();

    }





    private void setAdapterActividadByDay(String currentDayName) {


        ArrayList<ProgramacionActividad> paTemp = new ArrayList<>();

        adapter.removeAll();


        for (int i = 0; i < pe.size(); i++) {

            if (pe.get(i).getDia_Actividad().equals(currentDayName)) {
                paTemp.add(pe.get(i));
            }

        }

        TextView status = (TextView)getActivity().findViewById(R.id.status_text);

        ImageView imageCalendarNot = (ImageView)getActivity().findViewById(R.id.imageCalendarNot);

        if (paTemp.isEmpty()){

            status.setVisibility(View.VISIBLE);
            status.setText("Sin actividades :(");

            imageCalendarNot.setVisibility(View.VISIBLE);
        } else {

            adapter.addAll(paTemp);
            status.setVisibility(View.GONE);
            imageCalendarNot.setVisibility(View.GONE);

        }






    }

    private void setAdapterActividadByDay() {
        setAdapterActividadByDay(currentDayName);
    }

    public static String stripAccents(String s)
    {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }

}