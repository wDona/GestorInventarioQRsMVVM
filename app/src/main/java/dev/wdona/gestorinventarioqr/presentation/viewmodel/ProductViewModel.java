package dev.wdona.gestorinventarioqr.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import dev.wdona.gestorinventarioqr.data.repository.ProductRepository;
import dev.wdona.gestorinventarioqr.domain.model.Producto;

public class ProductViewModel {
    ProductRepository repository;

    public ProductViewModel(ProductRepository repository) {
        this.repository = repository;
    }

    private MutableLiveData<List<Producto>> _productosLiveData = new MutableLiveData<>();
    public LiveData<List<Producto>> productosLiveData = _productosLiveData;


    public void addUndsProduct(String nombre, int unidadesSumadas) {

        if (unidadesSumadas <= 0) System.out.println("Error: No se pueden agregar unidades negativas o cero.");
        else {

        }
    }
}
