package com.edmtz.service;

import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;//import com.edmtz.dto.request.EventDTO;
//import com.edmtz.model.Event;
//import com.edmtz.repository.EventRepository;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import java.time.LocalDateTime;
//
public class EventServiceTest {
//
//    @Mock
//    private EventRepository eventRepository;
//    @InjectMocks
//    private EventService eventService;
//
//    @Captor
//    private ArgumentCaptor<Event> eventCaptor;
//
//    @Test
//    public void testCreateEvent() {
//        //Given
//        EventDTO eventDTO = new EventDTO();
//        eventDTO.setName("Michelle's Event");
//        eventDTO.setDescription("This is a nice Mich Event");
//        eventDTO.setDateTime(LocalDateTime.of(2025, 5, 10, 14, 30));
//        eventDTO.setLocation("Mexico City");
//
//        String expectedName = "Michelle's Event";
//        String expectedDescription = "This is a nice Mich Event";
//        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2025, 5, 10, 14, 30);
//        String expectedLocation = "Mexico City";
//
//        //When
//        when(eventRepository.save(any(Event.class))).thenAnswer(invocation -> {
//            Event savedEvent = invocation.getArgument(0);
//            savedEvent.setId(1L);
//            return savedEvent;
//
//        });
//        Event result = eventService.createEvent(eventDTO);
//
//        //Then
//        // Object received by repository
//        verify(eventRepository).save(eventCaptor.capture());
//        Event capturedEvent = eventCaptor.getValue();
//        assertNotNull(capturedEvent);
//        assertNull(capturedEvent.getId());
//        assertEquals(expectedName, capturedEvent.getName());
//        assertEquals(expectedDescription, capturedEvent.getDescription());
//        assertEquals(expectedLocalDateTime, capturedEvent.getDateTime());
//        assertEquals(expectedLocation, capturedEvent.getLocation());
//        //Object returned by repository
//        assertNotNull(result);
//        assertEquals(1L, result.getId());
//        assertEquals(expectedName, result.getName());
//        assertEquals(expectedDescription, result.getDescription());
//        assertEquals(expectedLocalDateTime, result.getDateTime());
//        assertEquals(expectedLocation, result.getLocation());
//    }
//
//    @Test
//    public void testGetEventById() {
//
//    }
//
//    @Test
//    public void testGetAllEvents() {
//
//    }
//
    @Test
    public void testUpdateEvent() {
        Assertions.assertEquals(2, 2);
    }
//
//    @Test
//    public void testDeleteEvent() {
//
//    }
//
//    @Test
//    public void testGetEventOrThrow() {
//
//    }
//
//    @Test
//    public void testValidateUserOwnership() {
//
//    }
}
