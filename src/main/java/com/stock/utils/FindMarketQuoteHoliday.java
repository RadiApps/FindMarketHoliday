package com.stock.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.stock.model.StockQuote;

public class FindMarketQuoteHoliday {

  public List<LocalDate> FindMarketHoliday(List<StockQuote> data) {
    List<LocalDate> missingDates = new ArrayList<LocalDate>();
    int weekEndDaysCount = 2;
    int normalWeekDays = 5;

    for (int i = 0; i < data.size(); i++) {

      LocalDate d = data.get(i).getDayDate();
      int tempJump = normalWeekDays - (d.getDayOfWeek().ordinal() + 1);

      if (tempJump <= 0) {
        LocalDate nextWeekDay = d.plusDays(weekEndDaysCount + 1);
        if (!nextWeekDay.equals(data.get(i + 1).getDayDate())) {
          missingDates.add(nextWeekDay);
          continue;
        }
      }

      LocalDate endWeek = d.plusDays(tempJump);
      if (i + tempJump < data.size() && endWeek.equals(data.get(i + tempJump).getDayDate())) {
        i += tempJump;
      } else if (i < data.size() - 1 && !d.plusDays(1).equals(data.get(i + 1).getDayDate())) {
        missingDates.add(d.plusDays(1));
      }
    }

    return missingDates;
  }

}
