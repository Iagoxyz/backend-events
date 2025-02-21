package br.com.nlw.events.repository;

import br.com.nlw.events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
    public Event findByPrettyName(String prettyName);
}
