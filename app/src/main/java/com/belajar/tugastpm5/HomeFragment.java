package com.belajar.tugastpm5;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<Motor> motorOptions;
    FirebaseRecyclerAdapter<Motor, MotorViewHolder> motorAdapter;
    DatabaseReference databaseReference;
    String urlFirman;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        urlFirman = "https://i.imgur.com/YQqb10M.jpg";
        ImageView ivUser = getView().findViewById(R.id.iv_user);
        recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.rv_motor);
        databaseReference = FirebaseDatabase.getInstance().getReference("motor_terjual");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setHasFixedSize(true);
        Glide.with(getContext())
                .load(urlFirman)
                .apply(RequestOptions.circleCropTransform())
                .into(ivUser);
        loadData();
    }

    private void loadData() {
        motorOptions = new FirebaseRecyclerOptions.Builder<Motor>().setQuery(databaseReference, Motor.class).build();
        motorAdapter = new FirebaseRecyclerAdapter<Motor, MotorViewHolder>(motorOptions) {
            @Override
            protected void onBindViewHolder(@NonNull MotorViewHolder holder, int position, @NonNull Motor model) {
            holder.tvModel.setText(model.getModel());
            holder.tvJenis.setText(model.getJenis());
            holder.tvPlat.setText(model.getPlat());
            holder.ivForward.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("motorkey", getRef(position).getKey());
                startActivity(intent);
            });
            holder.ivShare.setOnClickListener(v -> {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"ini text yang dishare");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);

            });
                Glide.with(holder.itemView.getContext())
                        .load(model.getUrl())
                        .into(holder.ivMotor);
            }

            @NonNull
            @Override
            public MotorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_terjual, parent, false);
                return new MotorViewHolder(view);
            }
        };
        motorAdapter.startListening();
        recyclerView.setAdapter(motorAdapter);
    }
}