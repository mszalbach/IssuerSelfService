# language: de

Funktionalität: Authentifizierung
  Als Börse
  möchte ich das sich alle Webseitenbenutzer anmelden müssen
  damit der Zugriff geregelt ist
  und jeder nur die für ihn erlaubten Sachen tun darf

  Grundlage:
    Angenommen niemand ist gerade angemeldet

  Szenario: Erfolgreich anmelden
    Wenn Ralf sich einloggt mit password 'ralf'
    Dann sollte er Zugriff auf die Seite bekommen

  Szenario: Logging on with an incorrect password
    Wenn Ralf sich einloggt mit password 'falsch'
    Dann sollte er informiert werden das der Login fehlgeschlagen ist