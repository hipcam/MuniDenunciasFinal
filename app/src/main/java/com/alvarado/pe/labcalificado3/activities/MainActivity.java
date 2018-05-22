package com.alvarado.pe.labcalificado3.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alvarado.pe.labcalificado3.R;
import com.alvarado.pe.labcalificado3.adapters.ReporteAdapter;
import com.alvarado.pe.labcalificado3.models.Reporte;
import com.alvarado.pe.labcalificado3.services.ApiService;
import com.alvarado.pe.labcalificado3.services.ApiServiceGenerator;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String idciudadano;

    private RecyclerView reportesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        idciudadano = String.valueOf(getIntent().getExtras().getString("idciudadano"));
        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggin);


        reportesList = findViewById(R.id.recyclerview);
        reportesList.setLayoutManager(new LinearLayoutManager(this));

        reportesList.setAdapter(new ReporteAdapter(this));

        initialize();
    }

    private void initialize() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

            Call<List<Reporte>> call = service.getReportes();

        call.enqueue(new Callback<List<Reporte>>() {
            @Override
            public void onResponse(Call<List<Reporte>> call, Response<List<Reporte>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Reporte> reportes = response.body();
                        Log.d(TAG, "reportes: " + reportes);

                        ReporteAdapter adapter = (ReporteAdapter) reportesList.getAdapter();
                        adapter.setReportes(reportes);
                        adapter.notifyDataSetChanged();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Reporte>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
    private static final int REGISTER_FORM_REQUEST = 100;

    public void showRegister(View view){
        Intent intent = new Intent(this, ReportActivity.class);
        intent.putExtra("idciudadano", idciudadano);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REGISTER_FORM_REQUEST) {
            // refresh data
            initialize();
        }
    }
}
