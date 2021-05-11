package com.belajar.tugastpm5;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MotorViewHolder extends RecyclerView.ViewHolder {
    ImageView ivMotor;
    TextView tvPlat;
    TextView tvJenis;
    TextView tvModel;
    ImageView ivForward;
    ImageView ivShare;
    public MotorViewHolder(@NonNull View itemView) {
        super(itemView);
        ivMotor = itemView.findViewById(R.id.iv_motor_terjual);
        tvPlat = itemView.findViewById(R.id.tv_plat_motor_terjual);
        tvJenis = itemView.findViewById(R.id.tv_jenis_terjual);
        tvModel = itemView.findViewById(R.id.tv_model_terjual);
        ivForward = itemView.findViewById(R.id.iv_forward);
        ivShare = itemView.findViewById(R.id.iv_share);
    }
}
