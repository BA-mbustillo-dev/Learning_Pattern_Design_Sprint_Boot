/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.patron.patron_iterador;

import com.patron.impl.Employee;
import com.patron.impl.IIterator;

/**
 *
 * @author mbustillo
 */
public class Patron_Iterador {

    public static void main(String[] args) {
        Employee employee = new Employee("juan", "CEO", 
                new Employee("Pedro", "Presidente", 
                        new Employee("Liliana", "VP", 
                                new Employee("Oscar", "Gerente", 
                                        new Employee("Mario", "Developer"),
                                        new Employee("Rodolfo", "Developer")),
                                new Employee("Sofia", "Gerente",
                                        new Employee("adriana", "Sr Developer"),
                                        new Employee("Rebecca", "Developer")
                                )
                        )
                )
        );
        IIterator<Employee> empIterator = employee.createIterator();
        while(empIterator.hasNext()){
            Employee emp = empIterator.next();
            System.out.println("emp>"+emp.toString());
        }
    }
}
