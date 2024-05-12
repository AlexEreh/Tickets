package ru.alexereh.tickets.data.remote.selectedsearch

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.alexereh.tickets.data.remote.selectedsearch.api.TicketsOffersRemoteApi
import ru.alexereh.tickets.domain.model.PriceModel
import ru.alexereh.tickets.domain.model.TicketsOfferModel
import ru.alexereh.tickets.domain.model.TicketsOffersModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TicketsOffersRemoteDataSource @Inject constructor(
    private val api: TicketsOffersRemoteApi
) {
    fun getOffersTickets(): Flow<TicketsOffersModel> {
        return flow {
            val result = api.getOffersTickets()
            val mappedResult = TicketsOffersModel(result.ticketsOffers.map {
                TicketsOfferModel(
                    id = it.id,
                    title = it.title,
                    timeRange = it.timeRange,
                    price = PriceModel(it.price.value)
                )
            })
            emit(mappedResult)
        }
    }
}