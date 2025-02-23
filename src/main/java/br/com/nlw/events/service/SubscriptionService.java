package br.com.nlw.events.service;

import br.com.nlw.events.exception.EventNotFoundException;
import br.com.nlw.events.model.Event;
import br.com.nlw.events.model.Subscription;
import br.com.nlw.events.model.User;
import br.com.nlw.events.repository.EventRepository;
import br.com.nlw.events.repository.SubscriptionRepository;
import br.com.nlw.events.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription createNewSubscription(String eventName, User user) {
        // Recuperar o evento pelo nome

        Event event = eventRepository.findByPrettyName(eventName);
        if (event == null) {
            throw new EventNotFoundException("Evento " + eventName + " não existe!");
        }
        User userRec = userRepository.findByEmail(user.getEmail());
        if (userRec == null) {
            userRec = userRepository.save(user);
        }

        Subscription subs = new Subscription();
        subs.setEvent(event);
        subs.setSubscriber(userRec);

        Subscription result = subscriptionRepository.save(subs);
        return result;

    }
}
