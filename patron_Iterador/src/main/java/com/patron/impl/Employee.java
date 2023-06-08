
package com.patron.impl;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

/**
 *
 * @author mbustillo
 */
public class Employee implements IContainer<Employee>{
    
    private String name;
    private String role;
    private List<Employee> subordinates;

    public Employee(String name, String role, Employee... subordinates) { //aqui se encontra un arreglo infinito no se especifica el tamaño
        this.name = name;
        this.role = role;
        this.subordinates = Arrays.asList(subordinates);
    }
    
    @Override
    public IIterator<Employee> createIterator(){
        return new TreeEmployeeIterador();
    }
    
    private class TreeEmployeeIterador implements IIterator<Employee>{
        private LinkedList<Employee> list = new LinkedList<Employee>();
        private int index = 0;
        
        public TreeEmployeeIterador(){
            addRecursive(Employee.this);
        }
        
        public void addRecursive(Employee employee){
            list.add(employee);
            if(employee.getSubordinates() != null){
                for(Employee subordinate : employee.getSubordinates()){
                    addRecursive(subordinate);
                }
            }
        }
        
        @Override
        public boolean hasNext(){
            if (list.isEmpty()){
                return false;
            }
            return index < list.size();
        }
        
        @Override
        public Employee next(){
            if(!hasNext()){
                throw new RuntimeException("No hay mas elementos");
            }
            return list.get(index++);
        }
    }
    
    public void setSubordinates(List<Employee> subordinates){
        this.subordinates = subordinates;
    }
    
    public void addSubordinate(Employee subordinate){
        if(subordinate == null){
            subordinates = new ArrayList<>();
        }
        subordinates.add(subordinate);
    }


    @Override
    public String toString(){
        return "Employee{" + "name="+name+", role="+role+'}';
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }
}
