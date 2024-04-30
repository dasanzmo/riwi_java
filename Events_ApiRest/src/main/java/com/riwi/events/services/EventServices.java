package com.riwi.events.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.events.entities.Event;
import com.riwi.events.repository.EventsRepository;
import com.riwi.events.services.services_abstract.IEventService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventServices implements IEventService {

    @Autowired
    private final EventsRepository eventRepository;



    @Override
    public Event save(Event event) {
        event.setCapacidad(Math.abs(event.getCapacidad()));
       return this.eventRepository.save(event);
    }

    @Override
    public List<Event> getAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public Event findById(String id) {
       return this.eventRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(String id) {
       Event eventFind = this.eventRepository.findById(id).orElseThrow();
       this.eventRepository.delete(eventFind);
       
    }

    @Override
    public Event update(String id, Event event) {
        event.setId(id);
        return this.eventRepository.save(event);
        
    }

    @Override
    public List<Event> search(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    public Page<Event> findpage(int page, int size){
        if(page<0){
            page = 1;
        }

        PageRequest pageable =  PageRequest.of(page, size);
        return this.eventRepository.findAll(pageable);
     }
    
    

}
