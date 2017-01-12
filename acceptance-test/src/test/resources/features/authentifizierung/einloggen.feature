# language: de

Funktionalität: Authentifizierung
  Als Börse
  möchte ich das sich alle Webseitenbenutzer anmelden müssen
  damit der Zugriff geregelt ist
  und jeder nur die für ihn erlaubten Sachen tun darf

  Szenario: Erfolgreich anmelden
    Angenommen Emil will sich anmelden
    Wenn Emil sich einloggt mit Passwort "emil"
    Dann sollte er eingelogt sein als Emil

  Szenario: anmelden mit falschen Passwort
    Angenommen Emil will sich anmelden
    Wenn Emil sich einloggt mit Passwort "falsch"
    Dann sollte der Login fehlschlagen mit "401:Bad credentials"

  Szenario: Ausloggen
    Angenommen Emil ist angemeldet mit Password "emil"
    Wenn er sich ausloggt
    Dann sollte er den "Sign In" Knopf sehen