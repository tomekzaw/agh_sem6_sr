<br>
Tomasz Zawadzki

Nr albumu: 296721

## Zadanie 1.

### Resume vs. restart

```
Started. Commands: 'hi', 'm [nb1] [nb2]', 'q'
hi
hello
d 9 3
result: 3 (operation count: 1)
d 12 4
result: 3 (operation count: 2)
d 15 5
result: 3 (operation count: 3)
d 18 0
[WARN] [05/13/2020 17:34:40.471] [local_system-akka.actor.default-dispatcher-5] [akka://local_system/user/math/divideWorker] / by zero
d 21 7
result: 3 (operation count: 5)
d test
[ERROR] [05/13/2020 17:34:50.200] [local_system-akka.actor.default-dispatcher-4] [akka://local_system/user/math/divideWorker] For input string: "test"
java.lang.NumberFormatException: For input string: "test"
	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:68)
	at java.base/java.lang.Integer.parseInt(Integer.java:658)
	at java.base/java.lang.Integer.parseInt(Integer.java:776)
	at Z1_DivideWorker.Divide(Z1_DivideWorker.java:26)
	at Z1_DivideWorker.lambda$createReceive$0(Z1_DivideWorker.java:17)
	at akka.japi.pf.UnitCaseStatement.apply(CaseStatements.scala:24)
	()...)

d 24 8
result: 3 (operation count: 1)
d 27 9
result: 3 (operation count: 2)
```

Przy strategii resume (dla błędu dzielenia przez zero) licznik operacji nie został zresetowany, ponieważ działanie aktora zostało wznowione, natomiast przy strategii restart (dla nieprawidłowych danych wejściowych) licznik zrestartował się, ponieważ aktor został zrestartowany.

<div style="page-break-after: always;"></div>

### OneForOneStrategy vs. AllForOneStrategy

##### OneForOneStrategy
```
Started. Commands: 'hi', 'm [nb1] [nb2]', 'q'
m 2 3
result: 6 (operation count: 1)
m 2 3
result: 6 (operation count: 2)
d 12 4
result: 3 (operation count: 1)
d 12 4
result: 3 (operation count: 2)
m test
[ERROR] [05/13/2020 17:23:25.028] [local_system-akka.actor.default-dispatcher-6] [akka://local_system/user/math/multiplyWorker] For input string: "test"
java.lang.NumberFormatException: For input string: "test"
	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:68)
	(...)

m 2 3
result: 6 (operation count: 1)
d 12 4
result: 3 (operation count: 3)
```

##### AllForOneStrategy
```
Started. Commands: 'hi', 'm [nb1] [nb2]', 'q'
m 2 3
result: 6 (operation count: 1)
m 2 3
result: 6 (operation count: 2)
d 12 4
result: 3 (operation count: 1)
d 12 4
result: 3 (operation count: 2)
m test
[ERROR] [05/13/2020 17:24:14.629] [local_system-akka.actor.default-dispatcher-4] [akka://local_system/user/math/multiplyWorker] For input string: "test"
java.lang.NumberFormatException: For input string: "test"
	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:68)
	(...)

m 2 3
result: 6 (operation count: 1)
d 12 4
result: 3 (operation count: 1)
```

W przypadku strategii OneForOne resetowany jest jedynie ten aktor, u którego wystąpił błąd (MultiplyActor).

W przypadku strategii AllForOne zresetowani zostali obaj aktorzy, ponieważ strategia zostaje uruchomiona dla wszystkich podwładnych, pomimo że tylko jeden spośród nich zgłosił błąd.

<div style="page-break-after: always;"></div>

## Zadanie 2.

##### Z2_AppLocal
```
[WARN] [05/13/2020 17:11:03.767] [main] [akka.remote.RemoteActorRefProvider] Using the 'remote' ActorRefProvider directly,
which is a low-level layer. For most use cases, the 'cluster' abstraction on top of remoting is more suitable instead.
[WARN] [05/13/2020 17:11:03.768] [main] [akka.remote.RemoteActorRefProvider] Akka Cluster not in use - Using Akka Cluster
is recommended if you need remote watch and deploy.
[INFO] [05/13/2020 17:11:05.559] [main] [ArteryTcpTransport(akka://local_system)] Remoting started with transport [Artery
tcp]; listening on address [akka://local_system@127.0.0.1:2551] with UID [344458399110511315]
Hello world!
Sent request: Hello world!
Received response: HELLO WORLD!
```

##### Z2_AppRemote
```
[WARN] [05/13/2020 17:11:08.473] [main] [akka.remote.RemoteActorRefProvider] Using the 'remote' ActorRefProvider directly,
which is a low-level layer. For most use cases, the 'cluster' abstraction on top of remoting is more suitable instead.
[WARN] [05/13/2020 17:11:08.473] [main] [akka.remote.RemoteActorRefProvider] Akka Cluster not in use - Using Akka Cluster
is recommended if you need remote watch and deploy.
[INFO] [05/13/2020 17:11:10.149] [main] [ArteryTcpTransport(akka://remote_system)] Remoting started with transport [Artery
tcp]; listening on address [akka://remote_system@127.0.0.1:2552] with UID [-1547781537305956452]
Received request: Hello world!
Sent response: HELLO WORLD!
```

<div style="page-break-after: always;"></div>

## Zadanie 3.

##### Mapowanie bez async
```
20
40
60
80
100
120
140
160
180
200
10.104410401 seconds
```

##### Mapowanie z async
```
20
40
60
80
100
120
140
160
180
200
5.618781499000001 seconds
```

##### Dodany bufor ze strategią dropHead
```
160
180
200
2.0916943000000003 seconds
```

##### Bufor ze strategią dropTail
```
20
40
200
2.1056236 seconds
```

##### Bufor ze strategią backpressure
```
20
40
60
80
100
120
140
160
180
200
5.597836201000001 seconds
```