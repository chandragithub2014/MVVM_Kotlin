package com.rxjava.practice

class DataSource {
    companion object{
        fun  createEmployeeList() : MutableList<Employee> {
            var list = mutableListOf<Employee>()
            list.add(Employee("John","He is Android Developer",false))
            list.add(Employee("Sham","He is Android Developer",true))
            list.add(Employee("Pollock","He is iOS Developer",false))
            list.add(Employee("Ram","He is iOS Developer",true))
            list.add(Employee("Krish","He is Hybrid Developer",true))
            return  list
        }

        fun createMaleUserList():MutableList<User>{
            var list = mutableListOf<User>()
            list.add(User("Chandra","Male"))
            list.add(User("Sai","Male"))
            list.add(User("Mohan","Male"))
            return  list

        }


        fun createFeMaleUserList():MutableList<User>{
            var list = mutableListOf<User>()
            list.add(User("Rita","Female"))
            list.add(User("Geetha","Female"))
            list.add(User("Sitha","Female"))
            return  list

        }
    }
}