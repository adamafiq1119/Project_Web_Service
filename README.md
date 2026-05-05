Predefined Calculation ModulesYour SOAP web service must implement the following three modules, ensuring each provides detailed output information to the client: 

i. Body Mass Index (BMI) CalculationPurpose: To categorize a user's weight relative to their height.  
Required Inputs: Weight (kg) and Height (m).  
Formula Logic: Use the standard formula $BMI = \frac{weight}{height^2}$. 
Detailed Output: The service should return the numeric BMI value and its classification (e.g., Underweight, Normal, Overweight, or Obese). 
  
ii. Body Fat Percentage CalculationPurpose: To estimate the total mass of fat divided by total body mass. 
Required Inputs: Gender, weight, height, and age. Some formulas may require additional measurements like waist or neck circumference. 
Formula Logic: You must refer to a valid scientific resource for this formula (such as the BMI-based formula or the Navy Method) and cite it in your report. 
Detailed Output: The percentage of body fat and its corresponding fitness category. 

iii. Calories Burn Rate CalculationPurpose: To determine how many calories a user burns during specific activities or as a daily baseline. 
Required Inputs: Weight, age, gender, and potentially the duration or intensity of an activity. 
Formula Logic: Typically uses the Basal Metabolic Rate (BMR) as a starting point, multiplied by an activity factor.
Detailed Output: Total calories burned, which can then be used as a reference for your new Calorie Deficit module.  

iv. Calorie Deficit CalculatorThis module helps users determine how many calories they should consume to lose weight
Formula: $\text{Daily Calories} = \text{TDEE} - \text{Deficit Amount}
$Note: TDEE (Total Daily Energy Expenditure) is usually calculated using the Mifflin-St Jeor Equation
Required Inputs: TDEE (calculated from module iii), weight goal (e.g., lose 0.5kg per week).
Output: Target daily caloric intake and estimated time to reach the goal.

v.Protein Intake CalculatorThis
module determines the daily protein requirement based on the user's activity level and fitness goals
Formula: $\text{Protein (g)} = \text{Weight (kg)} \times \text{Activity Factor}
$Sedentary: $0.8\text{g} / \text{kg}$Active/Athletic: $1.2\text{g} \text{ to } 2.0\text{g} / \text{kg}$
Required Inputs: Weight (from user info), activity level (dropdown/integer).Output: Recommended daily protein in grams.
