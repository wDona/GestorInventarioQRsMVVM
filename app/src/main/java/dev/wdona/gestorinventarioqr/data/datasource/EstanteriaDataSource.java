package dev.wdona.gestorinventarioqr.data.datasource;

import dev.wdona.gestorinventarioqr.domain.model.Estanteria;

public interface EstanteriaDataSource {
    Estanteria getEstanteriaById(int id);
}
