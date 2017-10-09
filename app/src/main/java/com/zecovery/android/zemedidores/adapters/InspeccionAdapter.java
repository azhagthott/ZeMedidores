package com.zecovery.android.zemedidores.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.models.Inspection;
import com.zecovery.android.zemedidores.views.map.MapActivity;

import java.util.ArrayList;
import java.util.List;

import static com.zecovery.android.zemedidores.data.Constant.DIRECCION;
import static com.zecovery.android.zemedidores.data.Constant.TIPO_INSPECCION;
import static com.zecovery.android.zemedidores.data.Constant.COMERCIAL;
import static com.zecovery.android.zemedidores.data.Constant.EMPRESA;
import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION;
import static com.zecovery.android.zemedidores.data.Constant.LATITUD;
import static com.zecovery.android.zemedidores.data.Constant.LONGITUD;
import static com.zecovery.android.zemedidores.data.Constant.RESIDENCIAL;

/**
 * Created by fbarrios80 on 08-05-17.
 */

public class InspeccionAdapter extends RecyclerView.Adapter<InspeccionAdapter.InspeccionViewHolder> {

    private List<Inspection> inspections = new ArrayList<>();

    public InspeccionAdapter(List<Inspection> inspections) {
        this.inspections = inspections;
    }

    @Override
    public InspeccionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_inspection, parent, false);
        return new InspeccionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InspeccionViewHolder holder, int position) {

        final Inspection inspection = inspections.get(position);

        if (inspection.getOrigin() == 1) {
            holder.setColor(Color.RED);
        } else if (inspection.getOrigin() == 2) {
            holder.setColor(Color.BLUE);
        } else {
            holder.setColor(Color.BLACK);
        }

        holder.setAddress(inspection.getAddress());

        holder.inspectionLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, MapActivity.class);

                switch (inspection.getAssigment_type()) {
                    case 1:
                        intent.putExtra(TIPO_INSPECCION, RESIDENCIAL);
                        intent.putExtra(LATITUD, inspection.getLat());
                        intent.putExtra(LONGITUD, inspection.getLng());
                        intent.putExtra(DIRECCION, inspection.getAddress());
                        intent.putExtra(ID_INSPECCION, inspection.getId_inspeccion());
                        context.startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra(TIPO_INSPECCION, COMERCIAL);
                        intent.putExtra(LATITUD, inspection.getLat());
                        intent.putExtra(LONGITUD, inspection.getLng());
                        intent.putExtra(DIRECCION, inspection.getAddress());
                        intent.putExtra(ID_INSPECCION, inspection.getId_inspeccion());
                        context.startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra(TIPO_INSPECCION, EMPRESA);
                        intent.putExtra(LATITUD, inspection.getLat());
                        intent.putExtra(LONGITUD, inspection.getLng());
                        intent.putExtra(DIRECCION, inspection.getAddress());
                        intent.putExtra(ID_INSPECCION, inspection.getId_inspeccion());
                        context.startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return inspections.size();
    }

    static class InspeccionViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout inspectionLinearLayout;
        private TextView inspectionAddressTextView;
        private View inpseccionColor;

        public InspeccionViewHolder(View view) {
            super(view);
            inspectionLinearLayout = view.findViewById(R.id.inspectionLinearLayout);
            inspectionAddressTextView = view.findViewById(R.id.inspectionAddressTextView);
            inpseccionColor = view.findViewById(R.id.inpseccionColor);
        }

        private void setAddress(String address) {
            inspectionAddressTextView.setText(address);
        }

        private void setColor(int color) {
            inpseccionColor.setBackgroundColor(color);
        }
    }
}