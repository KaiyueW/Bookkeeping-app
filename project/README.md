# Kaiyue Wang's Personal Project

## Introduction


The application is an bookkeeping app which can keep track of the money. Basically, it can record the money earned and spent. It also breaks down where each spend goes, such as food, clothing, housing and transportation. By using this application, users can know where the money goes and the amount of money they spent in each month. In such case, they will be able to manage their money better and more wisely.

This application reaches a wide audience. Students and office workers can use it for daily bookkeeping. Families can use it to record household expenses. 

I came up with this idea because I want to manage my money better. I wasn't in the habit of bookkeeping before and didn't notice where the money went. The result is I have difficulty controlling the amount of money I spent and will be short of money by the end of the month. So, I want to desigh such an app to help users manage their money better.

## User Stories
- As a user, I want to be able to record the amount of money spent and what it is used for.
- As a user, I want to be able to record the amount of income money and the source of it.
- As a user, I want to be able to add a money change to the records and the total income and expenditure.
- As a user, I want to be able to view a list of records and the total amount of money.
- As a user, I want to be able to delete a record from the list of records.
- As a user, when I select the quit option from the application menu, I want to be reminded to save my records to file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my record list from file and resume exactly where they left off at some earlier time.

# Instructions for Grader

- You can add multiple records to a list of records by clicking the "Add Record" button and type the related infomation.
- You can view all the records by clicking the "View Records" button.
- You can delete one record by clicking the "Delete Record" button.
- You can view the money summary by clicking the "View Summary" button.
- You can locate my visual component when opening the application.
- You can save the state of my application by clicking the "Quit" button and choose "Yes/No" to decide whether you want to save the records.
- You can reload the state of my application by clicking the "Load Records" button.

# Phase 4: Task 2
*There is a sample of the events that occur:*

Sun Aug 04 21:43:11 PDT 2024
Add a new record to the list.

Sun Aug 04 21:43:16 PDT 2024
Add a new record to the list.

Sun Aug 04 21:43:17 PDT 2024
Show the list of records.

Sun Aug 04 21:43:20 PDT 2024
Show the money summary of the records: -3.0

Sun Aug 04 21:43:22 PDT 2024
Show the list of records.

Sun Aug 04 21:43:25 PDT 2024
Show the list of records.

Sun Aug 04 21:43:25 PDT 2024
Show the list of records.

Sun Aug 04 21:43:25 PDT 2024
Show the list of records.

Sun Aug 04 21:43:25 PDT 2024
Delete a record from the list.


# Phase 4: Task 3
In the GUI class, I think I can extract some duplicate codes to a helper method and pass the corresponding parameters in that method. For example, when adding 6 buttons in the panel, there are lots of duplicate methods like setActionCommand and addActionListener. To refactor, I can extract them to a helper method, which can reduce duplicates in the code.

Moreover, according to the single responsible principle, we need to center each method on one concept. Before, in the GUI class, I mixed some methods with different responsibilities together. For example, I also setVisible and setResizable of the panel in the loadImages method. I think I can split them by adding more helper methods and put the previous methods with the same concept into one helper method. This can increase the cohesion of the program.