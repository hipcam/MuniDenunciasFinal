package com.alvarado.pe.labcalificado3.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alvarado.pe.labcalificado3.R;
import com.alvarado.pe.labcalificado3.models.Ciudadano;
import com.alvarado.pe.labcalificado3.services.ApiService;
import com.alvarado.pe.labcalificado3.services.ApiServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText userInput;
    private EditText passInput;
    private static final int REGISTER_FORM_REQUEST = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       userInput = findViewById(R.id.userInput);
       passInput = findViewById(R.id.passInput);

    }
    public void callRegisterUser(View view){
        startActivityForResult(new Intent(this, RegisterUserActivity.class), REGISTER_FORM_REQUEST);
    }
    public void callMain(View view){
        String user = userInput.getText().toString();
        String password = passInput.getText().toString();
        ApiService service = ApiServiceGenerator.createService(ApiService.class);
        Call<Ciudadano> call = service.Login(user,password);

        call.enqueue(new Callback<Ciudadano>() {
            @Override
            public void onResponse(Call<Ciudadano> call, Response<Ciudadano> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        Ciudadano ciudadano = response.body();
                        Log.d(TAG, "ciudadano: " + ciudadano.toString());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("idciudadano", String.valueOf(ciudadano.getUsuario()));
                        startActivity(intent);
                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Campos incorrectos");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }



            }

            @Override
            public void onFailure(Call<Ciudadano> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}