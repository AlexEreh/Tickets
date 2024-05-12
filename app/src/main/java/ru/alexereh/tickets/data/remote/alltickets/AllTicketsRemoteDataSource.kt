package ru.alexereh.tickets.data.remote.alltickets

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.alexereh.tickets.data.remote.alltickets.api.AllTicketsRemoteApi
import ru.alexereh.tickets.domain.model.DestinationArrivalModel
import ru.alexereh.tickets.domain.model.PriceModel
import ru.alexereh.tickets.domain.model.TicketModel
import ru.alexereh.tickets.domain.model.TicketsModel
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllTicketsRemoteDataSource @Inject constructor(
    private val api: AllTicketsRemoteApi
) {
    fun getTickets(): Flow<TicketsModel> {
        return flow {
            val result = api.getTickets()
            val mappedResult = TicketsModel(
                tickets = result.tickets.map {
                    TicketModel(
                        id = it.id,
                        badge = it.badge ?: "",
                        price = PriceModel(it.price.value),
                        providerName = it.providerName,
                        company = it.company,
                        departure = DestinationArrivalModel(
                            it.departure.town,
                            LocalDateTime.parse(it.departure.date),
                            it.departure.airport
                        ),
                        arrival = DestinationArrivalModel(
                            it.arrival.town,
                            LocalDateTime.parse(it.arrival.date),
                            it.arrival.airport
                        ),
                        hasTransfer = it.hasTransfer,
                        hasVisaTransfer = it.hasVisaTransfer,
                        isReturnable = it.isReturnable,
                        isExchangeable = it.isExchangeable
                    )
                }
            )
            emit(mappedResult)
        }
    }
}