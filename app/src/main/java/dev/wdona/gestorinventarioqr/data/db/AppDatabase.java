package dev.wdona.gestorinventarioqr.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dev.wdona.gestorinventarioqr.data.entity.EstanteriaEntity;
import dev.wdona.gestorinventarioqr.data.entity.ProductoEntity;

@Database(entities = {ProductoEntity.class, EstanteriaEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductoDao productoDao();
    public abstract EstanteriaDao estanteriaDao();

    private static volatile AppDatabase INSTANCE;
    private static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();


    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "inventario_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public void populateInitialData() {
        databaseWriteExecutor.execute(() -> {
            EstanteriaDao dao = estanteriaDao();
            if (dao.getCount() == 0) {
                EstanteriaEntity est1 = new EstanteriaEntity();
                est1.setId(1L);
                est1.setNombre("Estanteria 1");
                dao.insertEstanteria(est1);

                EstanteriaEntity est2 = new EstanteriaEntity();
                est2.setId(2L);
                est2.setNombre("Estanteria 2");
                dao.insertEstanteria(est2);

                // También insertar productos de prueba
                ProductoDao prodDao = productoDao();
                ProductoEntity prod1 = new ProductoEntity();
                prod1.setId(1L);
                prod1.setNombre("Producto A");
                prod1.setPrecio(10.0);
                prod1.setCantidad(10);
                prod1.setFK_estanteriaId(1L);
                prodDao.insertProducto(prod1);

                ProductoEntity prod2 = new ProductoEntity();
                prod2.setId(2L);
                prod2.setNombre("Producto B");
                prod2.setPrecio(20.0);
                prod2.setCantidad(5);
                prod2.setFK_estanteriaId(2L);
                prodDao.insertProducto(prod2);
            }
        });
    }
}
