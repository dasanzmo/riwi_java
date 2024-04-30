package com.riwi.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.events.entities.Event;

@Repository
public interface EventsRepository extends JpaRepository<Event, String> {

}
