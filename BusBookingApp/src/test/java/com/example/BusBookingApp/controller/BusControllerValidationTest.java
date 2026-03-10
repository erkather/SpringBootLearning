package com.example.BusBookingApp.controller;

import com.example.BusBookingApp.service.BusService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BusController.class)
class BusControllerValidationTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    BusService busService;

    @Test
    void addNewBus_withInvalidRequest_returnsBadRequest() throws Exception
    {
        String busJson = """
                {
                    "bus_number": "",
                    "bus_operator": "Test Operator",
                    "source": "City A",
                    "destination": "City B",
                    "departureTime": "2024-12-01T10:00:00",
                    "arrivalTime": "2024-12-01T14:00:00",
                    "seats": [
                        {"seatNumber": "1A", "isAvailable": true}
                    ]
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders.post("/api/buses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(busJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.busNumber").value("Bus number is required"))
                .andExpect(jsonPath("$.type").value("Bus type is required"))
                .andExpect(jsonPath("$.manufacturer").value("Manufacturer is required"));
    }
}
