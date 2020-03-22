package de.risikominimierungarztpraxen.queuingApp.service.queue;

@FunctionalInterface
public interface IQueueChange {

    /**
     * Called whenever something in the queue changes
     */
    public void queueChanged();
}
