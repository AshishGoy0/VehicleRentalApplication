package com.vehicle.rental.service;

import com.vehicle.rental.apimodels.request.BookRequest;

public interface BookingService {

    Double bookVehicle(BookRequest bookRequest);
}
