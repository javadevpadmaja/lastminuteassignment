Description:

Given
airport of origin
airport of destination
date of departure
number of adult, child and infant passengers
When you perform the search
Then you get the list of:
flight code matching the route
total flight price (for all passengers)

Pricing rules
Days to departure date
days prior to the departure date
% of the base price
more than 30     80%
30 - 16         100%
15 - 3          120%
less that 3     150%

Passenger type
passenger type  price
adult   full price (i.e. price resulting from the days to departure date rule)
child   33% discount of the price calculated according to the days to departure date rule
infant  fixed price depending on the airline. Rule days to departure date is not applied for infants

Assumptions:
Search only for direct flights (no stopovers)
Assume there is always seat availability
All flights leave every day
There is only one currency (€)
