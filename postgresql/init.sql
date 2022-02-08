CREATE TABLE IF NOT EXISTS singer (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    BIRTH_DATE DATE,
    version INT NOT NULL DEFAULT 0,
    UNIQUE (first_name, last_name)
);

CREATE TABLE IF NOT EXISTS album (
    id SERIAL PRIMARY KEY,
    singer_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    release_date DATE,
    version INT NOT NULL DEFAULT 0,
    UNIQUE (singer_id, title),
    CONSTRAINT FK_ALBUM FOREIGN KEY (singer_id) REFERENCES singer(id)
);

CREATE TABLE IF NOT EXISTS instrument (
    instrument_id VARCHAR(60) PRIMARY KEY
);

CREATE TABLE singer_instrument (
    singer_id INT NOT NULL,
    instrument_id VARCHAR(60) NOT NULL,
    PRIMARY KEY (singer_id, instrument_id),
    CONSTRAINT fk_singer_instrument_1 FOREIGN KEY (singer_id) REFERENCES singer(id) ON DELETE CASCADE,
    CONSTRAINT fk_singer_instrument_2 FOREIGN KEY (instrument_id) REFERENCES instrument(instrument_id)
);

insert into singer (first_name, last_name, birth_date) values ('John', 'Mayer', '1977-10-16');
insert into singer (first_name, last_name, birth_date) values ('Eric', 'Clapton', '1945-03-30');
insert into singer (first_name, last_name, birth_date) values ('John', 'Butler', '1975-04-01');

insert into album (singer_id, title, release_date) values (1, 'The Search For Everything', '2017-01-20');
insert into album (singer_id, title, release_date) values (1, 'Battle Studies', '2009-11-17');
insert into album (singer_id, title, release_date) values (2, 'From The Cradle ', '1994-09-13');

insert into instrument (instrument_id) values('Guitar');
insert into instrument (instrument_id) values('Piano');
insert into instrument (instrument_id) values('Voice');
insert into instrument (instrument_id) values('Drums');
insert into instrument (instrument_id) values('Synthesizer');

insert into singer_instrument(singer_id, instrument_id) values (1, 'Guitar');
insert into singer_instrument(singer_id, instrument_id) values (1, 'Piano');
insert into singer_instrument(singer_id, instrument_id) values (2, 'Guitar');


CREATE FUNCTION get_first_name_by_id(in_id INT)
RETURNS VARCHAR(60)
language plpgsql
AS
$$
BEGIN
    RETURN (SELECT first_name FROM singer WHERE id = in_id);
END;
$$;