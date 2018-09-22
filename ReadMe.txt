Title: Deduplication of Records.

Files: 
1) advanced.csv
2) normal.csv

Steps to run:

1) In the file "src\main\java\com\ishaan\deduplication\Controller\BaseController.java" change the file path in the function getData().

Assumptions:

1) email is unique to a user.
2) phone is unique to a user.

Approach:
This application detects duplicates on the following basis:

1) First It will check whether the email or phone is same or not. It it is same then it will return true.
2) If not then it will check whether the first name of the person has almost all letters same or does it sound same(using algorithms like levenshtein distance and metaphone).
3) If it is same then it will check whether the last name of the person has almost all letters same or does it sound same.
4) If yes then it will check if the company for which the person is working is almost same or address1 is same or not.

In the following cases the records will be considered duplicate.

Cases in which it will not be considered duplicate:
1) Will reject if email, phone, first name (almost), last name (almost), company (almost) and address1 (almost) do not match. 

We are not considering the factors like address2, zip, city, or state to detect duplicates because some of the records do not have these fields.

 