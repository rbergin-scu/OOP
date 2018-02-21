
##### Riley Bergin<br />Mason Bruce<br />COEN160
##Deliverable 1

### <u>Use Case Diagram</u>
![](UseCaseDiagram.png)


### <u>CRC Cards</u>
<!--CCU-->
<table style='width:70%; border: 1px solid black;'>
	 <thead>
        <tr>
            <th colspan="2" style="text-align: left;">
            		CampusCardUser
            </th>
        </tr>
        
    </thead>
	<tr style='width:100%;'>
		<th style='width:50%;'>
			Responsibility
		</td>
		<th style='width:50%;'>
			Collaborator
		</td>
	</tr>
	<tr style='width:100%;'>
		<td style='width:50%;'>
			Knows name, contact info
		</td>
		<td style='width:50%;'>
		
		</td>
	</tr>
	<tr style='width:100%;'>
		<td style='width:50%;'>
			Knows food preferences, and initial balance
		</td>
		<td style='width:50%;'>
			CampusCard
		</td>
	</tr>
</table>
<!--Store-->
<table style='width:70%; border: 1px solid black;'>
	 <thead>
        <tr>
            <th colspan="2" style="text-align: left;">
            		Store
            </th>
        </tr>
        
    </thead>
	<tr style='width:100%;'>
		<th style='width:50%;'>
			Responsibility
		</td>
		<th style='width:50%;'>
			Collaborator
		</td>
	</tr>
	<tr style='width:100%;'>
		<td style='width:50%;'>
			Displays Expense Profile, Dietary Profile
		</td>
		<td style='width:50%;'>
			CampusCard
		</td>
	</tr>
	<tr style='width:100%;'>
		<td style='width:50%;'>
			Displays items to purchase
		</td>
		<td style='width:50%;'>
			SnackItem
		</td>
	</tr>
</table>

<!--CampusCard-->
<table style='width:70%; border: 1px solid black;'>
	 <thead>
        <tr>
            <th colspan="2" style="text-align: left;">
            		CampusCard
            </th>
        </tr>
        
    </thead>
	<tr style='width:100%;'>
		<th style='width:50%;'>
			Responsibility
		</td>
		<th style='width:50%;'>
			Collaborator
		</td>
	</tr>
	<tr style='width:100%;'>
		<td style='width:50%;'>
			Maintains credit balance
		</td>
		<td style='width:50%;'>
			CampusCardUser
		</td>
	</tr>
	<tr style='width:100%;'>
		<td style='width:50%;'>
			Maintains Dietary Profile and Expense Profile
		</td>
		<td style='width:50%;'>
			DietaryProfile, ExpenseProfile, Store
		</td>
	</tr>
</table>

<!--SnackItem-->
<table style='width:70%; border: 1px solid black;'>
	 <thead>
        <tr>
            <th colspan="2" style="text-align: left;">
            		SnackItem
            </th>
        </tr>
        
    </thead>
	<tr style='width:100%;'>
		<th style='width:50%;'>
			Responsibility
		</td>
		<th style='width:50%;'>
			Collaborator
		</td>
	</tr>
	<tr style='width:100%;'>
		<td style='width:50%;'>
			Knows its type of food, price and caloric value
		</td>
		<td style='width:50%;'>
		
		</td>
	</tr>
	<tr style='width:100%;'>
		<td style='width:50%;'>
			Allows itself to be sold
		</td>
		<td style='width:50%;'>
			Store
		</td>
	</tr>
</table>

<!--ExpenseProfile-->
<table style='width:70%; border: 1px solid black;'>
	 <thead>
        <tr>
            <th colspan="2" style="text-align: left;">
            		ExpenseProfile
            </th>
        </tr>
        
    </thead>
	<tr style='width:100%;'>
		<th style='width:50%;'>
			Responsibility
		</td>
		<th style='width:50%;'>
			Collaborator
		</td>
	</tr>
	<tr style='width:100%;'>
		<td style='width:50%;'>
			Tracks money spent
		</td>
		<td style='width:50%;'>
			CampusCard
		</td>
	</tr>
	<tr style='width:100%;'>
		<td style='width:50%;'>
			Calculates current balance
		</td>
		<td style='width:50%;'>
			
		</td>
	</tr>
</table>
