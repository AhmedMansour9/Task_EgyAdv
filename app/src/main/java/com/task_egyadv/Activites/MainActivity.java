package com.task_egyadv.Activites;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.task_egyadv.R;
import com.task_egyadv.SharedPrefManager;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.Log_out)
    TextView Log_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log_Out();
    }
    public void Log_Out(){
        Log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(MainActivity.this).saveUserToken(null);
                startActivity(new Intent(MainActivity.this,Login.class));
                finish();
            }
        });
    }
}
