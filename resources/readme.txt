Implementierung der Explicit Semantic Analysis-Methode

von Justin Käser

Konfiguration:

	Die Konfiguration der Datenbank-Verbindungsparameter befindet sich im Interface 
	justinkaeser.esa.app.Properties.
	

Anwendungen:

	Vergleich von Dokumenten auf Ähnlichkeit
	
		Aufruf der Klasse justinkaeser.esa.app.DocSimilarity mit 2 Dateinamen als Parameter. 
		Die Dateien werden eingelesen und ihre auf Basis des Index erstellten Konzeptvektoren
		mittels Kosinusmaß verglichen.
		

	Die wichtigsten Konzepte zu einem Dokument finden:
	
		Aufruf der Klasse justinkaeser.esa.app.TopConcepts mit einem Dateinamen als Parameter.
		Per Default werden die 10 höchst bewerteten Dokumente ausgegeben.
		
		
Die vorliegende Implementierung der Explicit Semantic Analysis ist etwas rudimentär:
Der Index wird nicht persistiert, wird also bei jedem Programmaufruf neu erstellt. Zudem ist die Erstellung
desselben recht zeit- und speicheraufwändig. Daher habe ich die Anzahl der in den Index aufgenommenen
Wikipedia-Artikel auf 1000 begrenzt. Diese Zahl lässt in justinkaeser.esa.app.IOUtil über die Konstante
MAX_INDEX_SIZE ändern. Für ernsthafte Anwendung ist diese Implementierung also noch nicht geeignet, ließe sich
aber mit vertretbarem Aufwand noch optimieren. Im Prinzip aber ist die Funktionalität vorhanden und überprüfbar. 