# language: de

Funktionalität: einfacher Accept/Reject Workflow
  Als Börse
  möchte ich Wertpapiere einfach akzeptieren oder ablehnen können
  damit ich weniger manuellen aufwand habe und der Emmitent schneller Feedback bekommt


  Grundlage:
    Angenommen Ralf ist angemeldet mit Password "ralf"

  Szenario: Ein neues Wertpapier sollte erwartete Aktionen erlauben
    Angenommen Ralf hat folgendes Wertpapier
      | isin         | symbol |
      | DE000BAY0017 | BAYN   |
    Wenn er auf die Wertpapierliste geht
    Dann kann er folgende Aktionen auf "DE000BAY0017" ausführen
      | request |
      | X       |

  Szenario: Ein neues Wertpapier beantragen
    Angenommen Ralf hat folgendes Wertpapier
      | isin         | symbol |
      | DE000BAY0017 | BAYN   |
    Wenn er auf die Wertpapierliste geht
    Und das Wertpapier "DE000BAY0017" beantragt
    Dann sollte "DE000BAY0017" den Status "Requested" haben


