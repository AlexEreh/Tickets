package ru.alexereh.tickets.data.remote.tickets

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.alexereh.tickets.data.remote.tickets.api.OffersRemoteApi
import ru.alexereh.tickets.domain.model.OfferModel
import ru.alexereh.tickets.domain.model.OffersModel
import ru.alexereh.tickets.domain.model.PriceModel
import javax.inject.Inject

class OffersRemoteDataSource @Inject constructor(
    private val offersRemoteApi: OffersRemoteApi
) {
    fun getTickets(): Flow<OffersModel> {
        return flow {
            val result = offersRemoteApi.getOffers()
            val mappedResult = OffersModel(
                offers = result.offers.map {
                    OfferModel(
                        id = it.id,
                        title = it.title,
                        town = it.town,
                        price = PriceModel(it.price.value)
                    )
                }
            )
            emit(mappedResult)
        }
    }
}