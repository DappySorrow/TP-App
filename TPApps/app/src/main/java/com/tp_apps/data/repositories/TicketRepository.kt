package com.tp_apps.data.repositories

import com.tp_apps.data.datasources.TicketDataSource
import com.tp_apps.domain.models.Ticket
import com.tp_apps.helpers.Constants
import com.tp_apps.helpers.LoadingResource
import com.tp_apps.helpers.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TicketRepository {

    private val ticketDataSource = TicketDataSource()

    suspend fun retrieveAll(): LoadingResource<List<Ticket>> {
            while(true) {
                try {
                    LoadingResource.Loading()
                    //delay(1500)
                    LoadingResource.Success(ticketDataSource.retrieveAll())
                } catch (ex: Exception) {
                    LoadingResource.Error(ex, ex.message)
                }
                delay(Constants.REFRESH_TICKET_DELAY)
        }
    }

    suspend fun retrieveOne(href: String) : Resource<Ticket> {
        return try {
            Resource.Success(ticketDataSource.retrieveOne(href))
        }catch (ex:Exception){
            Resource.Error(ex,ex.message)
        }
    }


}