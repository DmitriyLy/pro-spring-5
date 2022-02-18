CREATE TABLE IF NOT EXISTS singer (
    id AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    BIRTH_DATE DATE,
    version INT NOT NULL DEFAULT 0,
    UNIQUE (first_name, last_name)
);

CREATE TABLE IF NOT EXISTS album (
    id AUTO_INCREMENT PRIMARY KEY,
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

CREATE TABLE IF NOT EXISTS singer_instrument (
    singer_id INT NOT NULL,
    instrument_id VARCHAR(60) NOT NULL,
    PRIMARY KEY (singer_id, instrument_id),
    CONSTRAINT fk_singer_instrument_1 FOREIGN KEY (singer_id) REFERENCES singer(id) ON DELETE CASCADE,
    CONSTRAINT fk_singer_instrument_2 FOREIGN KEY (instrument_id) REFERENCES instrument(instrument_id)
);