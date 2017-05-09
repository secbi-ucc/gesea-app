package co.secbi.gesea.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import co.secbi.gesea.R;
import co.secbi.gesea.domain.ProgramacionActividad;
import co.secbi.gesea.ui.fragment.AsistenciaFragment;
import co.secbi.gesea.ui.listeners.ItemClickListener;

/**
 * Created by JulioC on 5/2/17.
 */

public class ProgramacionListAdapter extends RecyclerView.Adapter<ProgramacionListAdapter.ListViewHolder>  {
    ArrayList<ProgramacionActividad> listaProgramacion;
    Context context;

    public String Zona_ID;


    public ProgramacionListAdapter(Context context) {
        this.context = context;
        this.listaProgramacion = new ArrayList<>();

    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_programacion_list, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

        ProgramacionActividad currentItem = listaProgramacion.get(position);

        holder.setActividad(currentItem.getDia_Actividad());
        holder.setHoraInicio(currentItem.getHora_Inicio());
        holder.setHoraFinal(currentItem.getHora_Final());
        holder.setDia(currentItem.getTipo_actividad());

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {


                if (isLongClick) {


                } else {
                    Toast.makeText(context, "Id Programaciom:  " + listaProgramacion.get(position).getId() , Toast.LENGTH_SHORT).show();

                    Bundle args = new Bundle();
                    args.putString("programacion_id",listaProgramacion.get(position).getId());
                    AsistenciaFragment b = new AsistenciaFragment();

                    b.setArguments(args);

                    FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.main_content, b)
                            .addToBackStack(null)
                            .commit();

                }
            }
        });


    }

    public void addAll(ArrayList<ProgramacionActividad> p) {
        if (p == null)
            throw new NullPointerException("No puede ser un arreglo nulo");

        this.listaProgramacion.addAll(p);
        notifyDataSetChanged();

        for(ProgramacionActividad elem : p){
            Log.d("s", String.valueOf(elem.getDia_Actividad()));
        }

    }

    public void removeAll() {


        this.listaProgramacion.clear();
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return listaProgramacion.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView ActividadName;
        TextView ProgramacionId, HoraInicio,HoraFinal;

        private ItemClickListener clickListener;

        public ListViewHolder(View itemView) {
            super(itemView);

            ActividadName = (TextView) itemView.findViewById(R.id.estudiante_id);

            ProgramacionId = (TextView) itemView.findViewById(R.id.estudiante_nombre);
            HoraInicio = (TextView) itemView.findViewById(R.id.hora_inicio);
            HoraFinal = (TextView) itemView.findViewById(R.id.hora_final);



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

        public void setActividad(String name) {
            this.ActividadName.setText(name);
        }

        public void setDia(String zona) {
            this.ProgramacionId.setText(zona);
        }

        public void setHoraInicio(String h) {
            this.HoraInicio.setText(h);
        }
        public void setHoraFinal(String h) {
            this.HoraFinal.setText(h);
        }


    }
}
