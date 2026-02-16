package dev.wdona.gestorinventarioqr.data.datasource;

import dev.wdona.gestorinventarioqr.data.db.EstanteriaDao;
import dev.wdona.gestorinventarioqr.domain.model.Estanteria;

public class LocalEstanteriaDataSource {
    EstanteriaDao dao;

    public LocalEstanteriaDataSource(EstanteriaDao dao) {
        this.dao = dao;
    }

    public Estanteria getEstanteriaById(int id) {
        return dao.getEstanteriaById(id);
    }
}
