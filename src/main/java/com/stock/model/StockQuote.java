package com.stock.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockQuote {
  private LocalDate dayDate;
  private double open;
  private double high;
  private double low;
  private double close;
  private double adjClose;
  private Long volum;
}
