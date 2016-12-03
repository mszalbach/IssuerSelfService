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
    Und Ralf ist angemeldet
    Dann sollte seine Werpapierliste 2 Einträge haben

  Szenario: Neues Wertpapier anlegen
    Angenommen Ralf ist angemeldet
    Wenn er ein Wertpapier mit folgenden Daten anlegt
      | isin         | symbol |
      | US5949181045 | MSFT   |
    Dann sollte es folgendes Wertpapier existieren
      | isin         | symbol |
      | US5949181045 | MSFT   |

  Szenario: Wertpapiere löschen
    Angenommen Ralf hat folgende Wertpapiere
      | isin         | symbol |
      | US9843321061 | YHOO   |
    Wenn Ralf ist angemeldet
    Und er das Wertpapier "US9843321061" löscht
    Dann gibt es kein Wertpapier "US9843321061" mehr

  Szenario: Ungültiges Wertpapier anlegen
    Angenommen Ralf ist angemeldet
    Wenn er ein Wertpapier mit folgenden Daten anlegen will
      | isin   | symbol | nominalValue |
      | FALSCH | MSFT   | -2           |
    Dann sollte das Anlegen fehlschlagen mit folgenden Fehlern:
      | ISIN not Valid. |