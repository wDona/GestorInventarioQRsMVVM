package dev.wdona.gestorinventarioqr.data.api.impl;

import dev.wdona.gestorinventarioqr.data.api.EstanteriaApi;
import dev.wdona.gestorinventarioqr.domain.model.Estanteria;
import dev.wdona.gestorinventarioqr.mock.MockDatabaseController;
import dev.wdona.gestorinventarioqr.mock.MockDatabaseOperations;

public class EstanteriaApiImpl implements EstanteriaApi {
    MockDatabaseOperations mockDatabaseOperations = new MockDatabaseController();

    @Override
    public Estanteria getEstanteriaById(Long id) {
        try {
            return mockDatabaseOperations.getEstanteriaById(id);
        } catch (Exception e) {
            System.out.println("Error al obtener estanteria por ID: " + e.getMessage());
            return null;
        }
    }
}
