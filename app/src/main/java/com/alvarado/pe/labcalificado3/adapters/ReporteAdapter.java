package com.alvarado.pe.labcalificado3.adapters;

/**
 * Created by Alumno on 11/05/2018.
 */
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.alvarado.pe.labcalificado3.R;
import com.alvarado.pe.labcalificado3.activities.DetailActivity;
import com.alvarado.pe.labcalificado3.models.Reporte;
import com.alvarado.pe.labcalificado3.services.ApiService;
import com.squareup.picasso.Picasso;

public class ReporteAdapter extends RecyclerView.Adapter<ReporteAdapter.ViewHolder> {

    private static final String TAG = ReporteAdapter.class.getSimpleName();
    private List<Reporte> reportes;
    private Activity activity;
    public ReporteAdapter(Activity activity){
        this.reportes = new ArrayList<>();
        this.activity = activity;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titulo;
        public TextView usuario;
        public TextView ubicacion;
        public ImageView fotoImage;



        public ViewHolder(View itemView) {
            super(itemView);
            titulo =  itemView.findViewById(R.id.titulo_text);
            usuario =  itemView.findViewById(R.id.usuario_text);
            ubicacion =  itemView.findViewById(R.id.ubicacion_text);
            fotoImage = itemView.findViewById(R.id.foto_image);
        }
    }

    @Override
    public ReporteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReporteAdapter.ViewHolder viewHolder, int position) {
        final Reporte reporte = this.reportes.get(position);
        viewHolder.titulo.setText(reporte.getTitulo());
        viewHolder.usuario.setText(reporte.getCiudadano_idciudadano());
        String url = ApiService.API_BASE_URL + "/images/" + reporte.getFoto();
        Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoImage);
        viewHolder.ubicacion.setText(reporte.getUbicacion());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra("ID", reporte.getId());
                activity.startActivity(intent);
            }

        });
/*
 viewHolder.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), v);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.remove_button:

                                ApiService service = ApiServiceGenerator.createService(ApiService.class);

                                Call<ResponseMessage> call = service.destroyProducto(producto.getId());

                                call.enqueue(new Callback<ResponseMessage>() {
                                    @Override
                                    public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                                        try {

                                            int statusCode = response.code();
                                            Log.d(TAG, "HTTP status code: " + statusCode);

                                            if (response.isSuccessful()) {

                                                ResponseMessage responseMessage = response.body();
                                                Log.d(TAG, "responseMessage: " + responseMessage);

                                                // Eliminar item del recyclerView y notificar cambios
                                                productos.remove(position);
                                                notifyItemRemoved(position);
                                                notifyItemRangeChanged(position, productos.size());

                                                Toast.makeText(v.getContext(), responseMessage.getMessage(), Toast.LENGTH_LONG).show();

                                            } else {
                                                Log.e(TAG, "onError: " + response.errorBody().string());
                                                throw new Exception("Error en el servicio");
                                            }

                                        } catch (Throwable t) {
                                            try {
                                                Log.e(TAG, "onThrowable: " + t.toString(), t);
                                                Toast.makeText(v.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                            }catch (Throwable x){}
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseMessage> call, Throwable t) {
                                        Log.e(TAG, "onFailure: " + t.toString());
                                        Toast.makeText(v.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                    }

                                });

                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        })
 */









    }

        @Override
        public int getItemCount () {
            return this.reportes.size();
        }

}