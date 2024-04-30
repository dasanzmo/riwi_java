package com.riwi.events.controllers;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.events.entities.Event;
import com.riwi.events.services.services_abstract.IEventService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/events")
@AllArgsConstructor

public class EventController {
    private final IEventService eventServices;

    @GetMapping
    public ResponseEntity<Page<Event>> getAll(
    @RequestParam(defaultValue = "1")int page,
    @RequestParam(defaultValue = "2")int size){
        
        Page<Event> list =  this.eventServices.findpage(page-1, size);

        return ResponseEntity.ok(list);
    }


    @PostMapping
    public ResponseEntity <Event> insert(@RequestBody Event objEvent){
        
        return ResponseEntity.ok(this.eventServices.save(objEvent));
    }

    @GetMapping(path= "/{id}")
    public ResponseEntity <Event> findById(@PathVariable String id){
            return ResponseEntity.ok(this.eventServices.findById(id));
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity <Event> update(
        @PathVariable String id, 
        @RequestBody Event event){
        return ResponseEntity.ok(this.eventServices.update(id, event));

    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity <Void> delete(@PathVariable String id){
        this.eventServices.delete(id);
        return ResponseEntity.noContent().build();
    }


    
}
