package com.riwi.events.services.services_abstract;

import java.util.List;

import org.springframework.data.domain.Page;

import com.riwi.events.entities.Event;

public interface IEventService {

    public Event save(Event event);

    public List<Event> getAll();

    public Event findById(String id);

    public void delete (String id);

    public Event update(String id, Event event);

    public List<Event> search(String name);

    public Page<Event> findpage(int page, int size);

}
