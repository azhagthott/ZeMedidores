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
import com.zecovery.android.zemedidores.views.assignments.AssignmentActivity;

import static com.zecovery.android.zemedidores.data.Constant.AA;
import static com.zecovery.android.zemedidores.data.Constant.BUSINESS;
import static com.zecovery.android.zemedidores.data.Constant.COMMERCIAL;
import static com.zecovery.android.zemedidores.data.Constant.EMPTY;
import static com.zecovery.android.zemedidores.data.Constant.REJECTED;
import static com.zecovery.android.zemedidores.data.Constant.RESIDENTIAL;
import static com.zecovery.android.zemedidores.data.Constant.ZE;

/**
 * Created by fbarrios80 on 08-05-17.
 */

public class AssignmentAdapter extends FirebaseRecyclerAdapter<Assignment, AssignmentAdapter.AssignmentHolder> {

    private AssignmentUpdater updater;
    private Context context;

    public AssignmentAdapter(AssignmentUpdater updater, Context context) {
        super(Assignment.class, R.layout.list_item_assignment, AssignmentHolder.class, new Nodes().assignments());
        this.updater = updater;
        this.context = context;
    }

    @Override
    protected void populateViewHolder(final AssignmentHolder holder, final Assignment assignment, final int position) {
        holder.setAssignment(assignment.getAddress());
        holder.setDescription(assignment.getDescription());


        if (assignment.getOrigin().equals(ZE)) {
            holder.originColor.setBackgroundColor(Color.BLUE);
        } else if (assignment.getOrigin().equals(AA)) {
            holder.originColor.setBackgroundColor(Color.YELLOW);
        } else {
            holder.originColor.setBackgroundColor(Color.BLACK);
        }

        holder.assignmentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, AssignmentActivity.class);
                String assignmentType = assignment.getAssignment_type();

                switch (assignmentType) {
                    case REJECTED:
                        intent.putExtra("EXTRA", REJECTED);
                        context.startActivity(intent);
                    case RESIDENTIAL:
                        intent.putExtra("EXTRA", RESIDENTIAL);
                        context.startActivity(intent);
                        break;
                    case COMMERCIAL:
                        intent.putExtra("EXTRA", COMMERCIAL);
                        context.startActivity(intent);
                        break;
                    case BUSINESS:
                        intent.putExtra("EXTRA", BUSINESS);
                        context.startActivity(intent);
                        break;
                    default:
                        intent.putExtra("EXTRA", EMPTY);
                        context.startActivity(intent);
                        break;
                }
            }
        });
    }

    public static class AssignmentHolder extends RecyclerView.ViewHolder {

        private View assignmentView, originColor;
        private TextView assignmentMainTv, assignmentSubTv;

        public AssignmentHolder(View itemView) {
            super(itemView);
            assignmentView = itemView.findViewById(R.id.assignmentView);
            originColor = itemView.findViewById(R.id.originColor);
            assignmentMainTv = (TextView) itemView.findViewById(R.id.assignmentTv);
            assignmentSubTv = (TextView) itemView.findViewById(R.id.assignmentDescriptionTv);
        }

        private void setAssignment(String name) {
            assignmentMainTv.setText(name);
        }

        private void setDescription(String description) {
            assignmentSubTv.setText(description);
        }

    }
}
