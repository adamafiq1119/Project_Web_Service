/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package org.caloriesdeficeit;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.soap.SOAPFaultException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
/**
 *
 * @author adama
 */
@WebService(serviceName = "CaloriesDeficeit")
public class CaloriesDeficeit {

@WebMethod(operationName = "calculateCalorieDeficit")
    public String calculateCalorieDeficit(
            @WebParam(name = "gender") String gender,
            @WebParam(name = "weight") double weight,
            @WebParam(name = "height") double height,
            @WebParam(name = "age") int age,
            @WebParam(name = "activityLevel") double activityFactor, // e.g., 1.2, 1.55
            @WebParam(name = "targetDeficit") int deficit) throws Exception {

        // Validation & SOAP Fault Handling
        if (weight <= 0 || height <= 0 || age <= 0) {
            SOAPFault fault = SOAPFactory.newInstance().createFault();
            fault.setFaultString("Invalid Input: Measurements must be greater than zero.");
            throw new SOAPFaultException(fault);
        }

        // BMR Calculation (Mifflin-St Jeor)
        double bmr;
        if (gender.equalsIgnoreCase("male")) {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
        } else {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
        }

        // TDEE (Total Daily Energy Expenditure)
        double tdee = bmr * activityFactor;
        double recommendedIntake = tdee - deficit;

        // Safety check for SOAP Fault
        if (recommendedIntake < 1200) {
            return "Warning: Your deficit goal results in " + String.format("%.0f", recommendedIntake) + 
                   " kcal. Consuming less than 1200 kcal is not recommended without medical supervision.";
        }

        return "Your maintenance calories (TDEE) are: " + String.format("%.0f", tdee) + 
               " kcal. To reach your goal, consume: " + String.format("%.0f", recommendedIntake) + " kcal daily.";
    }
}
