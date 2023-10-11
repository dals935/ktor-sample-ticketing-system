package com.example.routes

import com.example.controllers.ticketController
import com.example.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.Identity.encode
import kotlinx.serialization.json.Json

fun Route.ticketRouting() {
    route("/ticket_data")
    {
        get {
            try {
                val ticketVal = ticketController().getTickData()
                call.respond(ticketVal)
            } catch (e: Exception) {
                return@get call.respond(resModel(500, "Server error: $e"))
            }

        }
    }
    route("/employee_data")
    {
        get {
            try {
                val empVal = ticketController().getEmpData()
                call.respond(empVal)
            } catch (e: Exception) {
                return@get call.respond(resModel(500, "Server error: $e"))
            }
        }
    }

    route("/comms_data")
    {
        get {
            try {
                val commsVal = ticketController().getCommsData()
                call.respond(commsVal)
            } catch (e: Exception) {
                return@get call.respond(resModel(500, "Server error: $e"))
            }
        }
    }

    route("/add_tic")
    {
        post {
            try {
                val ticket = call.receive<emp_complaints>()

                val (subject, description, status, cre_date, res_date) = ticket

                if ( subject == "" )
                    return@post call.respond(resModel(417, "Input Subject"))
                if ( description == "" )
                    return@post call.respond(resModel(417, "Input Description"))
                if ( status == "" )
                    return@post call.respond(resModel(417, "Input Status"))
                if ( cre_date == "" )
                    return@post call.respond(resModel(417, "Input Creation Date"))

                ticketController().addTicketdata(subject, description, status, cre_date, res_date)
                return@post call.respond(resModel(201, "Ticket Created"))

            } catch (e: Exception) {
                return@post call.respond(resModel(500, "Server error: $e"))
            }
        }
    }
    route("/add_emp")
    {
        post {
            try {
                val emp = call.receive<employees>()

                val (employee_name, email, department) = emp

                ticketController().addEmpData(employee_name, email, department)
                return@post call.respond(resModel(201, "Employee Added Successfully"))

            } catch (e: Exception) {
                return@post call.respond(resModel(500, "Server error: $e"))
            }
        }
    }
    route("/add_comms")
    {
        post {
            try {
                val comms = call.receive<comms>()

                val (comms_text, comms_date) = comms

                ticketController().addCommData(comms_text, comms_date)
                return@post call.respond(resModel(201, "Comment Added Successfully"))

            } catch (e: Exception) {
                return@post call.respond(resModel(500, "Server error: $e"))
            }
        }
    }

    route("/update_tic/{tickid?}"){
        put{
            try {
                val tickdata = call.receive<tickarray>()
                val (complaint_id, emp_id, subject, description, status, cre_date, res_date) = tickdata
                val ticStorage = ticketController().getTickData()
                val comp_id = (call.parameters["tickid"] ?: return@put call.respond(HttpStatusCode.BadRequest))
                if (ticStorage.removeIf { it.complaint_id.toString() == comp_id }) {
                    ticketController().updateTickData(subject, description, status, cre_date, res_date, comp_id)
                    return@put call.respond(resModel(201, "$comp_id was updated correctly"))
                }
            }
            catch (e: Exception)
            {
                return@put call.respond(resModel(500, "Server error: $e"))
            }
        }
    }

    route("/delete_tic/{tickid?}"){
        delete {
            val ticStorage = ticketController().getTickData()
            val comp_id = (call.parameters["tickid"] ?: return@delete call.respond(HttpStatusCode.BadRequest))
            ticStorage.find { it.complaint_id.toString() == comp_id } ?: return@delete call.respond(resModel(404, "Not Found"))
            ticketController().deleteTickData(comp_id)
            return@delete call.respond(resModel(200, "Ticked ID #$comp_id was Deleted"))

        }
    }

    route("/get_tic/{tickid}"){
        get {
            val ticStorage = ticketController().getTickData()
            val comp_id = (call.parameters["tickid"] ?: return@get call.respond(HttpStatusCode.BadRequest))
            val ticket = ticStorage.find { it.complaint_id.toString() == comp_id } ?: return@get call.respond(resModel(404, "Not Found"))
            call.respond(ticket)
            return@get call.respond(resModel(200, "Ticked ID #$comp_id is Found"))
        }
    }
}