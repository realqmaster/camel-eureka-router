# camel-eureka-router

Questo repository nasce per proporre una soluzione alternativa alla conversione main/product localizzata a monte o valle, introducendo un terzo componente dedicato (da ora "Router") che abbia al suo interno la dipendenza da tutti i domini lasciando disaccoppiati i moduli main e di prodotto.

Di fatto si basa sul pattern EIP [Content Based Router](https://www.enterpriseintegrationpatterns.com/patterns/messaging/ContentBasedRouter.html) implementandolo tramite rotte Camel integrate in un modulo Spring Boot, a sua volta registrato su un registro Eureka di esempio.

Nell'esempio proposto, il router espone l'endpoint **/serviceCall/service** che accetta un singolo query param "type". 
Il controller del router è agganciato ad una rotta Camel e la invoca passando come header il parametro ricevuto e un body di esempio;  la rotta a sua volta valuta l'header che accompagna il messaggio e decide il comportamento da adottare:

* Se il tipo è A, il body testuale viene inoltrato così com'è al servizio A, risolvendone host e porta tramite Eureka
* Se il tipo è B il body viene convertito nel tipo SpecialBody e serializzato, per poi essere inoltrato al servizio B.
* In tutti gli altri casi il router risponde che il tipo non è supportato.


La possibilità di sfruttare il service registry già a nostra disposizione mantiene integri i vantaggi di Eureka, mentre la possibilità di centralizzare la logica di conversione e dispatching in un componente indipendente dai moduli main e di prodotto.
