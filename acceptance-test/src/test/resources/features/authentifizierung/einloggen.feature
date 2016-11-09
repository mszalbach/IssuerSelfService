# language: de

Funktionalität: Authentifizierung
  Als Börse
  möchte ich das sich alle Webseitenbenutzer anmelden müssen
  damit der Zugriff geregelt ist
  und jeder nur die für ihn erlaubten Sachen tun darf

  Szenario: Erfolgreich anmelden
    Wenn Ralf sich einloggt mit Passwort "ralf"
    Dann sollte er eingelogt sein als Ralf

  Szenario: anmelden mit falschen Passwort
    Wenn Ralf sich einloggt mit Passwort "falsch"
    Dann sollte er informiert werden das der Login fehlgeschlagen ist