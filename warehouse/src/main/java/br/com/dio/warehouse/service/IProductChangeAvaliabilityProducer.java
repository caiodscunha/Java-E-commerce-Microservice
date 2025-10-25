package br.com.dio.warehouse.service;

import br.com.dio.warehouse.dto.StockStatusMessage;

public interface IProductChangeAvaliabilityProducer {

    void notifyStatusChange(final StockStatusMessage message);
}
