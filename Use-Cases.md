## Use-Cases

### Template

####Use-case: 
*Actors*: 

*Type*: 

*Description*:

*Cross-references*:

*Scenario Details*:

1. Something happens
2. Something else happens

*Alternative Courses*:

* Line x: this happens instead


####Use-case: Activate a card
*Actors*: CCU

*Type*: Primary, Essential

*Description*: Community card user wants to activate their card.

*Cross-references*:

*Scenario Details*:

1. User inserts card
2. Prompts for user's name and contact info
3. User enters name and contact info
2. Prompts for dietary limit
3. User enters dietary limit in calories
4. Prompts for expense limit per month
5. User enters expense limit
6. Card becomes active
7. User removes and takes card

*Alternative Courses*:

* Line 2, 4, or 6: User enters bad input, reprompted for correct input

####Use-case: View expenses
*Actors*: CCU, Store

*Type*: Secondary, essential

*Description*: Community card user wants to view their expenses for the month.

*Cross-references*: View Dietary profile

*Scenario Details*:

1. User inserts card
2. UI displays options
3. User selects "View Expenses"
4. Generates and displays expense profile (current date, money spent so far, and remaining funds)
5. User closes session

*Alternative Courses*:

* Line 1: User may also enter their card number and password, which may be incorrect

####Use-case: View dietary profile
*Actors*: CCU, Store

*Type*: Secondary, essential

*Description*: Community card user wants to view their dietary profile.

*Cross-references*: View Expenses

*Scenario Details*:

1. User inserts card
2. UI displays options
3. User selects "View Dietary Profile"
4. Generates and displays dietary profile (current date, permitted calories per day range, calories spent today, calories left to spend)
5. User closes session

*Alternative Courses*:

* Line 1: User may also enter their card number and password, which may be incorrect

####Use-case: Buy a food item
*Actors*: CCU, Store

*Type*: Primary, essential

*Description*: User wants to select and purchase a food item from the vending machine.

*Cross-references*: Display food items

*Scenario Details*: 

1. User inserts card
2. System displays food options with names and prices
3. User selects a food item
4. System prompts user for confirmation message
5. User confirms purchase
6. Item's calorie and price are deducted from user's dietary and expense limits
7. Item is dispensed


*Alternative Courses*:

* Line 1: User may also enter their card number and password, which may be incorrect
* Line 6: User may not have enough funds or be above calorie limit, in which item is not dispensed and warning message is displayed.

####Use-case: Manage items
*Actors*: Store

*Type*: Secondary, real

*Description*: Store wants to add or remove items for sale

*Cross-references*:

*Scenario Details*:

1. Array of items is accessed by store
2. From appropriate function call, store removes and item from list OR store instantiates a new item (with name, price, and calorie count) and adds it to list.

*Alternative Courses*:
