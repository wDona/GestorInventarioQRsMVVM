package dev.wdona.gestorinventarioqr.presentation.ui.scan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.wdona.gestorinventarioqr.R;
import dev.wdona.gestorinventarioqr.domain.model.Producto;

public class ProductoScanAdapter extends RecyclerView.Adapter<ProductoScanAdapter.ViewHolder> {

    private List<Producto> productos = new ArrayList<>();
    private OnProductoClickListener listener;

    public interface OnProductoClickListener {
        void onProductoClick(Producto producto);
    }

    public ProductoScanAdapter(OnProductoClickListener listener) {
        this.listener = listener;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos != null ? productos : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto_scan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.bind(producto);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNombre;
        private final TextView tvCantidad;
        private final TextView tvPrecio;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvProductoNombre);
            tvCantidad = itemView.findViewById(R.id.tvProductoCantidad);
            tvPrecio = itemView.findViewById(R.id.tvProductoPrecio);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && listener != null) {
                    listener.onProductoClick(productos.get(pos));
                }
            });
        }

        void bind(Producto producto) {
            tvNombre.setText(producto.getNombre());
            tvCantidad.setText("Cantidad: " + producto.getCantidad());
            tvPrecio.setText(String.format("%.2f €", producto.getPrecio()));
        }
    }
}

