# 📌 ClashCards

**ClashCards** è un gioco di ruolo (RPG) a turni basato sull'uso di carte, sviluppato in Java con interfaccia grafica Swing.
Il progetto è stato realizzato per il corso di Modellazione e Gestione della Conoscenza con l'obiettivo di applicare i principali pilastri della programmazione ad oggetti e della progettazione software.

## 🚀 Funzionalità

* **Combattimento a turni:** Scontri strategici tra il protagonista (Warden) e diversi nemici.
* **Gestione delle Abilità:** Le carte possiedono abilità specifiche; il giocatore può scegliere attivamente se attivarla durante il proprio turno, mentre per i nemici l'attivazione delle abilità è automatica e integrata nel loro pattern di attacco.
* **Progressione a stage:** Struttura di gioco a livelli sequenziali con difficoltà variabile.
* **Caricamento dinamico dei nemici:** I dati dei mostri vengono parsati ed estratti da un file di configurazione XML.
* **Sistema di Salvataggio:** Possibilità di salvare e caricare lo stato della partita corrente (inclusi i dettagli precisi della battaglia in corso) tramite persistenza dei dati.
* **Interfaccia Grafica (GUI):** Sviluppata interamente in Swing per gestire menu, finestre di gioco e pannelli dinamici.

---

## 🛠️ Tecnologie Utilizzate

| Tecnologia           | Utilizzo |
|:---------------------| :--- |
| **Java 25**          | Linguaggio di programmazione principale |
| **Swing**            | Sviluppo dell'Interfaccia Grafica (GUI) |
| **Gson (2.10.1)**    | Serializzazione e deserializzazione JSON per il salvataggio della partita |
| **DOM Parser / XML** | Gestione e parsing dei dati dei nemici di gioco |
| **JUnit 5**          | Framework per i test automatizzati della logica di business |
| **Gradle (9.2.0)**   | Build system per la gestione delle dipendenze e l'esecuzione dei task |
| **Git / GitHub**     | Controllo di versione e coordinamento del codice |

---

## 💻 Build ed Esecuzione

### Prerequisiti
* **Java 25** (LTS)
* **Gradle** (gestito automaticamente tramite Gradle Wrapper)

### Clone del progetto
```bash
git clone https://github.com/Corrado-lab/ClashCards.git
cd ClashCards
```

### 1. Compilazione e Test
Per compilare il codice sorgente ed eseguire l'intera suite di test automatizzati, lancia il comando:
```bash
./gradlew build
```

### 2. Avvio del Gioco
Per eseguire l'applicazione e avviare l'interfaccia grafica del gioco, lancia il comando:
```bash
./gradlew run
```

### 3. Esecuzione dei Test alternativi
Per eseguire esclusivamente la suite di test JUnit senza ricompilare l'intero progetto:
```bash
./gradlew test
```

---

## 🤖 Uso di Strumenti di AI

Per lo sviluppo di *ClashCards* è stato utilizzato **Gemini (Google AI)** come assistente virtuale e tutor alla programmazione.

### Attività svolte con il supporto dell'AI:
* **Risoluzione di Conflitti di Compilazione e Ambiente** 
* **Gestione della Persistenza Polimorfica** 
* **Refactoring in Ottica SOLID e OOP** 
* **Supporto allo Sviluppo dell'Interfaccia Grafica**
* **Strutturazione e Revisione della Documentazione**


Ogni suggerimento fornito dallo strumento è stato analizzato e compreso a fondo prima dell'integrazione nel codice sorgente.

> 📌 *Nota: Per una descrizione dettagliata, approfondita e strutturata dell'architettura del software e delle specifiche attività svolte con l'ausilio dell'AI, si rimanda alla **Wiki** associata al repository.*