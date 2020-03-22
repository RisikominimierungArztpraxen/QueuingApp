package de.apppointment.queuingserver.service.queue;

@FunctionalInterface
public interface IQueueChange {

    /**
     * Called whenever something in the queue changes
     */
    public void queueChanged();
}
