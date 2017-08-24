package com.zecovery.android.zemedidores.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.models.Inspection;
import com.zecovery.android.zemedidores.views.assignments.AskActivity;

import java.util.ArrayList;
import java.util.List;

import static com.zecovery.android.zemedidores.data.Constant.ADDRESS;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_TYPE;
import static com.zecovery.android.zemedidores.data.Constant.BUSINESS;
import static com.zecovery.android.zemedidores.data.Constant.COMMERCIAL;
import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT;
import static com.zecovery.android.zemedidores.data.Constant.LATITUDE;
import static com.zecovery.android.zemedidores.data.Constant.LONGITUDE;
import static com.zecovery.android.zemedidores.data.Constant.RESIDENTIAL;

/**
 * Created by fbarrios80 on 08-05-17.
 */

public class InspectionAdapter extends RecyclerView.Adapter<InspectionAdapter.InspectionViewHolder> {

    private Context context;
    private List<Inspection> inspections = new ArrayList<>();

    public InspectionAdapter(Context context, List<Inspection> inspections) {
        this.context = context;
        this.inspections = inspections;
    }

    @Override
    public InspectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_inspection, parent, false);
        return new InspectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InspectionViewHolder holder, int position) {

        final Inspection inspection = inspections.get(position);

        holder.setOrder(inspection.getOrden());

        holder.inspectionLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AskActivity.class);

                switch (inspection.getAssigment_type()) {
                    case 1:
                        intent.putExtra(ASSIGNMENT_TYPE, RESIDENTIAL);
                        intent.putExtra(LATITUDE, inspection.getLat());
                        intent.putExtra(LONGITUDE, inspection.getLng());
                        intent.putExtra(ADDRESS, inspection.getAddress());
                        //intent.putExtra(ID_ASSIGNMENT, inspection.getInspectionId());
                        context.startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra(ASSIGNMENT_TYPE, COMMERCIAL);
                        intent.putExtra(LATITUDE, inspection.getLat());
                        intent.putExtra(LONGITUDE, inspection.getLng());
                        intent.putExtra(ADDRESS, inspection.getAddress());
                        //intent.putExtra(ID_ASSIGNMENT, inspection.getInspectionId());
                        context.startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra(ASSIGNMENT_TYPE, BUSINESS);
                        intent.putExtra(LATITUDE, inspection.getLat());
                        intent.putExtra(LONGITUDE, inspection.getLng());
                        intent.putExtra(ADDRESS, inspection.getAddress());
                        //intent.putExtra(ID_ASSIGNMENT, inspection.getInspectionId());
                        context.startActivity(intent);
                        break;
                }
            }
        });

        //holder.setInspection(inspection.getAddress());
        //holder.setDescription(inspection.getOrden());

        /*
        switch (assignment.getOrigin()) {
            case ZE:
                holder.originColor.setBackgroundColor(Color.BLUE);
                break;
            case AA:
                holder.originColor.setBackgroundColor(Color.YELLOW);
                break;
            default:
                holder.originColor.setBackgroundColor(Color.BLACK);
                break;
        }

        switch (assignment.getAssignment_type()) {
            case RESIDENTIAL:
                holder.assignmentTypeColor.setBackgroundColor(Color.CYAN);
                break;
            case COMMERCIAL:
                holder.assignmentTypeColor.setBackgroundColor(Color.MAGENTA);
                break;
            default:
                holder.assignmentTypeColor.setBackgroundColor(Color.BLACK);
        }

        switch (assignment.getPolygon()) {
            case SOCIAL_POLYGON:
                holder.polygonColor.setBackgroundColor(Color.RED);
                break;
            default:
                holder.polygonColor.setBackgroundColor(Color.WHITE);
        }

       /* holder.assignmentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, AskActivity.class);

                switch (assignment.getAssignment_type()) {
                    case RESIDENTIAL:
                        intent.putExtra(ASSIGNMENT_TYPE, RESIDENTIAL);
                        intent.putExtra(LATITUDE, assignment.getLat());
                        intent.putExtra(LONGITUDE, assignment.getLng());
                        intent.putExtra(ADDRESS, assignment.getAddress());
                        intent.putExtra(ID_ASSIGNMENT, assignment.getPush_key());
                        context.startActivity(intent);
                        break;
                    case COMMERCIAL:
                        intent.putExtra(ASSIGNMENT_TYPE, COMMERCIAL);
                        intent.putExtra(LATITUDE, assignment.getLat());
                        intent.putExtra(LONGITUDE, assignment.getLng());
                        intent.putExtra(ADDRESS, assignment.getAddress());
                        intent.putExtra(ID_ASSIGNMENT, assignment.getPush_key());
                        context.startActivity(intent);
                        break;
                    case BUSINESS:
                        intent.putExtra(ASSIGNMENT_TYPE, BUSINESS);
                        intent.putExtra(LATITUDE, assignment.getLat());
                        intent.putExtra(LONGITUDE, assignment.getLng());
                        intent.putExtra(ADDRESS, assignment.getAddress());
                        intent.putExtra(ID_ASSIGNMENT, assignment.getPush_key());
                        context.startActivity(intent);
                        break;
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return inspections.size();
    }

    static class InspectionViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout inspectionLinearLayout;
        private TextView inspectionOrderTextView;

        public InspectionViewHolder(View view) {
            super(view);
            inspectionLinearLayout = view.findViewById(R.id.inspectionLinearLayout);
            inspectionOrderTextView = view.findViewById(R.id.inspectionOrderTextView);
        }


        private void setOrder(String order) {
            inspectionOrderTextView.setText(order);
        }

    }
}