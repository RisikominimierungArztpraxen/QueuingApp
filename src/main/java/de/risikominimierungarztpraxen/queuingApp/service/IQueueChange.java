package de.risikominimierungarztpraxen.queuingApp.service;

@FunctionalInterface
public interface IQueueChange {

    /**
     * Called whenever something in the queue changes
     */
    public void queueChanged();
}
