# POC Kafka

## Uses Case

<img src="/pocs/screenshots/archi.PNG" width="600" align="center">

<ol>
  <li>Création de demande via IHM, ou bien via application batch. Les demandes sont enregistrées dans la file de message (Topic Kafka) <b>demandes-in</b></li>
  <li>Le service de gestion des demandes recupère les demandes depuis la file puis enregistre les demandes en bases</li>
  <li>Le service de gestion des demandes transmet la nouvelle demande, via la file de message demandes-out, aux services Logistique et facturation</li>
  <li>Le service Logistique recupère les demandes, puis lance les opérations de préparation et d'expédition de la commande. Le service Facturation recupère les demandes, puis lance les opérations de facturation</li>
  <li>Le service Logistique transmet les modifications effectuées sur la demande au service de gestion des demandes. Le service de gestion des demandes met à jour la demande en base</li>
  <li>De manière transverse, tous les modules envoient des traces dans la file de message dédiée <b>traces</b>. Puis le module de gestion des traces les récupère puis les sauvegarde dans une base Elasticsearch</li>
</ol>

