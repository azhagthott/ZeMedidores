package com.zecovery.android.zemedidores.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.Nodes;
import com.zecovery.android.zemedidores.models.Assignment;
import com.zecovery.android.zemedidores.views.assignments.AskActivity;

import static com.zecovery.android.zemedidores.data.Constant.AA;
import static com.zecovery.android.zemedidores.data.Constant.ADDRESS;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_TYPE;
import static com.zecovery.android.zemedidores.data.Constant.BUSINESS;
import static com.zecovery.android.zemedidores.data.Constant.COMMERCIAL;
import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT;
import static com.zecovery.android.zemedidores.data.Constant.LATITUDE;
import static com.zecovery.android.zemedidores.data.Constant.LONGITUDE;
import static com.zecovery.android.zemedidores.data.Constant.RESIDENTIAL;
import static com.zecovery.android.zemedidores.data.Constant.SOCIAL_POLYGON;
import static com.zecovery.android.zemedidores.data.Constant.ZE;

/**
 * Created by fbarrios80 on 08-05-17.
 */

public class AssignmentAdapter extends FirebaseRecyclerAdapter<Assignment, AssignmentAdapter.AssignmentHolder> {

    private Context context;

    public AssignmentAdapter(Context context) {
        super(Assignment.class, R.layout.list_item_assignment, AssignmentHolder.class, new Nodes().assignments());
        this.context = context;
    }

    @Override
    protected void populateViewHolder(final AssignmentHolder holder, final Assignment assignment, final int position) {

        holder.setAssignment(assignment.getAddress());
        holder.setDescription(assignment.getDescription());

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

        holder.assignmentView.setOnClickListener(new View.OnClickListener() {
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
        });
    }

    public static class AssignmentHolder extends RecyclerView.ViewHolder {

        private View assignmentView, originColor, assignmentTypeColor, polygonColor;
        private TextView assignmentAddressTextView, assignmentDescriptionTextView;

        public AssignmentHolder(View itemView) {
            super(itemView);
            assignmentView = itemView.findViewById(R.id.assignmentView);

            originColor = itemView.findViewById(R.id.originColor);
            assignmentTypeColor = itemView.findViewById(R.id.assignmentTypeColor);
            polygonColor = itemView.findViewById(R.id.polygonColor);

            assignmentAddressTextView = (TextView) itemView.findViewById(R.id.assignmentAddressTextView);
            assignmentDescriptionTextView = (TextView) itemView.findViewById(R.id.assignmentDescriptionTextView);

        }

        private void setAssignment(String name) {
            assignmentAddressTextView.setText(name);
        }

        private void setDescription(String description) {
            assignmentDescriptionTextView.setText(description);
        }


    }
}
