# Exercise #08 - Modularity

#### What you already know from the lecture:

1. What is a module? What is a component?
2. What is modularity and what is it good for?
3. What is an interface?
4. Who is a user of an interface? What's the difference between an interface user and the end user?
5. What is an API?

---

## 0. Homework questions

**Goal:** Reflect on homework, prepare for upcoming homework.

0. Is there anything your tutor wants to tell you about previous homework?
1. Do you have any HW6-related questions?
2. Do you have any questions about HW77 Have you already started? Do you have a team / need a team?
3. Remember: you should avoid code smells in your homework! 

## 1. Functional core, imperative shell

**Goal:** Recognize and understand functional core, imperative shell.

1. Take a look into the `licenser` in HW5. Find the interface which abstracts over file operations.
2. Find the implementations of the interface. How do they differ? Where are they used?
3. What is the functional core? What is the imperative shell?
4. Which effect is this abstracting over?

## 2. API

**Goal:** Can describe and use REST APIs.

1. What APIs do you know and use?
2. Do you know how they are implemented? If not, how come you can still use them?
3. Go to https://http.cat/ and try 200, 404, 403, 418
4. Describe the input, output and _how they relate_!
5. Go to http://colormind.io/api-access/ and read the API description.
6. Based on the description, make predictions: what's the input, what's the output?
7. Try it out both using `curl` and using the [httpie online app](http://httpie.io/app)
8. Describe the endpoints precisely: what's the inputs, what's the outputs, how they relate. Verify your hypotheses by calling the API either with `curl` or the httpie online app.

## 3. Tuetter 

**Goal:** Can program against an interface, understands what happens when interface changes.

In diesem Tutorium werden Sie ein neues soziales Netzwerk bauen: Tuetter.
Die einzelnen Nachrichten heißen "Tuets".

### Das Projekt

In diesem Projekt befinden sich zwei Ordner: `server` und `client`.
Öffnen Sie jedoch nicht den Ordner `server` und nicht
den Ordner `client` in Ihrer IDE, sondern diesen Ordner.

#### Der Server

Im Ordner [`server`](./server) befindet sich `Server.scala`, diese werden Sie editieren.

#### Den Server laufen lassen

1. Öffnen Sie diesen Ordner in einem neuen Terminal
2. Führen Sie `sbt server/run` aus
    - Die Ausgabe sollte "Starting server." enthalten
3. Besuchen Sie in Ihrem Browser http://localhost:8080/feed
    - Es sollte eine Seite mit "first message" angezeigt werden
4. Der Server läuft so lange bis Sie `<Enter>` drücken.
5. Beachten Sie, dass Sie nicht mehrere Instanzen des Servers laufen lassen sollten.

#### Der Client

Im Ordner [`client`](./client) befindet sich `Client.scala`, diese werden Sie editieren.

#### Den Client laufen lassen

1. Öffnen Sie diesen Ordner in einem neuen Terminal
2. Führen Sie `sbt client/run` aus
    - Die Ausgabe sollte "Starting client." enthalten
3. Geben Sie die kurze Nachricht "hello world" ein und bestätigen Sie mit `<Enter>`
4. Besuchen Sie erneut (refresh) die Seite http://localhost:8080/feed
    - Auf der Seite sollte jetzt auch "hello world" stehen
5. Der Client läuft so lange bis sie `Strg + C` drücken.

### Vorwissen

#### Das Hypertext Transfer Protocol (HTTP)

Der Server und der Client kommunizieren über das Hypertext Transfer Protocol (HTTP).
Eine Anfrage (request) besteht aus
  - einer Anfragemethode (method),
  - einem Uniform Resource Locator (URL),
  - und einem Rumpf (body).
Die beiden wichtigsten Anfragemethoden sind `GET` und `POST`.
Ein Uniform Resource Locator ist eine Webadresse, zum Beispiel "http://example.com/site".
Der Rumpf kann beliebige Daten enthalten.

### Aufgabe 3.1: Neues Verhalten, gleiches Interface

In dieser Aufgabe fügen Sie die aktuelle Zeit zu jedem Tuet hinzu.

1. Erweitern Sie die Datendefinition `Tuet` um ein Feld `time: java.time.LocalTime`
2. Ihre IDE weist Sie auf die beiden Stellen hin die Sie verändern müssen.
3. In der Funktion `feed` müssen Sie beim pattern matching auch das Feld `time` extrahieren.
    - Der String könnte zum Beispiel so aussehen `s"$time $message"`
4. In der Funktion `tuet` müssen Sie den `Tuet` auch mit der aktuellen Zeit konstruieren.
    - Sie bekommen die aktuelle Zeit mit `java.time.LocalTime.now()`
5. Starten Sie den Server neu und beobachten Sie, dass der Client nach wie vor funktioniert.

### Aufgabe 3.2: Neues Verhalten, erweitertes Interface

In dieser Aufgabe fügen Sie die Möglichkeit hinzu den letzten Tuet zu liken.

1. Erweitern Sie die Datendefinition `Tuet` um ein Feld `likes: Int`.
2. Ihre IDE weist Sie auf die beiden Stellen hin die Sie verändern müssen.
3. In der Funktion `feed` müssen Sie beim pattern matching auch das Feld `likes` extrahieren.
    - Der String könnte zum Beispiel so aussehen `s"$time $likes $message"`
4. In der Funktion `tuet` müssen Sie den `Tuet` auch mit einer Anzahl Likes konstruieren.
    - Wahrscheinlich sollte die Anzahl am Anfang `0` sein
5. Erstellen Sie eine neue Funktion `def like() = { ??? }`
6. Schreiben Sie unmittelbar über die Funktionsdefinition `@cask.post("/like")`
7. Erhöhen Sie in der Funktion `like` die Anzahl der Likes des ersten Tuets
8. Starten Sie den Server neu und beobachten Sie, dass der Client nach wie vor funktioniert.

Mit `requests.post("http://localhost:8080/like")` können Sie im Client
die neue Funktionalität des Servers ansteuern.
Zum Beispiel wenn der Benutzer die Nachricht `"like"` eingegeben hat.

### Aufgabe 3.3: Neues Verhalten, anderes Interface

In dieser Aufgabe fügen Sie Namen zu den Tuets hinzu.

1. Erweitern Sie die Datendefinition `Tuet` um ein Feld `name: String`.
2. Ihre IDE weist Sie auf die beiden Stellen hin die Sie verändern müssen.
3. In der Funktion `feed` müssen Sie beim pattern matching auch das Feld `name` extrahieren.
    - Der String könnte zum Beispiel so aussehen `s"$name $time $likes $message"`
4. In der Funktion `tuet` müssen Sie den `Tuet` auch mit einem Namen konstruieren.
    - Fügen Sie einen Parameter `name: String` zu der Funktion hinzu
    - Ändern Sie die Zeile oberhalb der Funktionsdefinition zu `@cask.post("/tuet/:name")`
5. Starten Sie den Server neu und beobachten Sie, dass der Client nicht mehr funktioniert.
    - Wie hätte man das verhindern können?

Mit `requests.post("http://localhost:8080/tuet/" + name, data = message)` können Sie die
veränderte Funktionalität des Servers ansteuern.
Zum Beispiel können Sie den Benutzer auffordern am Anfang einen Namen einzugeben.

Gratulation, das neue soziale Netzwerk Tuetter geht bestimmt durch die Decke.

### Diskussion

Welches war die fragliche Schnittstelle? Wie hat sie sich in den einzelnen Schritten entwickelt? Was sind die möglichen Probleme bei der Entwicklung einer Schnittstelle?
