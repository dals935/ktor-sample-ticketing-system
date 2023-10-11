package com.example.controllers

import com.example.config.ticketConfig
import com.example.models.*
import com.example.queries.*
import java.sql.ResultSet

class ticketController {

    fun getTickData(): MutableList<tickarray>
    {
        val tickdata = mutableListOf<tickarray>()

        val query = ticketConfig().connect().prepareStatement(getCompDataQuery)

        val result = query.executeQuery()
        while (result.next())
        {
            val complaint_id = result.getInt("complaint_id")
            val employee_id = result.getInt("employee_id")
            val subject = result.getString("subject")
            val description = result.getString("description")
            val status  = result.getString("status")
            val cre_date = result.getString("creation_date")
            val res_date = result.getString("resolution_date")
            tickdata.add(tickarray(complaint_id, employee_id, subject, description, status, cre_date, res_date))
        }
        return tickdata
    }

    fun getEmpData(): MutableList<employee_data>{

        val empdata = mutableListOf<employee_data>()

        val query = ticketConfig().connect().prepareStatement(getEmpDataQuery)

        val result = query.executeQuery()
        while (result.next())
        {
            val employee_id = result.getInt("employee_id")
            val emp_name = result.getString("employee_name")
            val email = result.getString("email")
            val department  = result.getString("department")
            empdata.add(employee_data(employee_id,emp_name, email, department))
        }
        return empdata
    }

    fun getCommsData(): MutableList<comms_data>{

        val commsdata = mutableListOf<comms_data>()

        val query = ticketConfig().connect().prepareStatement(getCommsDataQuery)

        val result = query.executeQuery()
        while (result.next())
        {
            val comms_id = result.getInt("comment_id")
            val comp_id = result.getInt("complaint_id")
            val comms_txt = result.getString("comms_text")
            val comms_date  = result.getString("comms_date")
            commsdata.add(comms_data(comms_id, comp_id, comms_txt, comms_date))
        }
        return commsdata

    }
    fun addTicketdata(subject: String, description: String, status: String, cre_date: String, res_date: String) : Int {

        val query = ticketConfig().connect().prepareStatement(addTicketQuery)
        query.setString(1, subject)
        query.setString(2, description)
        query.setString(3, status)
        query.setString(4, cre_date)
        query.setString(5, res_date)

        return query.executeUpdate()
    }

    fun addEmpData(employee_name: String, email: String, department: String) : Int {

        val query = ticketConfig().connect().prepareStatement(addEmpQuery)
        query.setString(1, employee_name)
        query.setString(2, email)
        query.setString(3, department)

        return query.executeUpdate()
    }

    fun addCommData(comms_text: String, comms_date: String) : Int {

        val query = ticketConfig().connect().prepareStatement(addCommsQuery)

        query.setString(1, comms_text)
        query.setString(2, comms_date)

        return query.executeUpdate()
    }

    fun updateTickData(subject: String, description: String, status: String, cre_date: String, res_date: String, comp_id: String) : Int {

        val query = ticketConfig().connect().prepareStatement(updateTickQuery)
        query.setString(1, subject)
        query.setString(2, description)
        query.setString(3, status)
        query.setString(4, cre_date)
        query.setString(5, res_date)
        query.setString(6, comp_id)
        return query.executeUpdate()
    }

    fun deleteTickData(comp_id: String): Int{

        val query = ticketConfig().connect().prepareStatement(deleteTickQuery)
        query.setString(1, comp_id)
        return query.executeUpdate()

    }

}