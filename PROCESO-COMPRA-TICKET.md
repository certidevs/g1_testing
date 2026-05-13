1. movie-detail.html
    * sessionRepo.findByMovieId

2. session-detail.html    /sessions/1
    * room
    * movie
    * opcion 1:
        * ticketRepository.findBySessionIdAndPurchaseTimeIsNull mostrar como columnas con botón de Comprar (similar a order-detail muestra dishes)
    * opción 2:
        * ticketRepository.findBySessionId
        * if ticket.purchaseTime == null  entonces mostrr botón verde de Comprar
        * if ticket.purchaseTime != null  entonces mostrar badge Vendido gris/rojo


3. ticket-detail.html  /tickets/1
    * si estado distinto de FINISHED entonces dejar añadir entidad Food osea columnas de comida con botón Añadir
    * entity Food asociada @ManyToOne a Ticket ticket