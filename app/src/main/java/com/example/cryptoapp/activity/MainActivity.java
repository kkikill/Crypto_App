package com.example.cryptoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.cryptoapp.api.BybitApiService;
import com.example.cryptoapp.api.BybitClient;
import com.example.cryptoapp.api.BybitResponse;
import com.example.cryptoapp.api.BybitTicker;
import com.example.cryptoapp.databinding.ActivityMainBinding;
import com.example.cryptoapp.session.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SessionManager session = new SessionManager(this);
        TextView name = findViewById(R.id.textView3);
        name.setText("Hello, " + session.getUser());

        TextView userLogin = findViewById(R.id.textView4);
        userLogin.setText(session.getUserLogin());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        RecyclerviewInit();

        // Set up BottomAppBar button listeners
        findViewById(R.id.bottomBtn1).setOnClickListener(v -> {
            // Main Menu button - already on MainActivity, so do nothing or refresh
            // For now, it will just stay on MainActivity
        });

        findViewById(R.id.bottomBtn2).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AccountActivity.class));
        });

        findViewById(R.id.bottomBtn3).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TransferActivity.class));
        });

        findViewById(R.id.bottomBtn4).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });
        
    }


    private void RecyclerviewInit() {

        BybitClient.getApiService().getTickers("spot").enqueue(new Callback<BybitResponse>() {
            @Override
            public void onResponse(Call<BybitResponse> call, Response<BybitResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getResult() != null) {
                    ArrayList<CryptoWallet> cryptoWalletArrayList = new ArrayList<>();
                    for (BybitTicker ticker : response.body().getResult().getList()) {
                        String symbol = ticker.getSymbol().replace("USDT", "");
                        String picUrl = symbol.toLowerCase();
                        double changePercent = Double.parseDouble(ticker.getPrice24hPcnt()) * 100;
                        double lastPrice = Double.parseDouble(ticker.getLastPrice());

                        // For simplicity, propertyAmount and cryptoBalance are hardcoded or derived
                        // In a real app, these would come from user data or further API calls
                        double propertyAmount = 0.0; // Placeholder
                        double cryptoBalance = lastPrice; // Placeholder for current price

                        cryptoWalletArrayList.add(new CryptoWallet(symbol, picUrl, changePercent, propertyAmount, cryptoBalance));
                    }
                    binding.list.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    binding.list.setAdapter(new CryptoWallerAdapter(cryptoWalletArrayList));
                } else {
                    Log.e("MainActivity", "Unsuccessful response or empty body: " + response.message());
                    // Optionally show a toast or error message to the user
                }
            }

            @Override
            public void onFailure(Call<BybitResponse> call, Throwable t) {
                Log.e("MainActivity", "API call failed: " + t.getMessage());
                // Optionally show a toast or error message to the user
            }
        });
    }
}