package com.zecovery.android.zemedidores.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.Nodes;
import com.zecovery.android.zemedidores.models.Assignment;
import com.zecovery.android.zemedidores.views.Assignments.AssignmentDetailActivity;

import static com.zecovery.android.zemedidores.data.Constant.AA;
import static com.zecovery.android.zemedidores.data.Constant.ZE;

/**
 * Created by fbarrios80 on 08-05-17.
 */

public class AssignmentAdapter extends FirebaseRecyclerAdapter<Assignment, AssignmentAdapter.AssignmentHolder> {

    private AssignmentUpdater updater;

    public AssignmentAdapter(AssignmentUpdater updater) {
        super(Assignment.class, R.layout.list_item_assignment, AssignmentHolder.class, new Nodes().assignments());
        this.updater = updater;
    }

    @Override
    protected void populateViewHolder(final AssignmentHolder holder, final Assignment assignment, final int position) {
        holder.setAssignment(assignment.getName());
        holder.setDescription(assignment.getDescription());

        holder.assignmentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), AssignmentDetailActivity.class));
            }
        });

        if (assignment.getOrigin().equals(ZE)) {
            holder.originColor.setBackgroundColor(Color.RED);
        } else if (assignment.getOrigin().equals(AA)) {
            holder.originColor.setBackgroundColor(Color.GREEN);
        } else {
            holder.originColor.setBackgroundColor(Color.BLACK);
        }
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
