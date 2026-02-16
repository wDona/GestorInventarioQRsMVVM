package dev.wdona.gestorinventarioqr.data.datasource;

import dev.wdona.gestorinventarioqr.data.api.EstanteriaApi;
import dev.wdona.gestorinventarioqr.domain.model.Estanteria;

public class RemoteEstanteriaDataSource {
    EstanteriaApi api;

    public RemoteEstanteriaDataSource(EstanteriaApi api) {
        this.api = api;
    }

    public Estanteria getEstanteriaById(int id) {
        return api.getEstanteriaById(id);
    }
}
