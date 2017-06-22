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
import com.zecovery.android.zemedidores.views.assignments.AssignmentActivity;

import static com.zecovery.android.zemedidores.data.Constant.AA;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_TYPE;
import static com.zecovery.android.zemedidores.data.Constant.BUSINESS;
import static com.zecovery.android.zemedidores.data.Constant.COMMERCIAL;
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
        holder.setOrigin(assignment.getOrigin());

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

        holder.assignmentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, AskActivity.class);

                switch (assignment.getAssignment_type()) {
                    case RESIDENTIAL:
                        intent.putExtra(ASSIGNMENT_TYPE, RESIDENTIAL);
                        context.startActivity(intent);
                        break;
                    case COMMERCIAL:
                        intent.putExtra(ASSIGNMENT_TYPE, COMMERCIAL);
                        context.startActivity(intent);
                        break;
                    case BUSINESS:
                        intent.putExtra(ASSIGNMENT_TYPE, COMMERCIAL);
                        context.startActivity(intent);
                        break;
                }
            }
        });
    }

    public static class AssignmentHolder extends RecyclerView.ViewHolder {

        private View assignmentView, originColor;
        private TextView assignmentMainTv, assignmentSubTv, assignmentOriginTv;

        public AssignmentHolder(View itemView) {
            super(itemView);
            assignmentView = itemView.findViewById(R.id.assignmentView);
            originColor = itemView.findViewById(R.id.originColor);
            assignmentMainTv = (TextView) itemView.findViewById(R.id.assignmentTv);
            assignmentSubTv = (TextView) itemView.findViewById(R.id.assignmentDescriptionTv);
            assignmentOriginTv = (TextView) itemView.findViewById(R.id.assignmentOriginTv);

        }

        private void setAssignment(String name) {
            assignmentMainTv.setText(name);
        }

        private void setDescription(String description) {
            assignmentSubTv.setText(description);
        }

        private void setOrigin(String origin) {
            assignmentOriginTv.setText(origin);
        }

    }
}
