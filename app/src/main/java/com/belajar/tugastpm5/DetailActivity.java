package com.belajar.tugastpm5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
    ImageView ivMotor;
    TextView tvPlat;
    TextView tvHarga;
    TextView tvJenis;
    TextView tvModel;
    TextView tvMesin;
    TextView tvTahun;
    ImageView btnBack;
    ImageView btnFavorite;
    ImageView btnShare;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvHarga = findViewById(R.id.tv_harga_motor_detail_tersedia);
        ivMotor = findViewById(R.id.iv_motor_detail_tersedia);
        tvPlat = findViewById(R.id.tv_plat_motor_detail_tersedia);
        tvJenis = findViewById(R.id.tv_jenis_detail_tersedia);
        tvModel = findViewById(R.id.tv_model_detail_tersedia);
        tvMesin = findViewById(R.id.tv_kapasitas_mesin_detail_tersedia);
        tvTahun = findViewById(R.id.tv_tahun_motor_detail_tersedia);
        btnFavorite = findViewById(R.id.iv_wishlist_detail_tersedia);
        btnShare = findViewById(R.id.iv_share_detail_tersedia);

        btnFavorite.setOnClickListener(v ->{
            btnFavorite.setImageResource(R.drawable.ic_star_yellow_24dp);
            Toast.makeText(this, "Sudah difavoritkan", Toast.LENGTH_LONG).show();
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("motor_terjual");
        String motorKey = getIntent().getStringExtra("motorkey");

        databaseReference.child(motorKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String hargaMotor = snapshot.child("harga").getValue().toString();
                    String jenisMotor = snapshot.child("jenis").getValue().toString();
                    String ccMotor = snapshot.child("kapasitas_mesin").getValue().toString();
                    String modelMotor = snapshot.child("model").getValue().toString();
                    String platMotor = snapshot.child("plat").getValue().toString();
                    String tahun = snapshot.child("tahun").getValue().toString();
                    String url = snapshot.child("url").getValue().toString();

                    Glide.with(getApplicationContext())
                            .load(url)
                            .into(ivMotor);


                    tvHarga.setText(hargaMotor);
                    tvJenis.setText(jenisMotor);
                    tvMesin.setText(ccMotor);
                    tvModel.setText(modelMotor);
                    tvPlat.setText(platMotor);
                    tvTahun.setText(tahun);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnShare.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,"ini text yang dishare");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });


    }

}