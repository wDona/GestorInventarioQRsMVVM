package dev.wdona.gestorinventarioqr.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import dev.wdona.gestorinventarioqr.R;
import dev.wdona.gestorinventarioqr.data.api.impl.EstanteriaApiImpl;
import dev.wdona.gestorinventarioqr.data.api.impl.ProductoApiImpl;
import dev.wdona.gestorinventarioqr.data.datasource.local.impl.EstanteriaLocalDataSourceImpl;
import dev.wdona.gestorinventarioqr.data.datasource.local.impl.ProductoLocalDataSourceImpl;
import dev.wdona.gestorinventarioqr.data.datasource.remote.impl.EstanteriaRemoteDataSourceImpl;
import dev.wdona.gestorinventarioqr.data.datasource.remote.impl.ProductoRemoteDataSourceImpl;
import dev.wdona.gestorinventarioqr.data.db.AppDatabase;
import dev.wdona.gestorinventarioqr.data.db.EstanteriaDao;
import dev.wdona.gestorinventarioqr.data.db.ProductoDao;
import dev.wdona.gestorinventarioqr.data.repository.EstanteriaRepositoryImpl;
import dev.wdona.gestorinventarioqr.data.repository.ProductoRepositoryImpl;
import dev.wdona.gestorinventarioqr.mock.MockDatabaseController;
import dev.wdona.gestorinventarioqr.presentation.viewmodel.EstanteriaViewModel;
import dev.wdona.gestorinventarioqr.presentation.viewmodel.ProductoViewModel;

public class MainActivity extends AppCompatActivity {

    // TODO: MOCKEAR BASE DE DATOS CON 3 ESTANTERIAS Y 5 PRODUCTOS, CON SUS CANTIDADES
    // TODO: Pantalla principal con botones para escanear codigo QR de producto y estanteria
    // TODO: Pantalla de escaneo de codigo QR, con opciones para agregar, quitar o asignar producto a estanteria
    // TODO: Pantalla de consulta de estanteria, mostrando los productos que hay en esa estanteria y sus cantidades
    // TODO: Pantalla de historial de operaciones, mostrando las operaciones realizadas y las pendientes (si se actua offline)
    // TODO: Implementar funcionalidad de escaneo de codigo QR, utilizando una libreria como ZXing o ML Kit

    // TODO: Lectura de codigo QR
    // TODO: Base de datos mockeada con productos y sus cantidades
    // TODO: Al escanear un QR de estanteria - consultar que productos hay en esa estanteria
    // TODO: Al escanear un codigo QR de producto - anadir, quitar y mover/asignar a estanteria
    //      FACILITAR ESCANEO DE ESTANTERIA - ESCANEO PRODUCTO - ESCANEO CANTIDAD - CONFIRMAR
    // TODO: Registrar operaciones localmente para mostrar historial y pendientes
    //      PARA PENDIENTES, "DESACTIVAR" MOCK DE BASE DE DATOS "ONLINE" Y ACTUAR OFFLINE

    /**
     * Arquitectura
     * ViewModels -> Repositories -> DataSources (Local/Remote)
     * ViewModels: Gestionan la lógica de UI y exponen datos a la vista.
     * Repositories: Actúan como intermediarios entre los ViewModels y las fuentes de datos, gestionando la lógica de negocio.
     * DataSources: Encapsulan el acceso a los datos, ya sea local (SQLite, Room (DAO)) o remoto (API) (RemoteDataSource, LocalDataSource).
     */

    EstanteriaViewModel estanteriaViewModel;
    ProductoViewModel productoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentes();

        Button btnScanEstanteria = findViewById(R.id.btnScan);
        btnScanEstanteria.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ScanActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void inicializarComponentes() {
        EstanteriaApiImpl estanteriaApi = new EstanteriaApiImpl();
        ProductoApiImpl productoApi = new ProductoApiImpl();

        AppDatabase appDatabase = AppDatabase.getDatabase(getApplicationContext());
        ProductoDao productoDao = appDatabase.productoDao();
        EstanteriaDao estanteriaDao = appDatabase.estanteriaDao();

        EstanteriaLocalDataSourceImpl estanteriaLocalDataSource = new EstanteriaLocalDataSourceImpl(estanteriaDao);
        ProductoLocalDataSourceImpl productoLocalDataSource = new ProductoLocalDataSourceImpl(productoDao, estanteriaDao);

        EstanteriaRemoteDataSourceImpl estanteriaRemoteDataSource = new EstanteriaRemoteDataSourceImpl(estanteriaApi);
        ProductoRemoteDataSourceImpl productoRemoteDataSource = new ProductoRemoteDataSourceImpl(productoApi);

        EstanteriaRepositoryImpl estanteriaRepository = new EstanteriaRepositoryImpl(estanteriaRemoteDataSource, estanteriaLocalDataSource);
        ProductoRepositoryImpl productoRepository = new ProductoRepositoryImpl(productoRemoteDataSource, productoLocalDataSource);

        this.estanteriaViewModel = new EstanteriaViewModel(estanteriaRepository);
        this.productoViewModel = new ProductoViewModel(productoRepository);

        appDatabase.populateInitialData();
        MockDatabaseController.initialize();

    }
}