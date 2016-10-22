# language: de

Funktionalität: Online Kotierung
  Als ein Emittent
  möchte ich in der Lage sein meine Kotierungen Online zu machen
  damit ich einfacher kotieren kann

  Szenario: alle meine Wertpapiere anzeigen
    Angenommen Ralf hat folgende Wertpapiere
      | isin         | symbol |
      | US02079K1079 | GOOG   |
      | US0378331005 | AAPL   |
    Dann sollte seine Werpapierliste 2 Einträge haben

