# Java RMI TV Show Manager

A Java client-server application for managing TV shows using Java RMI, object-oriented programming and persistent file storage.

## Features

- View stored TV shows
- Add new TV shows
- Edit existing TV show information
- Delete TV shows
- Categorise shows by genre
- Client-server communication using Java RMI
- Graphical user interface using Java Swing
- Persistent storage using Java serialization
- Automatically loads previously stored TV shows

## Technologies Used

- Java
- Java RMI
- Java Swing
- Java Serialization
- Java Collections

## Client-Server Architecture

The application uses Java RMI to separate the client interface from the server-side functionality.

- **Client** – provides the Swing interface and sends requests to the server
- **Server** – hosts the remote service through an RMI registry
- **Remote Interface** – defines the operations available to the client
- **ArrayList Service** – manages TV show records and persistent storage

The client connects to the RMI registry and can remotely add, retrieve, update and delete TV shows.

## Object-Oriented Design

The application uses an abstract `TVShow` class to represent common TV show information.

Different show types extend this class, including:

- `ThrillerShow`
- `FantasyShow`

A Factory design pattern is used to create the appropriate TV show type based on its genre.

## Key Concepts Demonstrated

This project demonstrates:

- Client-server architecture
- Remote Method Invocation (RMI)
- Object-oriented programming
- Inheritance and abstract classes
- Factory design pattern
- Java Swing GUI development
- CRUD operations
- Java collections
- Object serialization and persistent storage
