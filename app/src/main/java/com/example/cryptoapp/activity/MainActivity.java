package com.example.cryptoapp.activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptoapp.Adapter.CryptoWallerAdapter;
import com.example.cryptoapp.Domain.CryptoWallet;
import com.example.cryptoapp.R;
import com.example.cryptoapp.databinding.ActivityMainBinding;
import com.example.cryptoapp.session.SessionManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SessionManager session = new SessionManager(this);
        TextView name = findViewById(R.id.textView3);
        name.setText(session.getUser());


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        RecyclerviewInit();
        
    }


    private void RecyclerviewInit() {

        //sample data, maybe using API
        ArrayList<CryptoWallet> cryptoWalletArrayList=new ArrayList<>();
        cryptoWalletArrayList.add(new CryptoWallet("BTC","btc",2.13,1.4,14021.21));
        cryptoWalletArrayList.add(new CryptoWallet("ETH","eth",-1.13,3.6,2145.21));
        cryptoWalletArrayList.add(new CryptoWallet("XPR","xth",-3.14,2.6,21463.10));
        cryptoWalletArrayList.add(new CryptoWallet("LTC","ltc",4.45,3.5,5412.46));

        binding.list.setLayoutManager(new GridLayoutManager(this,2));
        binding.list.setAdapter(new CryptoWallerAdapter(cryptoWalletArrayList));
    }
}