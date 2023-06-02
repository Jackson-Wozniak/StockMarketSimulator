package org.api.stockmarket.stocks.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.stocks.earnings.dto.EarningsDTO;
import org.api.stockmarket.stocks.earnings.entity.EarningsReport;
import org.api.stockmarket.stocks.news.dto.NewsDTO;
import org.api.stockmarket.stocks.news.entity.News;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.entity.StockHistory;
import org.api.tradinggame.account.utils.CalculateCostBasisAndProfits;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class StockDTO {

    private String ticker;
    private String companyName;
    private String sector;
    private String marketCap;
    private double price;
    private double lastDayPrice;
    private double percentChange;
    private int momentum;
    private int momentumStreakInDays;
    private String volatileStock;
    private String investorRating;
    private List<NewsDTO> newsHistory;
    private List<EarningsDTO> earningsHistory;
    private List<StockHistoryDTO> stockHistory;

    public StockDTO(Stock stock, List<StockHistory> stockHistory) {
        this.ticker = stock.getTicker();
        this.companyName = stock.getCompanyName();
        this.sector = stock.getSector();
        this.marketCap = String.valueOf(stock.getMarketCap());
        this.price = stock.getPrice();
        this.lastDayPrice = stock.getLastDayPrice();
        this.momentum = stock.getMomentum();
        this.momentumStreakInDays = stock.getMomentumStreakInDays();
        this.volatileStock = String.valueOf(stock.getVolatileStock());
        this.investorRating = String.valueOf(stock.getInvestorRating());
        this.newsHistory = stock.getNewsHistory().stream()
                .map(NewsDTO::new)
                .collect(Collectors.toList());
        this.earningsHistory = stock.getEarningsHistory().stream()
                .map(EarningsDTO::new)
                .collect(Collectors.toList());
        this.stockHistory = stockHistory.stream()
                .map(StockHistoryDTO::new)
                .collect(Collectors.toList());
        this.percentChange = getPercentChange(this.getPrice(), this.getLastDayPrice());
    }

    public double getPercentChange(double currentPrice, double lastDayPrice) {
        if(lastDayPrice == 0) return 0.0;
        return CalculateCostBasisAndProfits.roundToTwoDecimalPlaces(
                (currentPrice - lastDayPrice) / lastDayPrice * 100);
    }
}
