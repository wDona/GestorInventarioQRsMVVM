package dev.wdona.gestorinventarioqr.data.repository;

import dev.wdona.gestorinventarioqr.data.datasource.LocalEstanteriaDataSource;
import dev.wdona.gestorinventarioqr.data.datasource.RemoteEstanteriaDataSource;
import dev.wdona.gestorinventarioqr.domain.model.Estanteria;

public class EstanteriaRepositoryImpl implements EstanteriaRepository{

    RemoteEstanteriaDataSource remote;
    LocalEstanteriaDataSource local;

    @Override
    public Estanteria getEstanteriaById(int id) {
        try {
            Estanteria estanteria = remote.getEstanteriaById(id);
            if (estanteria != null) {
                return estanteria;
            } else {
                System.out.println("Estanteria null");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return local.getEstanteriaById(id);
    }
}
