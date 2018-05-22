package com.alvarado.pe.labcalificado3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alvarado.pe.labcalificado3.R;
import com.alvarado.pe.labcalificado3.models.Reporte;
import com.alvarado.pe.labcalificado3.services.ApiService;
import com.alvarado.pe.labcalificado3.services.ApiServiceGenerator;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private Integer id;

    private ImageView fotoImage;
    private TextView tituloText;
    private TextView usuarioText;
    private TextView ubicacionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        fotoImage = findViewById(R.id.foto_image);
        tituloText = findViewById(R.id.titulo_text);
        usuarioText = findViewById(R.id.usuario_text);
        ubicacionText = findViewById(R.id.usuario_text);

        id = getIntent().getExtras().getInt("ID");
        Log.e(TAG, "id:" + id);

        initialize();
    }

    private void initialize() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<Reporte> call = service.showReporte(id);

        call.enqueue(new Callback<Reporte>() {
            @Override
            public void onResponse(Call<Reporte> call, Response<Reporte> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        Reporte reporte = response.body();
                        Log.d(TAG, "reporte: " + reporte);

                        String url = ApiService.API_BASE_URL + "/images/" + reporte.getFoto();
                        Picasso.with(DetailActivity.this).load(url).into(fotoImage);

                        tituloText.setText(reporte.getTitulo());
                        usuarioText.setText(reporte.getCiudadano_idciudadano());
                        ubicacionText.setText(reporte.getUbicacion());

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<Reporte> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
}
