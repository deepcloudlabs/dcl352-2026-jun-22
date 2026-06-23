package com.example.cm.infrastructure;

import com.example.hexagonal.Port;
import com.example.hexagonal.PortType;

@Port(PortType.DRIVEN_PORT)
public interface EventPublisher<E> {
	void publish(E event);
}
