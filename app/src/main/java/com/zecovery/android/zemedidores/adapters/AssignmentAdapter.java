package com.zecovery.android.zemedidores.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.Nodes;
import com.zecovery.android.zemedidores.models.Assignment;
import com.zecovery.android.zemedidores.views.assignments.AssignmentDetailActivity;

import static com.zecovery.android.zemedidores.data.Constant.AA;
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
        holder.setAssignment(assignment.getName());
        holder.setDescription(assignment.getDescription());

        if (assignment.getOrigin().equals(ZE)) {
            holder.originColor.setBackgroundColor(Color.RED);
        } else if (assignment.getOrigin().equals(AA)) {
            holder.originColor.setBackgroundColor(Color.GREEN);
        } else {
            holder.originColor.setBackgroundColor(Color.BLACK);
        }

        holder.assignmentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), AssignmentDetailActivity.class));
            }
        });

        holder.assignmentView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                activityRejectedDialog(context);
                return false;
            }
        });
    }


    private void activityRejectedDialog(final Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_fragment);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        Button saveDialogButton = (Button) dialog.findViewById(R.id.saveButton);

        saveDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        Button cancelDialogButton = (Button) dialog.findViewById(R.id.cancelButton);
        cancelDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //adapter.notifyDataSetChanged();
        dialog.show();
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
