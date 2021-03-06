# language: de

Funktionalität: einfacher Accept/Reject Workflow
  Als Börse
  möchte ich Wertpapiere einfach akzeptieren oder ablehnen können
  damit ich weniger manuellen Aufwand habe und der Emittent schneller Feedback bekommt


  Grundlage:
    Angenommen Emil ist angemeldet mit Password "emil"

  Szenario: Ein neues Wertpapier sollte erwartete Aktionen erlauben
    Angenommen Emil hat folgendes Wertpapier
      | isin         | symbol |
      | DE000BAY0017 | BAYN   |
    Wenn er auf die Wertpapierliste geht
    Dann kann er folgende Aktionen auf "DE000BAY0017" ausführen
      | request |
      | X       |

  Szenario: Ein neues Wertpapier beantragen
    Angenommen Emil hat folgendes Wertpapier
      | isin         | symbol |
      | DE0005190003 | BMW    |
    Wenn er auf die Wertpapierliste geht
    Und das Wertpapier "DE0005190003" beantragt
    Dann sollte "DE0005190003" den Status "Requested" haben
    Und er kann folgende Aktionen auf "DE0005190003" ausführen
      | X |

