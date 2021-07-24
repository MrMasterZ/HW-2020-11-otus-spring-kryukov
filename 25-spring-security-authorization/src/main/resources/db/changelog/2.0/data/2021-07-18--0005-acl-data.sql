--liquibase formatted sql

--changeset mrmasterz:2021-07-18--0005-acl-data
INSERT INTO acl_sid (id, principal, sid) VALUES
(1, 0, 'ROLE_USER'),
(2, 1, 'Sergey');

INSERT INTO acl_class (id, class) VALUES
(1, 'otus.student.kryukov.dz.domain.Book');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, 1, NULL, 1, 0),
(2, 1, 2, NULL, 1, 0),
(3, 1, 3, NULL, 1, 0),
(4, 1, 4, NULL, 1, 0),
(5, 1, 5, NULL, 1, 0),
(6, 1, 6, NULL, 1, 0),
(7, 1, 7, NULL, 1, 0),
(8, 1, 8, NULL, 1, 0),
(9, 1, 9, NULL, 1, 0),
(10, 1, 10, NULL, 1, 0),
(11, 1, 11, NULL, 1, 0),
(12, 1, 12, NULL, 1, 0),
(13, 1, 13, NULL, 1, 0),
(14, 1, 14, NULL, 1, 0),
(15, 1, 15, NULL, 1, 0),
(16, 1, 16, NULL, 1, 0), -- spare ID so that there are no problems creating the book
(17, 1, 17, NULL, 1, 0), -- for production: When creating book, need to fill tables acl_object_identity and acl_entry
(18, 1, 18, NULL, 1, 0); -- If acl_object_identity table does not contain data about the createted book then we will get an error:
                         -- ERROR: Unable to find ACL information for object identity 'org.springframework.security.acls.domain.ObjectIdentityImpl[Type: otus.student.kryukov.dz.domain.Book; Identifier: 19]'
                         -- If acl_entry table does not contain data about the createted book then we will not be able to see this book on the home page (/book/list)
                         -- WARNING: We use strategy = GenerationType.IDENTITY : maximum ID does not necessarily correspond to the number of books in the DB

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 1, 1, 1, 1),
(2, 2, 1, 1, 1, 1, 1, 1),
(3, 3, 1, 1, 1, 1, 1, 1),
(4, 4, 1, 1, 1, 1, 1, 1),
(5, 5, 1, 1, 1, 1, 1, 1),
(6, 6, 1, 1, 1, 1, 1, 1),
(7, 7, 1, 1, 1, 1, 1, 1),
(8, 8, 1, 1, 1, 1, 1, 1),
(9, 9, 1, 1, 1, 1, 1, 1),
(10, 10, 1, 1, 1, 1, 1, 1),
(11, 11, 1, 1, 1, 1, 1, 1),
(12, 12, 1, 1, 1, 1, 1, 1),
(13, 13, 1, 1, 1, 1, 1, 1),
(14, 14, 1, 1, 1, 1, 1, 1),
(15, 15, 1, 1, 1, 1, 1, 1),
(16, 2, 2, 2, 1, 0, 1, 1),   -- forbid user Sergey from seeing the book "Martian path"
(17, 16, 1, 1, 1, 1, 1, 1),  -- spare ID
(18, 17, 1, 1, 1, 1, 1, 1),  -- spare ID
(19, 18, 1, 1, 1, 1, 1, 1);  -- spare ID
