package com.example.BusBookingApp.controller;

import com.example.BusBookingApp.dto.*;
import com.example.BusBookingApp.service.BusService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BusController.class)
class BusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BusService busService;

    @Test
    void shouldGetAllBuses() throws Exception
    {
        BusResponse bus1 = new BusResponse(1L, "BUS123", "Sleeper", "Volvo", "ABC1234", 0);
        BusResponse bus2 = new BusResponse(2L, "BUS456", "Seater", "Mercedes", "XYZ5678", 0);
        List<BusResponse> busList = List.of(bus1, bus2);
        PageResponse<BusResponse> busPageResponse = new PageResponse<>(busList, 0, 10, busList.size(), 1);
        Mockito.when(busService.getAllBuses(Mockito.anyInt(), Mockito.anyInt())).thenReturn(busPageResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/buses")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].bus_number").value("BUS123"))
                .andExpect(jsonPath("$.content[1].bus_number").value("BUS456"));

    }

    @Test
    void shouldGetBusByNumber() throws Exception
    {
        BusResponseIncludingSeats bus = new BusResponseIncludingSeats(1L, "BUS123", "Sleeper", "Volvo", "ABC1234", new ArrayList<>());
        Mockito.when(busService.getBusByNumber("BUS123")).thenReturn(bus);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/buses/BUS123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bus_number").value("BUS123"))
                .andExpect(jsonPath("$.type").value("Sleeper"))
                .andExpect(jsonPath("$.manufacturer").value("Volvo"))
                .andExpect(jsonPath("$.bus_operator").value("ABC1234"));
    }

    @Test
    void shouldRegisterNewBus() throws Exception
    {
        CreateBusRequest createBusRequest = new CreateBusRequest("BUS123", "Sleeper", "Volvo", "ABC1234", new ArrayList<>());
        BusResponse bus = new BusResponse(1L, "BUS123", "Sleeper", "Volvo", "ABC1234", 0);
        Mockito.when(busService.addNewBus(createBusRequest)).thenReturn(bus);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/buses")
                        .contentType("application/json")
                        .content("{\"bus_number\":\"BUS123\",\"type\":\"Sleeper\",\"manufacturer\":\"Volvo\",\"bus_operator\":\"ABC1234\",\"seats\":[]}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bus_number").value("BUS123"))
                .andExpect(jsonPath("$.type").value("Sleeper"))
                .andExpect(jsonPath("$.manufacturer").value("Volvo"))
                .andExpect(jsonPath("$.bus_operator").value("ABC1234"));
    }

    @Test
    void shouldUpdateBusDetails() throws Exception
    {
        UpdateBusRequest updateBusRequest = new UpdateBusRequest("Sleeper", "Volvo");
        BusResponse bus = new BusResponse(1L, "BUS123", "Sleeper", "Volvo", "ABC1234", 0);
        Mockito.when(busService.updateBusDetails("BUS123", updateBusRequest)).thenReturn(bus);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/buses/BUS123")
                        .contentType("application/json")
                        .content("{\"type\":\"Sleeper\",\"manufacturer\":\"Volvo\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bus_number").value("BUS123"))
                .andExpect(jsonPath("$.type").value("Sleeper"))
                .andExpect(jsonPath("$.manufacturer").value("Volvo"))
                .andExpect(jsonPath("$.bus_operator").value("ABC1234"));
    }

    @Test
    void shouldDeleteBus() throws Exception
    {
        Mockito.doNothing().when(busService).deleteBus("BUS123");
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/buses/BUS123"))
                .andExpect(status().isOk());
    }
}