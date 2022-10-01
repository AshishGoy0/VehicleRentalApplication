# vehicle-rental

# Features
Rental service has multiple branches throughout the city.<br />
Each branch has a limited number of different kinds of vehicles.<br />
Each vehicle can be booked with a predefined fixed price.<br />
Each vehicle can be booked in multiples of 1-hour slots each. (Lets assume slots of a single day)<br />

# Requirements
Onboard a new branch with available vehicles.<br />
Onboard new vehicle(s) of an existing type to a particular branch.<br />
Rent a vehicle for a time slot and a vehicle type(the lowest price as the default choice extendable to any other strategy).<br />
Display available vehicles for a given branch sorted on price.<br />
The vehicle will have to be dropped at the same branch where it was picked up.<br />
Dynamic pricing – demand vs supply. If 80% of cars in a particular branch are booked, increase the price by 10%.<br />

# Sample files for better understanding
src/main/resources/TestData.txt<br />
src/main/resources/DynamicCost.txt<br />

# Build
./gradlew clean build<br />
