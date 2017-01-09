# language: de

Funktionalität: Online Kotierung
  Als ein Emittent
  möchte ich in der Lage sein meine Kotierungen Online zu machen
  damit ich einfacher kotieren kann

  Szenario: alle meine Wertpapiere anzeigen
    Gegeben sei Ralf ist angemeldet mit Password "ralf"
    Und er hat folgende Wertpapiere
      | isin         | symbol |
      | US02079K1079 | GOOG   |
      | US0378331005 | AAPL   |
    Wenn er auf die Wertpapierliste geht
    Dann sollte seine Werpapierliste mindestens 2 Einträge haben

  Szenario: Neues Wertpapier anlegen
    Gegeben sei Ralf ist angemeldet mit Password "ralf"
    Und er auf die Wertpapierliste geht
    Wenn er ein Wertpapier mit folgenden Daten anlegt
      | isin         | symbol |
      | US5949181045 | MSFT   |
    Dann sollte es ein Wertpapier mit folgenden Daten exisitieren
      | isin         | symbol |
      | US5949181045 | MSFT   |

  Szenario: Wertpapiere löschen
    Angenommen Ralf ist angemeldet mit Password "ralf"
    Und er hat folgende Wertpapiere
      | isin         | symbol |
      | US9843321061 | YHOO   |
    Wenn er das Wertpapier "US9843321061" löscht
    Dann gibt es kein Wertpapier "US9843321061" mehr

  Szenario: Ungültiges Wertpapier anlegen
    Angenommen Ralf ist angemeldet mit Password "ralf"
    Wenn er ein Wertpapier mit folgenden Daten anlegen will
      | isin   | symbol | nominalValue |
      | FALSCH | MSFT   | -2           |
    Dann sollte das Anlegen fehlschlagen mit folgenden Fehlern:
      | ISIN not Valid. |