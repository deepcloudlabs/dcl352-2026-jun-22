package com.example.cm.domain;

public abstract class DomainEntity<T> {
	protected T id;

	public DomainEntity(T id) {
		this.id = id;
	}

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
	
}
