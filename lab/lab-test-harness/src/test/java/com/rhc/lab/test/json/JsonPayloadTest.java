package com.rhc.lab.test.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.rhc.lab.domain.BookingRequest;
import com.rhc.lab.domain.Performance;
import com.rhc.lab.domain.PerformanceType;
import com.rhc.lab.domain.Venue;

/**
 * 
 * This JUnit test class tests Jackson mapping of JSON to and from the Java
 * domain model
 * 
 */
public class JsonPayloadTest {

	private ObjectMapper om;

	@Before
	public void init() {
		om = new ObjectMapper();
		om.registerModule(new JodaModule());
	}

	@Test
	public void shouldMarshalAndUnmarshalJson() throws IOException {

		BookingRequest request = populateBookingRequest();

		String requestString = om.writer().writeValueAsString(request);

		assertNotNull(requestString);
		System.out.println(om.writerWithDefaultPrettyPrinter()
				.writeValueAsString(request));

		BookingRequest r = om.readValue(requestString, BookingRequest.class);
		assertNotNull(r.getOpen());
		assertNotNull(r.getClose());
		assertNotNull(r.getPerformance().getName(), r.getPerformance()
				.getType());
		if (r.getPerformance().getName().equals("The Velvet Underground")) {
			assertEquals(PerformanceType.BAND, r.getPerformance().getType());
		} else if (r.getPerformance().getName()
				.equals("Brooklyn Symphony Orchestra")) {
			assertEquals(PerformanceType.ORCHESTRA, r.getPerformance()
					.getType());
		}
	}

	public BookingRequest populateBookingRequest() {
		BookingRequest request = new BookingRequest();
		Performance performance = new Performance();
		Venue venue = new Venue();

		venue.setName("Brooklyn Bowl");
		venue.setCity("Brooklyn");
		venue.setCapacity(8000);
		venue.setAccomodations(new ArrayList<PerformanceType>(Arrays.asList(
				PerformanceType.BAND, PerformanceType.ORCHESTRA,
				PerformanceType.RALLY)));
		performance.setName("The Velvet Underground");
		performance.setType(PerformanceType.BAND);

		request.setVenue(venue);
		DateTime d1 = DateTime.now();
		DateTime d2 = DateTime.now().plusHours(4);
		request.setOpen(d1);
		request.setClose(d2);
		request.setPerformance(performance);

		return request;
	}

}
