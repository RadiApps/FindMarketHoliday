package com.stock.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.stock.model.StockQuote;

public class FindMarketQuoteHoliday {

  public List<LocalDate> FindMarketHoliday(List<StockQuote> data) {
    List<LocalDate> missingDates = new ArrayList<LocalDate>();
    /**
     * Weekend days count[saturday-sunday] , Normal weekdays[Monday-Friday]
     */
    int weekEndDaysCount = 2;
    int normalWeekDays = 5;

    /*
     * loop for array items that contains all lines from csv
     */
    for (int i = 0; i < data.size(); i++) {

      LocalDate currentItemDate = data.get(i).getDayDate();
      // Get tempJump for end of this week,by know which day in the week
      int tempJump = normalWeekDays - (currentItemDate.getDayOfWeek().ordinal() + 1);

      // In case the list started in last working day in week like [Friday], this means expecting
      // the nest element is the start of next week
      if (tempJump <= 0) {
        LocalDate nextWeekDay = currentItemDate.plusDays(weekEndDaysCount + 1);
        // If the next item in array doesn't equal start of next week according to tempJump
        // This means the start of next week is missing
        if (!nextWeekDay.equals(data.get(i + 1).getDayDate())) {
          missingDates.add(nextWeekDay);
          continue;
        }
      }

      // If its start of weekdays,i will plus using the tempJump and after i+tempJump expecting same
      // date,if not this mean there is missing days in this week
      // So i will go with it to find the missing in this week
      LocalDate endWeek = currentItemDate.plusDays(tempJump);
      if (i + tempJump < data.size() && endWeek.equals(data.get(i + tempJump).getDayDate())) {
        i += tempJump;
      } else if (i < data.size() - 1
              && !currentItemDate.plusDays(1).equals(data.get(i + 1).getDayDate())) {
        missingDates.add(currentItemDate.plusDays(1));
      }
    }
    return missingDates;
  }
  public List<LocalDate>  FindMarketHoliday2(List<StockQuote> data){

    Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
            || date.getDayOfWeek() == DayOfWeek.SUNDAY;

    LocalDate startDate=data.get(0).getDayDate();
    LocalDate endDate=data.get(data.size()-1).getDayDate();

    List<LocalDate> allDataDates=data.stream().map(StockQuote::getDayDate).collect(Collectors.toList());

    List<LocalDate> missingDays = startDate.datesUntil(endDate)
            .filter(isWeekend.negate()).filter(d->!allDataDates.contains(d))
            .collect(Collectors.toList());

    return missingDays;
  }
  /*public static void main(String[] args){
    ReadCSVFile read = new ReadCSVFile();
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    String path=classloader.getResource("EPAM-JULY-2022.csv").getPath();
    List<StockQuote> data = read.readFile(path);
    FindMarketHoliday2(data);
  }*/
}