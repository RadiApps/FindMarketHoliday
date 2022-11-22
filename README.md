
# FindMarketHoliday

According to the given market quotes stock history, the app will find the market holidays.

## Features of the project 

Reading csv file line by line then mapping those lines to pojo class then looping all the objects 
to find missing holiday from history of market quotes for a stock.

## Technical idea that solved the problem

Givens -> CSV list of market quotes for stock , All lines in csv are sorted by date
Idea -> we already know that the weekdays is 5 started from monday till friday,
by using for loop i am checking which day in a week for the current record then jumping to i+5 in case 
my condition are true which is after 5 iteration will find the end of week.

## Tools and technologies

1. Java 1.8
2. Lombok 1.18.12
3. Junit jupiter 5.5.2
4. CSV
5. Spring tool suite

