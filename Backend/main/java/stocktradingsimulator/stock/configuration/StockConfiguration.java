package stocktradingsimulator.stock.configuration;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import stocktradingsimulator.stock.defaults.DefaultStocksList;
import stocktradingsimulator.stock.service.StockService;

import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
public class StockConfiguration {

    @Autowired
    private StockService stockService;

    private final Logger logger = LoggerFactory.getLogger(StockConfiguration.class);

    @PostConstruct
    public void saveStocksToDatabaseOnStartup(){
        if(DefaultStocksList.getCountForDefaultStocks() != stockService.findStockRowCount()){
            logger.info("Saving Default Stocks");
            stockService.saveDefaultStockToDatabase(DefaultStocksList.getAllDefaultStocks());
        }
    }
}