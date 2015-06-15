package com.rhc.lab.domain;

import java.io.Serializable;

/**
 * 
 * This class represents the domain model for a booking response that will go
 * through our Concert Booking application for artists and venue matching. This
 * payload will be returned to the UI after rules have fired to determine
 * whether or not the venue can be booked as per the requested time(s) and
 * performance type(s)
 * 
 */
public class BookingResponse implements Serializable {
	/**
   * 
   */
	private static final long serialVersionUID = 1074544082673724792L;

	private BookingRequest bookingRequest;
	private BookingResponse bookingResponse;

	public BookingRequest getBookingRequest() {
		return bookingRequest;
	}

	public void setBookingRequest(BookingRequest bookingRequest) {
		this.bookingRequest = bookingRequest;
	}

	public BookingResponse getBookingResponse() {
		return bookingResponse;
	}

	public void setBookingResponse(BookingResponse bookingResponse) {
		this.bookingResponse = bookingResponse;
	}

}
