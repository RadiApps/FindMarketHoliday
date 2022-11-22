package com.stock.utils;

import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.stock.model.StockQuote;

class FindMarketQuoteHolidayTest {
  private static FindMarketQuoteHoliday marketQuote = null;
  private static ReadCSVFile read = null;
  private static ClassLoader classloader = null;
  private List<StockQuote> data;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    marketQuote = new FindMarketQuoteHoliday();
    read = new ReadCSVFile();
    classloader = Thread.currentThread().getContextClassLoader();
  }

  @BeforeEach
  void setUp() throws Exception {
    // data = read.readFile(""C:\\Users\\Radi\\Downloads\\EPAM-JULY.csv"); //incase we need to read
    // csv file outside the project
    data = read.readFile(classloader.getResource("EPAM.csv").getPath());
  }

  // @Test
  void testFindMarketHolidayJuly() {
    // expected missing dates from EPAM-JULY-2022.csv
    List<LocalDate> first = Arrays.asList(LocalDate.parse("2022-07-04"),
        LocalDate.parse("2022-07-14"), LocalDate.parse("2022-07-20"), LocalDate.parse("2022-07-22"),
        LocalDate.parse("2022-07-27"));

    List<LocalDate> second = marketQuote.FindMarketHoliday(data);

    assertTrue(
        first.size() == second.size() && first.containsAll(second) && second.containsAll(first));

  }

  // @Test
  void testFindMarketHolidayNov() {
    // expected missing dates from EPAM-NOV-2021.csv
    List<LocalDate> first = Arrays.asList(LocalDate.parse("2021-11-25"));

    List<LocalDate> second = marketQuote.FindMarketHoliday(data);

    assertTrue(
        first.size() == second.size() && first.containsAll(second) && second.containsAll(first));

  }

  @Test
  void testFindMarketHolidayAll() {
    // expected missing dates from EPAM.csv
    List<LocalDate> first = Arrays.asList(LocalDate.parse("2021-11-25"),
        LocalDate.parse("2021-12-24"), LocalDate.parse("2022-04-15"));
    List<LocalDate> second = marketQuote.FindMarketHoliday(data);
    assertTrue(
        first.size() == second.size() && first.containsAll(second) && second.containsAll(first));

  }

}
