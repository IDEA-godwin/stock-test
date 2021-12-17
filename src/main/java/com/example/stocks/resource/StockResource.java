package com.example.stocks.resource;

import com.example.stocks.model.Stock;
import com.example.stocks.service.DTO.StockDTO;
import com.example.stocks.service.StockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@Tag(name = "Stock", description = "CRUD for stocks")
public class StockResource {

    private StockService stockService;

    @Autowired
    private void setBeans(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(stockService.createStock(stockDTO));
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStock() {
        return ResponseEntity.ok(stockService.getAllStock());
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long stockId) {
        return ResponseEntity.ok(stockService.getSingleStock(stockId));
    }

    @PutMapping("/{stockId}")
    public ResponseEntity<Stock> updateStockInfo(@PathVariable Long stockId, @RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(stockService.updateStock(stockId, stockDTO));
    }

    @DeleteMapping("/{stockId}")
    public ResponseEntity<String> deleteStockById(@PathVariable Long stockId) {
        stockService.deleteSinglePost(stockId);
        return ResponseEntity.ok(String.format("stock with Id %d deleted", stockId));
    }
}
