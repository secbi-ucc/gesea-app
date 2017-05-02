package co.secbi.gesea.ui.adapters;
/**
 * Created by uzi200 on 10/5/15.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.secbi.gesea.R;
import co.secbi.gesea.domain.Asistencia;
import co.secbi.gesea.ui.listeners.ItemClickListener;


public class AsistenciaListAdapter extends RecyclerView.Adapter<AsistenciaListAdapter.ZonaListViewHolder> {
    ArrayList<Asistencia> listaAsistencia;
    Context context;

    public String Zona_ID;


    public AsistenciaListAdapter(Context context) {
        this.context = context;
        this.listaAsistencia = new ArrayList<>();

    }

    @Override
    public ZonaListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_asistencia_estudiante_list, parent, false);
        return new ZonaListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ZonaListViewHolder holder, int position) {

        Asistencia currentZona = listaAsistencia.get(position);

        holder.setZonaName(currentZona.getEstudiante().getID_Estudiante());
        holder.setZonaId(String.valueOf(currentZona.getEstudiante().getNombre_completo()));

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {


                if (isLongClick) {
                    Toast.makeText(context, "La Zona " + listaAsistencia.get(position).getId() + " tiene el codigo  " + listaAsistencia.get(position).getN_horas() + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "La Zona  " + listaAsistencia.get(position).getId() + " tiene el codigo   " + listaAsistencia.get(position).getN_horas(), Toast.LENGTH_SHORT).show();

                    //BarrioListFragment b = new BarrioListFragment();

                   // FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                    //ft.replace(R.id.main_content, b).commit();

                    //Bundle bundle = new Bundle();
                    //bundle.putString(Zona_ID, listaAsistencia.get(position).getId());
                    //b.setArguments(bundle);

                    CheckBox cb = (CheckBox) view.findViewById(R.id.check_asistencia);
                    cb.setChecked(!cb.isChecked());




                }
            }
        });


    }

    public void addAll(ArrayList<Asistencia> zonas) {
        if (zonas == null)
            throw new NullPointerException("No puede ser un arreglo nulo");

        this.listaAsistencia.addAll(zonas);
        notifyDataSetChanged();

        for(Asistencia elem : zonas){
            Log.d("s", elem.getId());
        }

    }

    @Override
    public int getItemCount() {
        return listaAsistencia.size();
    }

    public class ZonaListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView zonaName;
        TextView ZonaId;

        private ItemClickListener clickListener;

        public ZonaListViewHolder(View itemView) {
            super(itemView);

            zonaName = (TextView) itemView.findViewById(R.id.estudiante_id);

            ZonaId = (TextView) itemView.findViewById(R.id.estudiante_nombre);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }


        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }

        public void setZonaName(String name) {
            this.zonaName.setText(name);
        }

        public void setZonaId(String zona) {
            this.ZonaId.setText(zona);
        }

    }
}
