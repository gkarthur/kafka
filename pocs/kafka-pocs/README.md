# POC Kafka

## Uses Case

<img src="/pocs/screenshots/archi.PNG" width="600">

<ol>
  <li>Création de demande via IHM, ou bien via application batch. Les demandes sont enregistrées dans la file de message (Topic Kafka) demande-in</li>
  <li>Le service de gestion des demandes recupère les demandes depuis la file puis effectue les opération de création ou de mise à jour de demandes</li>
</ol>

