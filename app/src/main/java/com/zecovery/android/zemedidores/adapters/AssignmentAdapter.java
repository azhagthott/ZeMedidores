package com.zecovery.android.zemedidores.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.Nodes;
import com.zecovery.android.zemedidores.models.Assignment;
import com.zecovery.android.zemedidores.views.Assignments.AssignmentDetailActivity;

/**
 * Created by fbarrios80 on 08-05-17.
 */

public class AssignmentAdapter extends FirebaseRecyclerAdapter<Assignment, AssignmentAdapter.AssignmentHolder> {

    private AssignmentUpdater updater;

    public AssignmentAdapter(String uid, AssignmentUpdater updater) {
        super(Assignment.class, R.layout.list_item_assignment, AssignmentHolder.class, new Nodes().assignments());
        this.updater = updater;
    }

    @Override
    protected void populateViewHolder(final AssignmentHolder holder, final Assignment assignment, final int position) {
        holder.setAssignment(assignment.getName());
        holder.setDescription(assignment.getAddress());
        holder.setStatus(assignment.getStatus());
        holder.assignmentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), AssignmentDetailActivity.class));
            }
        });
    }


    public static class AssignmentHolder extends RecyclerView.ViewHolder {

        private View assignmentView;
        private TextView assignmentMainTv, assignmentSubTv;
        private CheckBox status;

        public AssignmentHolder(View itemView) {
            super(itemView);
            assignmentView = itemView.findViewById(R.id.assignmentView);
            assignmentMainTv = (TextView) itemView.findViewById(R.id.assignmentTv);
            assignmentSubTv = (TextView) itemView.findViewById(R.id.assignmentDescriptionTv);
            status = (CheckBox) itemView.findViewById(R.id.status);

        }

        private void setAssignment(String name) {
            assignmentMainTv.setText(name);
        }

        private void setDescription(String name) {
            assignmentSubTv.setText(name);
        }

        private void setStatus(boolean done) {
            if (done) {
                status.isChecked();
            }
        }
    }
}
