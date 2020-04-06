# QueuingApp
QueuingApp receives REST calls of the medical offices und pushes notifications to Patients.

## usage

The app is built on spring boot and runs on port 8080.
When accessing the app with the browser you see the REST interface. It has further documentation on how to use what and why.
You can use the start and stop scripts to start/stop the application.

## developement

The app is built with spring boot.
It uses Spring Fox for REST documentation.
It uses an in memory h2 database, which can be exchanged, because the DB access is done via JPA.

## next steps

### notifications

The queue is currently not sending notifications. But the architecture is built in a way that would support notifications via SMS, App or portable beeper (for the elderly that don't have a mobile device)

### multiple queues

To account for medical offices with multiple doctors, we want to create multiple queues for the same office.
This also includes mulitple queues where a patient is first in the waiting for doctor queue, then the waiting for CT scan queue, and so on.
