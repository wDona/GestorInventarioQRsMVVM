package dev.wdona.gestorinventarioqr.data.db;

import dev.wdona.gestorinventarioqr.domain.model.Estanteria;

public interface EstanteriaDao {
    Estanteria getEstanteriaById(int id);
}
