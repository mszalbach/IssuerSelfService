# language: de

Funktionalität: Online Kotierung
  Als ein Emittent
  möchte ich in der Lage sein meine Kotierungen Online zu machen
  damit ich einfacher kotieren kann

  Szenario: alle meine Wertpapiere anzeigen
    Angenommen Emil ist angemeldet mit Password "emil"
    Und Emil hat folgende Wertpapiere
      | isin         | symbol |
      | US02079K1079 | GOOG   |
      | US0378331005 | AAPL   |
    Wenn er auf die Wertpapierliste geht
    Dann sollte seine Werpapierliste mindestens 2 Einträge haben

  Szenario: Neues Wertpapier anlegen
    Angenommen Emil ist angemeldet mit Password "emil"
    Und er auf die Wertpapierliste geht
    Wenn er ein Wertpapier mit folgenden Daten anlegt
      | isin         | symbol |
      | US5949181045 | MSFT   |
    Dann sollte ein Wertpapier mit folgenden Daten exisitieren
      | isin         | symbol |
      | US5949181045 | MSFT   |

  Szenario: Wertpapiere löschen
    Angenommen Emil ist angemeldet mit Password "emil"
    Und Emil hat folgendes Wertpapier
      | isin         | symbol |
      | US9843321061 | YHOO   |
    Wenn er auf die Wertpapierliste geht
    Und er das Wertpapier "US9843321061" löscht
    Dann gibt es kein Wertpapier "US9843321061" mehr

  Szenario: Ungültiges Wertpapier anlegen
    Angenommen Emil ist angemeldet mit Password "emil"
    Und er auf die Wertpapierliste geht
    Wenn er ein Wertpapier mit folgenden Daten anlegt
      | isin   | symbol |
      | FALSCH | MSFT   |
    Dann sollte das Anlegen fehlschlagen mit folgenden Fehlern:
      | ISIN not valid |