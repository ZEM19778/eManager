USE emanager;
-- Mitarbeiter
INSERT INTO emanager.m_mitarbeiter (m_id, m_passwort, m_rolle, m_benutzername, m_vorname) VALUES (2, 'user1', 'USER', 'user1', null);
INSERT INTO emanager.m_mitarbeiter (m_id, m_passwort, m_rolle, m_benutzername, m_vorname) VALUES (5, 'user2', 'USER', 'user2', null);
INSERT INTO emanager.m_mitarbeiter (m_id, m_passwort, m_rolle, m_benutzername, m_vorname) VALUES (18, 'admin1', 'ADMIN', 'admin1', null);
INSERT INTO emanager.m_mitarbeiter (m_id, m_passwort, m_rolle, m_benutzername, m_vorname) VALUES (19, 'admin2', 'ADMIN', 'admin2', null);

-- Dienste
INSERT INTO emanager.d_dienste (d_id, d_datum, d_dauer, d_mitarbeiter, d_bis, d_von, b_d_id) VALUES (1, '2023-01-13 00:00:00', 4, 'user1', '20:24:00', '16:24:00', 1);
INSERT INTO emanager.d_dienste (d_id, d_datum, d_dauer, d_mitarbeiter, d_bis, d_von, b_d_id) VALUES (2, '2023-01-03 00:00:00', 4, 'user1', '15:43:00', '11:43:00', 2);
INSERT INTO emanager.d_dienste (d_id, d_datum, d_dauer, d_mitarbeiter, d_bis, d_von, b_d_id) VALUES (3, '2023-01-21 00:00:00', 0.1, 'user2', '11:49:00', '11:43:00', 3);
INSERT INTO emanager.d_dienste (d_id, d_datum, d_dauer, d_mitarbeiter, d_bis, d_von, b_d_id) VALUES (4, '2023-01-12 00:00:00', 4, 'user2', '09:12:00', '05:12:00',4);

-- Nachrichten
INSERT INTO emanager.n_nachrichten (n_id, n_datumzeit, n_nachricht, n_sender) VALUES (2, '10.Jän.2023 11:22:49', 'Hallo test', null);
INSERT INTO emanager.n_nachrichten (n_id, n_datumzeit, n_nachricht, n_sender) VALUES (7, '11.Jän.2023 15:17:18', 'Wienerberg entfällt', null);

-- Termine
INSERT INTO emanager.t_termine (t_id, t_beginn, t_beschreibung, t_datum, t_ende, t_betrifft) VALUES (1, '13:15:00', 'Test1 Termi n', '2023-01-17', '19:15:00', 'Alle');
INSERT INTO emanager.t_termine (t_id, t_beginn, t_beschreibung, t_datum, t_ende, t_betrifft) VALUES (2, '14:29:00', 'test', '2023-01-20', '16:29:00', 'Admin');
INSERT INTO emanager.t_termine (t_id, t_beginn, t_beschreibung, t_datum, t_ende, t_betrifft) VALUES (3, '08:29:00', 'usertreffen', '2023-01-20', '14:30:00', 'Mitarbeiter');
INSERT INTO emanager.t_termine (t_id, t_beginn, t_beschreibung, t_datum, t_ende, t_betrifft) VALUES (4, '15:30:00', 'Weihnachtsfeier', '2023-12-22', '23:30:00', 'Alle');

-- Urlaub
INSERT INTO emanager.u_urlaub (u_id, u_beantragt_name, u_beginn, u_beschreibung, u_ende, u_genehmigt) VALUES (1, 'user', '2023-01-17', 'Pflegeurlaub für Tasic', '2023-01-26', 'Ja');

-- Baustelle
INSERT INTO emanager.b_baustelle (b_id, b_bezeichnung) VALUES (1, 'Spengergasse 20');
INSERT INTO emanager.b_baustelle (b_id, b_bezeichnung) VALUES (2, 'Wienerberg');
INSERT INTO emanager.b_baustelle (b_id, b_bezeichnung) VALUES (3, 'Der Mond');
INSERT INTO emanager.b_baustelle (b_id, b_bezeichnung) VALUES (4, 'Bergmanns Keller');

-- Hibernate Sequence
INSERT INTO emanager.hibernate_sequence (next_val) VALUES (0);