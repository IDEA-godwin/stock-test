package com.example.stocks.service;

import com.example.stocks.model.Stock;
import com.example.stocks.repository.StocksRepository;
import com.example.stocks.service.DTO.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class StockService {

    private StocksRepository stocksRepository;

    @Autowired
    private void setBeans(StocksRepository stocksRepository) {
        this.stocksRepository = stocksRepository;
    }

    public List<Stock> getAllStock() {
        return stocksRepository.findAll();
    }

    public Stock createStock(StockDTO stockDTO) {
         Stock stock = new Stock(stockDTO.getName(), stockDTO.getCurrentPrice());
         return stocksRepository.save(stock);
    }

    public Stock getSingleStock(Long id) {
        return stocksRepository.findById(id)
            .orElse(null);
    }

    public Stock updateStock(Long id, StockDTO stockDTO) {
        if(stocksRepository.findById(id).isPresent()) {
            Stock stock = stocksRepository.findById(id).get();
            stock.setName(stockDTO.getName());
            stock.setCurrentPrice(stockDTO.getCurrentPrice());
            stock.setLastUpdate(new Date());
            return stocksRepository.save(stock);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Stock with id %d not found", id));
    }

    public void deleteSinglePost(Long id) {
        stocksRepository.deleteById(id);
    }
}
