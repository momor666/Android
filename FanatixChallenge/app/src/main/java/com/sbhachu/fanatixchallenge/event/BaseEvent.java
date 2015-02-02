package com.sbhachu.fanatixchallenge.event;

import com.google.common.base.Optional;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * BaseEvent Class
 * Uses Optional to carry the event data, this prevents NullPointerException errors if data is
 * not supplied, it's an "optional" requirement
 */
public abstract class BaseEvent<T> {

    protected final Optional<T> data;

    protected BaseEvent(T data) {
        this.data = Optional.of(data);
    }

    protected BaseEvent() {
        this.data = Optional.absent();
    }

    public Optional<T> getData() {
        return data;
    }

    @Override
    public boolean equals(Object obj){
        return EqualsBuilder.reflectionEquals(this, obj);
    }
    @Override
    public int hashCode(){
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
