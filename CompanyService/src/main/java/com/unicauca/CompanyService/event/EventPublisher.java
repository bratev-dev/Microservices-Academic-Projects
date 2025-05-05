package com.unicauca.CompanyService.event;
import com.unicauca.CompanyService.event.ProjectPublishedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishProjectPublishedEvent(ProjectPublishedEvent event) {
        rabbitTemplate.convertAndSend("project.exchange", "project.published", event);
    }
}
