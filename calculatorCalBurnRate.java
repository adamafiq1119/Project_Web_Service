/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package org.calorieburnrate;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;

/**
 *
 * @author Afiq
 */
@WebService(serviceName = "calculatorCalBurnRate")
public class calculatorCalBurnRate {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "calculateBurnRate")
    public String calculateBurnRate(@WebParam(name = "gender") String gender, 
            @WebParam(name = "weight") double weight, 
            @WebParam(name = "height") double height, 
            @WebParam(name = "age") int age, 
            @WebParam(name = "activityFactor") double activityFactor) throws SOAPException {
        
        if (weight <= 0 || height <= 0 || age <= 0) {
            SOAPFault fault = SOAPFactory.newInstance().createFault();
            fault.setFaultString("Invalid Input: Weight, height, and age must be greater than zero.");
            throw new SOAPFaultException(fault);
        }
        
        if (activityFactor < 1.0 || activityFactor > 2.5) {
            SOAPFault fault = SOAPFactory.newInstance().createFault();
            fault.setFaultString("Invalid Input: Activity factor must be between 1.0 and 2.5 (e.g., 1.2 for sedentary).");
            throw new SOAPFaultException(fault);
        }
        
        if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
            SOAPFault fault = SOAPFactory.newInstance().createFault();
            fault.setFaultString("Invalid Input: Gender must be 'male' or 'female'.");
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
        
        if (tdee < 1200) {
            return "Warning: Your TDEE is only " + String.format("%.0f", tdee) + 
                   " kcal/day. This is very low; consult a nutritionist.\n" +
                   "Maintenance calories (TDEE): " + String.format("%.0f", tdee) + " kcal.";
        }

            return "Your Total Daily Energy Expenditure (TDEE) is: " + String.format("%.0f", tdee) + 
                   " kcal/day. This is the total energy (calories) you burn daily.";
        }
    }

