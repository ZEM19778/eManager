USE emanager;
-- Mitarbeiter
INSERT INTO emanager.m_mitarbeiter (m_id, m_passwort, m_rolle, m_benutzername, m_vorname) VALUES (2, 'user1', 'USER', 'user1', null);
INSERT INTO emanager.m_mitarbeiter (m_id, m_passwort, m_rolle, m_benutzername, m_vorname) VALUES (5, 'user2', 'USER', 'user2', null);
INSERT INTO emanager.m_mitarbeiter (m_id, m_passwort, m_rolle, m_benutzername, m_vorname) VALUES (18, 'admin1', 'ADMIN', 'admin1', null);
INSERT INTO emanager.m_mitarbeiter (m_id, m_passwort, m_rolle, m_benutzername, m_vorname) VALUES (19, 'admin2', 'ADMIN', 'admin2', null);


-- Baustelle
INSERT INTO emanager.b_baustelle (b_id, b_bezeichnung) VALUES (1, 'Spengergasse 20');
INSERT INTO emanager.b_baustelle (b_id, b_bezeichnung) VALUES (2, 'Wienerberg');
INSERT INTO emanager.b_baustelle (b_id, b_bezeichnung) VALUES (3, 'Der Mond');
INSERT INTO emanager.b_baustelle (b_id, b_bezeichnung) VALUES (4, 'Bergmanns Keller');

-- Hibernate Sequence
INSERT INTO emanager.hibernate_sequence (next_val) VALUES (0);