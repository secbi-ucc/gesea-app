package co.secbi.gesea.ui.fragment;
/**
 * Created by uzi200 on 9/17/15.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.secbi.gesea.MainActivity;
import co.secbi.gesea.R;
import co.secbi.gesea.io.ApiAdapter;
import co.secbi.gesea.io.ApiConstants;
import co.secbi.gesea.io.model.AsistenciaResponse;
import co.secbi.gesea.ui.ItemDividerDecoration;
import co.secbi.gesea.ui.adapters.AsistenciaListAdapter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AsistenciaFragment extends Fragment implements Callback<AsistenciaResponse>{

    private RecyclerView mAsistenciaList;
    private AsistenciaListAdapter adapter;
    public static final int COLUMNS = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new AsistenciaListAdapter(getActivity());


        Bundle b = getArguments();
        String programacionId = b.getString("programacion_id");

        Call<AsistenciaResponse> call = ApiAdapter.getApiService(getContext()).getAsistencia(ApiConstants.URL_ASISTENCIA_LIST + "/" + programacionId);
        call.enqueue(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_asistencia, container, false);


        mAsistenciaList = (RecyclerView) root.findViewById(R.id.asistencia_list);

        setupAsistenciaList();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void setupAsistenciaList(){
        mAsistenciaList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAsistenciaList.setAdapter(adapter);
        mAsistenciaList.addItemDecoration(new ItemDividerDecoration(getActivity()));
    }

    @Override
    public void onResponse(Call<AsistenciaResponse> call, Response<AsistenciaResponse> response) {
        //TextView status = (TextView)getActivity().findViewById(R.id.status_text);
        //status.setText("Conectado al servidor " + ApiConstants.BASE_URL);


        // response.isSuccessful() is true if the response code is 2xx
        if (response.isSuccessful()) {
            adapter.addAll( response.body().getAsistencia());
        } else {
            int statusCode = response.code();

            // handle request errors yourself
            ResponseBody errorBody = response.errorBody();


            Log.e("ERROR", String.valueOf(statusCode));
        }
    }

    @Override
    public void onFailure(Call<AsistenciaResponse> call, Throwable t) {

       // TextView status = (TextView)getActivity().findViewById(R.id.status_text);
       // status.setBackgroundColor(Color.RED);
        //status.setText("No se pudo conectar al servidor " + ApiConstants.BASE_URL);

        t.printStackTrace();

    }

}
