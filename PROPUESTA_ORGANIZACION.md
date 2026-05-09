# Propuesta de organización — Proyecto Cine

Hola a todos. He estado pensando en cómo podemos organizarnos mejor de aquí a junio y he preparado esta propuesta. Leedla con calma y comentad en el WhatsApp si estáis de acuerdo o si cambiaríais algo.

---

## El problema actual

Ahora mismo cada uno trabaja en su entidad de forma más o menos independiente, lo que en general está funcionando. Pero hay dos cosas que pueden complicarnos la entrega:

- **El nivel de avance no es igual en todos.** Movie y Room están bastante avanzadas, pero Review prácticamente no ha arrancado, y los HTMLs de Session y Ticket están vacíos. Cuando el profe introduzca servicios, y bueno, formularios ya prácticamente estamos terminando de verlo, los que vayan por detrás tendrán que ponerse al día y avanzar a la vez, lo cual es mucho más difícil.
- **No tenemos visibilidad real de en qué está cada uno.** Sin un sitio donde ver el estado de las tareas, es fácil que algo se quede parado sin que nadie lo sepa hasta que ya es tarde.

---

## Lo que propongo

### 1. Usar GitHub Projects como tablero

GitHub tiene una funcionalidad llamada **Projects** que permite convertir las Issues en tarjetas y organizarlas en columnas. La idea sería crear un tablero sencillo con cuatro columnas:

```
Por hacer → En progreso → Bloqueado → Hecho
```

Cada vez que alguien empiece una tarea, mueve su tarjeta a "En progreso". Si se atasca con algo, la mueve a "Bloqueado" y lo comenta en el WhatsApp. Así cualquiera puede abrir el tablero y ver el estado real del proyecto sin tener que preguntar.

No es mucho trabajo configurarlo, puedo encargarme de montar el tablero inicial con todas las Issues que ya tenemos.

---

### 2. Dos fases de trabajo hasta junio

**Fase 1 — Nivelación (objetivo: antes de que el profe introduzca servicios)**

El objetivo es que las 5 entidades tengan lo mínimo funcional:

| Quién | Entidad | Objetivo de la Fase 1 |
|---|---|---|
| Barbi | Movie | Ya muy avanzada ✓ — revisar y pulir |
| Adri | Room | Ya muy avanzada ✓ — revisar y pulir |
| Andrés | Session | HTMLs funcionales + Bootstrap aplicado |
| Fran | Ticket | HTMLs funcionales + Bootstrap aplicado |
| Nicolás | Review | Entidad + repositorio + controlador + HTMLs básicos |

Mientras el resto sigue avanzando, Nicolás necesita arrancar Review desde cero. Si alguien tiene un rato, le podemos echar una mano aunque sea por WhatsApp con dudas puntuales porque creo que sería de gran ayuda.

**Fase 2 — Construcción (una vez estemos nivelados)**

Con la base sólida, avanzamos juntos con:

- Formularios de creación y edición
- Testing de controladores con MockMvc
- Servicios (cuando Alan los explique)
- Selenium (cuando llegue)

Ir juntos en esta fase es importante porque si uno va muy por delante o muy por detrás, es más difícil ayudarse.

---

### 3. La tarea sin asignar: desactivar/borrar

La tarea de "Acción desactivar/borrar entidad" no tiene nadie asignado. Propongo que se la quede **Nicolás**, por dos razones: afecta principalmente a Reviews (su entidad) y es una tarea acotada y manejable para alguien que está arrancando. En Room ya hay un ejemplo funcionando del que puede tirar (`/salas/deactivate/{id}`) o sino en Restaurantes lo hemos estado viendo esta semana.

---

### 4. Una línea en el WhatsApp los viernes

No propongo reuniones ni nada formal. Solo que cada viernes, quien haya podido avanzar esa semana escriba dos líneas en el grupo diciendo qué ha hecho y si tiene algo bloqueado. Algo tan simple como:

> *"Esta semana he montado el HTML de session-list y empecé el controlador. De momento sin bloqueos."*

Con eso tenemos visibilidad sin necesidad de organizar nada más.

---

## Resumen

| Propuesta | Esfuerzo | Beneficio |
|---|---|---|
| GitHub Projects (tablero) | Bajo (Lo puedo montar yo, Adri) | Ver el estado real de todo en un vistazo |
| Fase 1 de nivelación | Medio (sobre todo Nicolás) | Llegar juntos a los formularios y servicios |
| Tarea borrar/desactivar → Nicolás | Bajo-medio | Tarea acotada, ejemplo en Restaurantes |
| Viernes en el WhatsApp | Muy bajo | Detectar bloqueos antes de que se agranden |

---

**¿Estáis de acuerdo? ¿Cambiaríais algo?** Comentad en el WhatsApp y si hay consenso, arrancamos esta semana.
