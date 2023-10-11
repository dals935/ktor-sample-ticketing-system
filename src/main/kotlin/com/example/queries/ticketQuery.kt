package com.example.queries

const val getCompDataQuery = "SELECT * FROM emp_complaints"

const val getEmpDataQuery = "SELECT * FROM employees"

const val getCommsDataQuery = "SELECT * FROM comms"

const val addTicketQuery = "INSERT INTO emp_complaints(subject, description, status, creation_date, resolution_date) VALUES(?, ?, ?, ?, ?)"

const val addEmpQuery = "INSERT INTO employees(employee_name, email, department) VALUES(?, ?, ?)"

const val addCommsQuery = "INSERT INTO comms(comms_text, comms_date) VALUES(?, ?)"

const val updateTickQuery = "UPDATE emp_complaints SET subject=?, description=?, status=?, creation_date=?, resolution_date=? WHERE complaint_id=cast(? as int)"

const val deleteTickQuery = "DELETE FROM emp_complaints WHERE complaint_id=cast(? as int)"