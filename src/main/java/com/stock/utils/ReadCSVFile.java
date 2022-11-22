package com.stock.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.stock.model.StockQuote;

public class ReadCSVFile {

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
  private final int skipCsvLineCount = 1;
  private final String splitCsvLineBy = ",";

  public List<StockQuote> readFile(String filename) {
    List<StockQuote> data = new ArrayList<StockQuote>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      data = br.lines().skip(skipCsvLineCount).map(line -> mapLineToStock(line))
          .collect(Collectors.toList());
    } catch (FileNotFoundException e) {
      System.out.println("ERROR: File not found " + filename);
    } catch (IOException e) {
      System.out.println("ERROR: Could not read " + filename);
    }
    return data;
  }

  private StockQuote mapLineToStock(String line) {
    String[] p = line.split(splitCsvLineBy);// a CSV has comma separated lines

    return StockQuote.builder().dayDate(LocalDate.parse(p[0].toString(), formatter))
        .open(Double.parseDouble(p[1])).high(Double.parseDouble(p[2])).low(Double.parseDouble(p[3]))
        .close(Double.parseDouble(p[4])).adjClose(Double.parseDouble(p[5]))
        .volum(Long.valueOf(p[6])).build();

  }
}
