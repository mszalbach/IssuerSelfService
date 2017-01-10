# language: de

Funktionalität: Authentifizierung
  Als Börse
  möchte ich das sich alle Webseitenbenutzer anmelden müssen
  damit der Zugriff geregelt ist
  und jeder nur die für ihn erlaubten Sachen tun darf

  Szenario: Erfolgreich anmelden
    Angenommen Ralf will sich anmelden
    Wenn Ralf sich einloggt mit Passwort "ralf"
    Dann sollte er eingelogt sein als Ralf

  Szenario: anmelden mit falschen Passwort
    Angenommen Ralf will sich anmelden
    Wenn Ralf sich einloggt mit Passwort "falsch"
    Dann sollte der Login fehlschlagen mit "401:Bad credentials"

  Szenario: Ausloggen
    Angenommen Ralf ist angemeldet mit Password "ralf"
    Wenn er sich ausloggt
    Dann sollte er den "Sign In" Knopf sehen