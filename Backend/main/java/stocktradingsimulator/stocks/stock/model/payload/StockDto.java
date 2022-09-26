package stocktradingsimulator.stocks.stock.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import stocktradingsimulator.stocks.earnings.EarningsReport;
import stocktradingsimulator.stocks.stock.enums.MarketCap;
import stocktradingsimulator.stocks.news.News;
import stocktradingsimulator.stocks.stock.model.entity.Stock;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StockDto implements Serializable {

    private String ticker;
    private String companyName;
    private String sector;
    private MarketCap marketCap;
    private Double price;
    private Double lastDayPrice;
    private Integer optimism;
    private Boolean volatileStock;
    private List<News> newsHistory;
    private List<EarningsReport> earningsHistory;

    public StockDto(Stock stock){
        this.ticker = stock.getTicker();
        this.companyName = stock.getCompanyName();
        this.sector = stock.getSector();
        this.marketCap = stock.getMarketCap();
        this.price = stock.getPrice();
        this.lastDayPrice = stock.getLastDayPrice();
        this.optimism = stock.getMomentum();
        this.volatileStock = stock.getVolatileStock();
        this.newsHistory = stock.getNewsHistory();
        this.earningsHistory = stock.getEarningsHistory();
    }
}