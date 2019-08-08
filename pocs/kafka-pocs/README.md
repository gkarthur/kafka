# POC Kafka

## Uses Case

<img src="/pocs/screenshots/archi.PNG" width="600" align="center">

<ol>
  <li>Création de demande via IHM, ou bien via application batch. Les demandes sont enregistrées dans la file de message (Topic Kafka) <b>demandes-in</b></li>
  <li>Le service de gestion des demandes recupère les demandes au fil de l'eau depuis la file puis enregistre les demandes en base de données</li>
  <li>Le service de gestion des demandes transmet la nouvelle demande, via la file de message <b>demandes-out</b>, aux services Logistique et facturation</li>
  <li>Le service Logistique recupère les demandes, puis lance les opérations de préparation et d'expédition de la commande. Le service Facturation recupère les demandes, puis lance les opérations de facturation</li>
  <li>Le service Logistique transmet les modifications effectuées sur la demande au service de gestion des demandes. Le service de gestion des demandes met à jour la demande en base</li>
  <li>De manière transverse, tous les modules envoient des traces dans la file de message dédiée <b>traces</b>. Puis le module de gestion des traces les récupère puis les sauvegarde dans une base Elasticsearch</li>
</ol>

## Build

```
cd kafka-pocs
mvn clean install
```

## Demo

### Download tools

Télécharger et installer les composants Kafka 2.11-2.2.0 et Elasticsearch 6.6.0. La configuration par défaut suffit pour la démo. Pas besoin du mode cluster et Kafka et Elasticsearch.

### Démarrage du serveur Kafka

```
cd C:\tools\kafka_2.11-2.2.0
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

### Création des topics

```
cd C:\tools\kafka_2.11-2.2.0
.\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3 --topic traces
.\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3 --topic demandes-in
.\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3 --topic demandes-out
```

### Démarrage du serveur ElasticSearch

```
cd C:\tools\elasticsearch-6.6.0
.\bin\elasticsearch.bat
```

### Démarrage du service Gestion des demandes

```
java -jar kafka-pocs-demandes\target\kafka-pocs-demandes-0.0.1-SNAPSHOT.jar
```

### Démarrage du service Logistique

```
java -jar kafka-pocs-logistique\target\kafka-pocs-logistique-0.0.1-SNAPSHOT.jar
```

### Démarrage du services Batch

```
java -jar kafka-pocs-batch\target\kafka-pocs-batch-0.0.1-SNAPSHOT.jar
```

### Démarrage du service Gestion des traces

```
java -jar kafka-pocs-traces\target\kafka-pocs-traces-0.0.1-SNAPSHOT.jar
```

### Visualiser les demandes

Se connecter à l'application de Gestion des demandes : http://localhost:8080/app-demandes/demandes.

<img src="/pocs/screenshots/Demandes.PNG" width="600" align="center">

Rafraichir la page toute les 5 secondes pour voir les demandes changer de statut.

### Visualiser les traces

Afficher les documents traces indexés dans Elasticsearch : http://localhost:9200/_search


